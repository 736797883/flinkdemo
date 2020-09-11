package myDemo;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.java.BatchTableEnvironment;

/**
 * @ClassName: TableDemo
 * @author: ZYX
 * @date: 2020/9/7 09:58
 * @Description: TODO
 */
public class TableDemo {

    public static void main(String[] args) {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        final BatchTableEnvironment tEnv = BatchTableEnvironment.create(env);

        //tEnv.connect()
    }
}
