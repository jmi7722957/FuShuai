package cn.kingcity.main.service.impl;

import cn.kingcity.main.entity.Customer;
import cn.kingcity.main.mapper.CustomerMapper;
import cn.kingcity.main.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jason
 * @since 2020-12-23
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
