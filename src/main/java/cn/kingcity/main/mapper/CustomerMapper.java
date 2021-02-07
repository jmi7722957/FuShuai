package cn.kingcity.main.mapper;

import cn.kingcity.main.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口:这里是用来写自定义SQL的，平时直接调用sql
 *  这个文件里面的接口方法，在resource/mapper里面的xml绑定映射实现
 * </p>
 *
 * @author Jason
 * @since 2020-12-23
 */
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {
    @Mapper
    @Select("SELECT address,COUNT(*) as number from customer GROUP BY address")
    public List<Map> addressCharts();

    @Mapper
    public List<Customer> test();
}
