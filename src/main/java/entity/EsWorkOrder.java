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
     * 工单ID，作为工单索引ID，具有唯一性
     */
    private String id;

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
    private String callSubject;

    /**
     * 来电主体字典value
     */
    private String callSubjectValue;

    /**
     * 工单类型：投诉、诉求、追办
     */
    private String orderType;

    private String orderTypeValue;

    /**
     * 查询逻辑存在且或判断
     * 区级标签
     */
    private String districtLabel;

    /**
     * 区级标签冗余字段，存储标签名便于显示
     */
    private String districtLabelName;

    /**
     * 查询逻辑存在且或判断
     * 市级标签
     */
    private String cityLabel;

    /**
     * 市级标签冗余字段，存储标签名便于显示
     */
    private String cityLabelName;
    /**
     * 工单来源等级
     */
    private String sourceLevel;

    /**
     * 工单来源等级字典value
     */
    private String sourceLevelValue;

    /**
     * 工单来源
     */
    private String source;

    /**
     * 工单来源字典value
     */
    private String sourceValue;

    /**
     * 紧急度：普通、紧急、特急、一般
     */
    private String urgency;

    /**
     * 紧急度字典value
     */
    private String urgencyValue;

    /**
     * 区级承办单位
     */
    private Long districtContractor;

    /**
     * 区级承办单位名称
     */
    private String districtContractName;

    /**
     * 街道级承办单位
     */
    private Long streetContractor;

    /**
     * 街道级承办单位名称
     */
    private String streetContractName;

    /**
     * 来电人
     */
    private String caller;

    /**
     * 联系电话,检索查询字段
     */
    private String telephone;

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
     * 工单描述
     */
    private String content;

    /**
     * 是否重复
     */
    private String repeated;

    private String repeatedValue;

    /**
     * 问题分类id
     */
    private String problemId;

    /**
     * 问题分类名称
     */
    private String problemName;

    /**
     * 区级工单状态
     */
    private String districtStatus;

    /**
     * 街道级工单状态
     */
    private String streetStatus;

    /**
     * 登记时间
     */
    private String checkInDateTime;

    private String districtSignDateTime;

    /**
     * 社区级签收时间
     */
    private String streetSignDateTime;

    /**
     * 结案时间
     */
    private String endDateTime;


    /**
     * 街道回复截止时间
     */
    private String districtReplyDateTime;

    /**
     * 社区级回复截止时间
     */
    private String streetReplyDateTime;

    /**
     * 街道回退截止时间
     */
    private String districtBackDateTime;

    /**
     * 社区级回退截止时间
     */
    private String streetBackDateTime;

    /**
     * 是否属于直派工单Y=是/N=否
     */
    private String straight;

    /**
     * 是否属于直派工单字典value
     */
    private String straightValue;

    /**
     * 通用模式检索条件
     */
    private String searchString;

    /**
     * 冗余字段
     * 对应A5表的工单ID
     */
    private String a5Id;
    /**
     * 冗余字段
     * 对应A5表工单详情ID
     */
    private String orderInfoId;

    /**
     * 是否保密
     */
    private String confidential;

    /**
     * 是否包含附件
     */
    private String hasAttachment;

    /**
     * 问题发生地
     */
    private String address;

    /**
     * 回访时间
     */
    private String visitDateTime;

    /**
     * 是否需要回访:N --> 不需要
     */
    private String needVisit;

    /**
     * 是否需要回访字典value
     */
    private String needVisitValue;

    /**
     * 是否成功解决：0 --> 否 1 --> 是 2 --> 未表态
     */
    private String successSolve;

    /**
     * 是否成功解决字典value
     */
    private String successSolveValue;

    /**
     * 是否成功回访 N --> 否
     */
    private String successVisit;

    /**
     * 是否成功回访字典value
     */
    private String successVisitValue;

    /**
     * 回访失败原因:字典
     */
    private String visitFailReason;

    /**
     * 回访失败原因字典value
     */
    private String visitFailReasonValue;

    /**
     * 处理结果满意度
     */
    private String processSatisfaction;

    /**
     * 处理结果满意度字典value
     */
    private String processSatisfactionValue;

    /**
     * 承办单位满意度
     */
    private String orgServiceSatisfaction;

    /**
     * 承办单位满意度value
     */
    private String orgServiceSatisfactionValue;

    /**
     * 话务员满意度
     */
    private String opeServiceSatisfaction;

    /**
     * 话务员满意度value
     */
    private String opeServiceSatisfactionValue;

    /**
     * 契合度1 --> 契合 2 --> 不契合
     */
    private String replyFit;

    /**
     * 契合度value
     */
    private String replyFitValue;

}
