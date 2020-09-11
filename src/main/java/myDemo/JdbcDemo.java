package myDemo;

import entity.EsWorkOrder;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.aggregation.Aggregations;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.functions.AggregateFunction;
import org.apache.flink.table.planner.functions.aggfunctions.CountAggFunction;
import org.apache.flink.types.Row;

import static org.apache.flink.table.api.DataTypes.STRING;

/**
 * @ClassName: JdbcDemo
 * @author: ZYX
 * @date: 2020/9/4 16:21
 * @Description: TODO
 */
public class JdbcDemo {

    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //DataSet<EsWorkOrder>
        DataSet<Row> workOrders = env.createInput(JDBCInputFormat.buildJDBCInputFormat()
                .setDrivername("com.mysql.jdbc.Driver")
                .setDBUrl("jdbc:mysql://172.16.10.10:3306/workorder?useUnicode=true&characterEncoding=utf-8&useSSL=false")
                .setUsername("root")
                .setPassword("root")
                .setQuery(orderSql)
                .setRowTypeInfo(new RowTypeInfo(BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.LONG_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.LONG_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO,
                        BasicTypeInfo.STRING_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO))
                .finish());
        workOrders.groupBy(21).sum(33).print();
       // workOrders.print();//.groupBy(row -> row.getField(21));

    }
    private static final String orderSql = "SELECT re.id,\n" +
            "               re.orderNum,\n" +
            "               DATE_FORMAT(re.checkInTime, '%Y-%m-%d %H:%i:%s')      AS checkInDateTime,\n" +
            "               re.a5Num,\n" +
            "               re.sourceLevel,\n" +
            "               IF(re.sourceLevel = '1', '市级', '区级')                  AS sourceLevelValue,\n" +
            "               re.source,\n" +
            "               re.typeName                                           AS sourceValue,\n" +
            "               re.callSubject,\n" +
            "               callSubDic.typeName                                   AS callSubjectValue,\n" +
            "               re.`type`                                             AS orderType,\n" +
            "               orderTypeDic.typeName                                 AS orderTypeValue,\n" +
            "               re.urgency,\n" +
            "               u.urgencyValue,\n" +
            "               re.caller,\n" +
            "               re.title,\n" +
            "               re.content,\n" +
            "               re.repeated,\n" +
            "               re.problemId,\n" +
            "               re.problemName,\n" +
            "               re.telephone,\n" +
            "               re.callerPhone,\n" +
            "               re.contactNumber,\n" +
            "               re.a5_id                                              AS a5Id,\n" +
            "               re.confidential,\n" +
            "               re.address,\n" +
            "               re.order_info_id                                      AS orderInfoId,\n" +
            "               DATE_FORMAT(re.endDate, '%Y-%m-%d %H:%i:%s')          AS endDateTime,\n" +
            "               city.cityLabel,\n" +
            "               city.cityLabelName,\n" +
            "               district.districtLabel,\n" +
            "               district.districtLabelName,\n" +
            "               DATE_FORMAT(flow2.sign_time, '%Y-%m-%d %H:%i:%s')     AS districtSignDateTime,\n" +
            "               flow2.to_org_id                                       AS districtContractor,\n" +
            "               flow2.org_name                                        AS districtContractName,\n" +
            "               DATE_FORMAT(flow2.deadline, '%Y-%m-%d %H:%i:%s')      AS districtReplyDateTime,\n" +
            "               DATE_FORMAT(flow2.deadline_back, '%Y-%m-%d %H:%i:%s') AS districtBackDateTime,\n" +
            "               re.proStatus                                          AS districtStatus,\n" +
            "               DATE_FORMAT(flow3.sign_time, '%Y-%m-%d %H:%i:%s')     AS streetSignDateTime,\n" +
            "               flow3.to_org_id                                       AS streetContractor,\n" +
            "               flow3.org_name                                        AS streetContractName,\n" +
            "               DATE_FORMAT(flow3.deadline, '%Y-%m-%d %H:%i:%s')      AS streetReplyDateTime,\n" +
            "               DATE_FORMAT(flow3.deadline_back, '%Y-%m-%d %H:%i:%s') AS streetBackDateTime,\n" +
            "               flow3.process_status                                  AS streetStatus,\n" +
            "               'N'                                                   AS straight,\n" +
            "               '否'                                                   AS straightValue,\n" +
            "               IF(IFNULL(rf.fileNum, 0) > 0, 'Y', 'N')               AS hasAttachment,\n" +
            "               DATE_FORMAT(visit.visit_time, '%Y-%m-%d %H:%i:%s')    AS visitDateTime,\n" +
            "               visit.need_visit                                      as needVisit,\n" +
            "               needVisit.typeName                                    AS needVisitValue,\n" +
            "               visit.success_solve                                   AS successSolve,\n" +
            "               visitSuccessSolve.typeName                            AS successSolveValue,\n" +
            "               visit.success_visit                                   AS successVisit,\n" +
            "               successVisit.typeName                                 AS successVisitValue,\n" +
            "               visit.visit_fail_reason                               AS visitFailReason,\n" +
            "               failReason.typeName                                   AS visitFailReasonValue,\n" +
            "               visit.process_satisfaction                            AS processSatisfaction,\n" +
            "               processSatisfaction.typeName                          AS processSatisfactionValue,\n" +
            "               visit.organizer_service_satisfaction                  AS orgServiceSatisfaction,\n" +
            "               organizerServiceSatisfaction.typeName                 AS orgServiceSatisfactionValue,\n" +
            "               visit.operator_service_satisfaction                   AS opeServiceSatisfaction,\n" +
            "               visitStatisfaction.typeName                           AS opeServiceSatisfactionValue,\n" +
            "               visit.reply_fit                                       AS replyFit,\n" +
            "               visitReplyFit.typeName                                AS replyFitValue\n" +
            "        FROM (\n" +
            "                 SELECT r.id,\n" +
            "                        r.checkInTime,\n" +
            "                        r.orderNum,\n" +
            "                        r.a5Num,\n" +
            "                        r.sourceLevel,\n" +
            "                        r.source,\n" +
            "                        s.typeName,\n" +
            "                        r.callSubject,\n" +
            "                        r.`type`,\n" +
            "                        r.urgency,\n" +
            "                        r.caller,\n" +
            "                        r.title,\n" +
            "                        trc.content,\n" +
            "                        r.repeated,\n" +
            "                        r.problemId,\n" +
            "                        r.problemName,\n" +
            "                        r.callerPhone,\n" +
            "                        r.a5_id,\n" +
            "                        r.order_info_id,\n" +
            "                        r.endDate,\n" +
            "                        r.proStatus,\n" +
            "                        r.telephone                                AS contactNumber,\n" +
            "                        r.confidential,\n" +
            "                        r.address,\n" +
            "                        concat_ws(',', r.callerPhone, r.telephone) AS telephone,\n" +
            "                        r.update_time,\n" +
            "                        r.create_time\n" +
            "                 FROM t_receive r,\n" +
            "                      t_receive_content trc,\n" +
            "                      (\n" +
            "                          SELECT de.typeCode,\n" +
            "                                 de.typeName\n" +
            "                          FROM sys_dict_ext de,\n" +
            "                               sys_dict d\n" +
            "                          WHERE d.id = de.parentId\n" +
            "                            AND d.typeCode = 'receiveFrom'\n" +
            "                          UNION ALL\n" +
            "                          SELECT de.typeCode,\n" +
            "                                 de.typeName\n" +
            "                          FROM sys_dict_ext de,\n" +
            "                               sys_dict d\n" +
            "                          WHERE d.id = de.parentId\n" +
            "                            AND d.typeCode = 'citySource'\n" +
            "                      ) s\n" +
            "                 WHERE r.id = trc.orderId\n" +
            "                   AND r.source = s.typeCode\n" +
            "             ) re\n" +
            "                 LEFT JOIN sys_urgency u ON re.urgency = u.urgencyKey\n" +
            "                 LEFT JOIN (SELECT e.typeCode, e.typeName\n" +
            "                            FROM sys_dict_ext e\n" +
            "                            WHERE e.parentId = '5bd7ed766fef478fa33eb73a66ab30ef') callSubDic\n" +
            "                           ON re.callSubject = callSubDic.typeCode\n" +
            "                 LEFT JOIN (SELECT e.typeCode, e.typeName\n" +
            "                            FROM sys_dict_ext e\n" +
            "                            WHERE e.parentId = 'ee68b7597ee246c0bf617251a5bf108d') orderTypeDic\n" +
            "                           ON re.`type` = orderTypeDic.typeCode\n" +
            "                 LEFT JOIN (\n" +
            "            SELECT trf2.order_id,\n" +
            "                   trf2.sign_time,\n" +
            "                   trf2.to_org_id,\n" +
            "                   o.org_name,\n" +
            "                   trf2.deadline,\n" +
            "                   trf2.deadline_back,\n" +
            "                   trf2.process_status,\n" +
            "                   trf2.createtime,\n" +
            "                   trf2.updatetime\n" +
            "            FROM t_receive_flow trf2\n" +
            "                     LEFT JOIN sys_org o ON trf2.to_org_id = o.id\n" +
            "            WHERE trf2.process_level = 2\n" +
            "        ) AS flow2 ON re.id = flow2.order_id\n" +
            "                 LEFT JOIN (\n" +
            "            SELECT trf3.order_id,\n" +
            "                   trf3.sign_time,\n" +
            "                   trf3.to_org_id,\n" +
            "                   o.org_name,\n" +
            "                   trf3.deadline,\n" +
            "                   trf3.deadline_back,\n" +
            "                   trf3.process_status,\n" +
            "                   trf3.createtime,\n" +
            "                   trf3.updatetime\n" +
            "            FROM t_receive_flow trf3\n" +
            "                     LEFT JOIN sys_org o ON trf3.to_org_id = o.id\n" +
            "            WHERE trf3.process_level = 3\n" +
            "        ) AS flow3 ON re.id = flow3.order_id\n" +
            "                 LEFT JOIN (\n" +
            "            SELECT rec.id,\n" +
            "                   rec.a5Id,\n" +
            "                   GROUP_CONCAT(lr.label_id)   AS cityLabel,\n" +
            "                   GROUP_CONCAT(lr.label_name) AS cityLabelName\n" +
            "            FROM (SELECT r.id, o.id AS a5Id\n" +
            "                  FROM t_receive r\n" +
            "                           LEFT JOIN t_orderinfo o ON r.a5Num = o.order_num) rec\n" +
            "                     LEFT JOIN t_order_label_relevance lr ON lr.order_id = rec.a5Id\n" +
            "            GROUP BY rec.a5Id\n" +
            "        ) city ON city.id = re.id\n" +
            "                 LEFT JOIN (\n" +
            "            SELECT r.id,\n" +
            "                   GROUP_CONCAT(rl.labelId)  AS districtLabel,\n" +
            "                   GROUP_CONCAT(l.labelName) AS districtLabelName,\n" +
            "                   rl.creatTime,\n" +
            "                   l.creatTime               AS lcreatTime,\n" +
            "                   l.updateTime              AS lupdateTime\n" +
            "            FROM t_receive r\n" +
            "                     LEFT JOIN t_receive_label rl ON r.id = rl.orderId\n" +
            "                     LEFT JOIN t_label l ON l.id = rl.labelId\n" +
            "            GROUP BY r.id\n" +
            "        ) district ON district.id = re.id\n" +
            "                 LEFT JOIN (SELECT f.orderId, COUNT(f.filePath) AS fileNum FROM t_receive_files f GROUP BY f.orderId) rf\n" +
            "                           ON re.id = rf.orderId\n" +
            "                 LEFT JOIN (SELECT v.receive_id,\n" +
            "                                   v.visit_time,\n" +
            "                                   v.need_visit,\n" +
            "                                   v.success_solve,\n" +
            "                                   v.success_visit,\n" +
            "                                   v.visit_fail_reason,\n" +
            "                                   v.process_satisfaction,\n" +
            "                                   v.organizer_service_satisfaction,\n" +
            "                                   v.operator_service_satisfaction,\n" +
            "                                   v.reply_fit\n" +
            "                            FROM t_visit v) AS visit\n" +
            "                           ON re.id = visit.receive_id\n" +
            "                 LEFT JOIN (SELECT e.typeCode, e.typeName\n" +
            "                            FROM sys_dict_ext e\n" +
            "                            WHERE e.parentId = 'ee68b7597ee246c0bf617251a5bf209g') failReason\n" +
            "                           ON visit.visit_fail_reason = failReason.typeCode\n" +
            "                 LEFT JOIN (SELECT e.typeCode, e.typeName\n" +
            "                            FROM sys_dict_ext e\n" +
            "                            WHERE e.parentId = 'ee68b7597ee246c0bf617251a5bf208g') visitStatisfaction\n" +
            "                           ON visit.operator_service_satisfaction = visitStatisfaction.typeCode\n" +
            "                 LEFT JOIN (SELECT e.typeCode, e.typeName\n" +
            "                            FROM sys_dict_ext e\n" +
            "                            WHERE e.parentId = 'ee68b7597ee246c0bf617251a5bf208g') processSatisfaction\n" +
            "                           ON visit.process_satisfaction = processSatisfaction.typeCode\n" +
            "                 LEFT JOIN (SELECT e.typeCode, e.typeName\n" +
            "                            FROM sys_dict_ext e\n" +
            "                            WHERE e.parentId = 'ee68b7597ee246c0bf617251a5bf208g') organizerServiceSatisfaction\n" +
            "                           ON visit.organizer_service_satisfaction = organizerServiceSatisfaction.typeCode\n" +
            "                 LEFT JOIN (SELECT e.typeCode, e.typeName\n" +
            "                            FROM sys_dict_ext e\n" +
            "                            WHERE e.parentId = '153dafb37fe44f2ca871bbe2e1004fbb') visitReplyFit\n" +
            "                           ON visit.reply_fit = visitReplyFit.typeCode\n" +
            "                 LEFT JOIN (SELECT e.typeCode, e.typeName\n" +
            "                            FROM sys_dict_ext e\n" +
            "                            WHERE e.parentId = 'ee68b7597ee246c0bf617251b5bf219g') visitSuccessSolve\n" +
            "                           ON visit.success_solve = visitSuccessSolve.typeCode\n" +
            "                 LEFT JOIN (SELECT e.typeCode, e.typeName\n" +
            "                            FROM sys_dict_ext e\n" +
            "                            WHERE e.parentId = 'ee68b7597ee246c0bf617251b5bf219g') needVisit\n" +
            "                           ON visit.need_visit = needVisit.typeCode\n" +
            "                 LEFT JOIN (SELECT e.typeCode, e.typeName\n" +
            "                            FROM sys_dict_ext e\n" +
            "                            WHERE e.parentId = 'ee68b7597ee246c0bf617251b5bf219g') successVisit\n" +
            "                           ON visit.success_visit = successVisit.typeCode";
}
