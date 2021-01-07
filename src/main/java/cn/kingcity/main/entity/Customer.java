package cn.kingcity.main.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author Jason
 * @since 2020-12-23
 */
@Component
@Data
@EqualsAndHashCode(callSuper = false)       //自动的给model bean实现equals方法和hashcode方法。
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.INPUT)      //设置ID手动输入，save时不会自动填进去，自增长
    private Integer id;

    private String name;

    private String sex;

    private String address;

    private Integer phone;

    private String introducer;

    private String create_person;

    private Date create_time;

}
