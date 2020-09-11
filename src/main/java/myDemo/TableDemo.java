package myDemo;

import entity.EsOrder;
import entity.EsWorkOrder;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.types.Row;

import java.lang.reflect.Field;

/**
 * @ClassName: TableDemo
 * @author: ZYX
 * @date: 2020/9/7 09:58
 * @Description: TODO
 */
public class TableDemo {

    private static final String orderSql = "select r.RECIEVE_NUM AS orderNum, r.PROBLEM_CLASS AS problemId, r.UNDERTAKER AS belongOrgId\n" +
            "from T_H_RECIEVE r";

    public static void main(String[] args) throws Exception {

        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        final BatchTableEnvironment tEnv = BatchTableEnvironment.create(env);
        DataSet<Row> workOrders = env.createInput(JDBCInputFormat.buildJDBCInputFormat()
                .setDrivername("oracle.jdbc.OracleDriver")
                .setDBUrl("jdbc:oracle:thin:@172.25.6.170:1521:ainosearch")
                .setUsername("ainosearch")
                .setPassword("ainosearch")
                .setQuery(orderSql)
                .setRowTypeInfo(new RowTypeInfo(BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO))
                .finish());

        DataSet<EsOrder> map = workOrders.map((MapFunction<Row, EsOrder>) value -> {
            EsOrder esOrder = new EsOrder();
            esOrder.setOrderNum(value.getField(0).toString());
            if(null != value.getField(1)){
                esOrder.setProblemId(value.getField(1).toString());
            }
            if(null != value.getField(2)){
                esOrder.setBelongOrgId(value.getField(2).toString());
            }
            return esOrder;
        });

        Table table = tEnv.fromDataSet(map);
        tEnv.createTemporaryView("order3",table);
        // 根据问题分类统计数量
        String thirdLevelSql = "select problemId,COUNT(orderNum) AS total from order3 GROUP BY problemId ORDER By problemId";
        Table thirdLevel = tEnv.sqlQuery(thirdLevelSql);
        DataSet<Row> esWorkOrderDataSet = tEnv.toDataSet(thirdLevel, Row.class);
        esWorkOrderDataSet.print();
        //tEnv.connect()
    }
}
