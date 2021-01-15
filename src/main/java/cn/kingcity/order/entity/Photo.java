package cn.kingcity.order.entity;

import java.io.Serializable;

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
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.INPUT)      //设置ID手动输入，save时不会自动填进去，自增长
    private Integer id;

    private Integer orderId;

    private String name;

    private String url;


}
