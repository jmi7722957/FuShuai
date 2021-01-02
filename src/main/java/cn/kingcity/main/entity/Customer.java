package cn.kingcity.main.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Jason
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String sex;

    private String address;

    private Integer phone;

    /**
     * 介绍人
     */
    private String introducer;

    private String create_person;

    private Date create_time;
}
