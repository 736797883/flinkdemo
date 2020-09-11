package entity;


import lombok.Data;

/**
 * @ClassName: EsWorkOrder
 * @author: ZYX
 * @date: 2020/3/31 14:28
 * @Description: TODO
 */
@Data
public class EsWorkOrder {

    /**
     * 96005工单编号
     */
    private String orderNum;

    /**
     * 12345工单编号
     */
    private String a5Num;

    /**
     * 来电主体
     */
    private String callSubjectValue;

    /**
     * 区级标签冗余字段，存储标签名便于显示
     */
    private String districtLabelName;

    /**
     * 市级标签冗余字段，存储标签名便于显示
     */
    private String cityLabelName;

    /**
     * 工单来源
     */
    private String sourceValue;

    /**
     * 紧急度：普通、紧急、特急、一般
     */
    private String urgencyValue;

    /**
     * 区级承办单位名称
     */
    private String districtContractName;

    /**
     * 街道级承办单位名称
     */
    private String streetContractName;

    /**
     * 来电人
     */
    private String caller;

    /**
     * 来电号码
     */
    private String callerPhone;

    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 问题发生地
     */
    private String address;

    /**
     * 区级工单状态
     */
    private String districtStatus;

    /**
     * 街道级工单状态
     */
    private String streetStatus;

    /**
     * 是否重复
     */
    private String repeatedValue;

    /**
     * 问题分类名称
     */
    private String problemName;


    /**
     * 登记时间
     */
    private String checkInDateTime;

    /**
     * 结案时间
     */
    private String endDateTime;

    /**
     * 区级回复截止时间
     */
    private String districtReplyDateTime;

    /**
     * 社区回复截止时间
     */
    private String streetReplyDateTime;

    /**
     * 区级回退截止时间
     */
    private String districtBackDateTime;

    /**
     * 社区回退截止时间
     */
    private String streetBackDateTime;

    /**
     * 是否属于直派工单
     */
    private String straightValue;

    /**
     * 是否保密
     */
    private String confidential;

    /**
     * 回访时间
     */
    private String visitDateTime;

    /**
     * 是否需要回访字典value
     */
    private String needVisitValue;

    /**
     * 是否成功解决字典value
     */
    private String successSolveValue;

    /**
     * 是否成功回访字典value
     */
    private String successVisitValue;

    /**
     * 回访失败原因字典value
     */
    private String visitFailReasonValue;

    /**
     * 处理结果满意度字典value
     */
    private String processSatisfactionValue;

    /**
     * 承办单位满意度value
     */
    private String orgServiceSatisfactionValue;

    /**
     * 话务员满意度value
     */
    private String opeServiceSatisfactionValue;

    /**
     * 契合度value
     */
    private String replyFitValue;

}
