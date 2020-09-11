package myDemo;

import entity.EsOrder;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.types.Row;


/**
 * @ClassName: JdbcDemo
 * @author: ZYX
 * @date: 2020/9/4 16:21
 * @Description: TODO
 */
public class JdbcDemo {

    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        final BatchTableEnvironment tEnv = BatchTableEnvironment.create(env);
        DataSet<Row> workOrders = env.createInput(JDBCInputFormat.buildJDBCInputFormat()
                .setDrivername("com.mysql.jdbc.Driver")
                .setDBUrl("jdbc:mysql://172.16.10.10:3306/workorder?useUnicode=true&characterEncoding=utf-8&useSSL=false")
                .setUsername("root")
                .setPassword("root")
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
        tEnv.createTemporaryView("order2",table);
        // 根据问题分类统计数量
        String thirdLevelSql = "select problemId,COUNT(orderNum) AS total from order2 GROUP BY problemId ORDER By problemId";
        Table thirdLevel = tEnv.sqlQuery(thirdLevelSql);
        DataSet<Row> esWorkOrderDataSet = tEnv.toDataSet(thirdLevel, Row.class);
        esWorkOrderDataSet.print();
        // 根据二级分类统计数量
        String secondLevelSql2 = "select SUBSTRING(problemId, 1, 4) AS problem, COUNT(orderNum) AS total from order2 GROUP BY SUBSTRING(problemId,1,4) ORDER By SUBSTRING(problemId,1,4)";
        Table secondLevel = tEnv.sqlQuery(secondLevelSql2);
        DataSet<Row> esWorkOrderDataSet2 = tEnv.toDataSet(secondLevel, Row.class);
        esWorkOrderDataSet2.print();
        // 根据一级分类统计数量
        String firstLevelSql2 = "select SUBSTRING(problemId, 1, 2) AS problem, COUNT(orderNum) AS total from order2 GROUP BY SUBSTRING(problemId,1,2) ORDER By SUBSTRING(problemId,1,4)";
        Table firstLevel = tEnv.sqlQuery(firstLevelSql2);
        DataSet<Row> esWorkOrderDataSet3 = tEnv.toDataSet(firstLevel, Row.class);
        esWorkOrderDataSet3.print();
    }
    private static final String orderSql = "SELECT r.orderNum, r.problemId, r.belongOrgId\n" +
            "FROM t_receive r";


}
