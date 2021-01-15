package cn.kingcity.order.service.impl;

import cn.kingcity.order.entity.OrderTable;
import cn.kingcity.order.mapper.OrderMapper;
import cn.kingcity.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jason
 * @since 2021-01-15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderTable> implements IOrderService {

}
