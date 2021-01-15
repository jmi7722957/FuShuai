package cn.kingcity.order.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author Jason
 * @since 2021-01-15
 */
@Component
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.INPUT)      //设置ID手动输入，save时不会自动填进去，自增长
    private Integer id;
    /**
     * 客户ID
     */
    private Integer customerId;

    /**
     * 订单地址
     */
    private String orderAddress;


    /**
     * 定金
     */
    private Double deposit;

    /**
     * 合计金额（未回单）
     */
    private Double totalMoney;

    /**
     * 退回金额
     */
    private Double returnMoney;

    /**
     * 最后金额
     */
    private Double endMoney;

    /**
     * 备注
     */
    private String remarks;

    private Date createTime;

    private Date updateTime;

    private String createPerson;

    private String updatePerson;


}
