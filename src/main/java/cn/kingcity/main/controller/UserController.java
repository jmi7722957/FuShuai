package cn.kingcity.main.controller;


import cn.kingcity.main.entity.User;
import cn.kingcity.main.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jason
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map map)
    {
        if(!map.isEmpty())
        {
            QueryWrapper wrapper = new QueryWrapper<>();;
            User user=new User();
            String username=map.get("p_username").toString();
            String password=map.get("p_password").toString();
            wrapper.eq("username",username);
            wrapper.eq("password",password);
            User result=service.getOne(wrapper);
            return ResponseEntity.ok(result);
        }else{
            return null;
        }
    }

    @GetMapping("/list")
    public ResponseEntity list()
    {
        List<User> list = service.list();
        return ResponseEntity.ok(list);
    }
}
