package cn.kingcity.main.service.impl;

import cn.kingcity.main.entity.User;
import cn.kingcity.main.mapper.UserMapper;
import cn.kingcity.main.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
