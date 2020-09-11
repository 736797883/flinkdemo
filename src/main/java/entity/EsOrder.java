package entity;

import lombok.Data;

/**
 * @ClassName: EsOrder
 * @author: ZYX
 * @date: 2020/9/11 14:49
 * @Description: TODO
 */
@Data
public class EsOrder {

    private String orderNum;

    private String problemId;

    private String belongOrgId;
}
