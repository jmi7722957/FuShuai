package cn.kingcity.order.mapper;

import cn.kingcity.order.entity.Photo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2021-01-15
 */
@Repository
public interface PhotoMapper extends BaseMapper<Photo> {
    @Mapper
    public List<Integer> findAllDel(List<Long> idList);
}
