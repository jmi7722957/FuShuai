package cn.kingcity.main.controller;


import cn.kingcity.main.entity.Customer;
import cn.kingcity.main.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jason
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/main/customer")
public class CustomerController {
    @Autowired
    ICustomerService service;

    @GetMapping("/test")
    public String test(){
        System.out.println("去虐企斩蔗");
        return "GG思密达";
    }

    @GetMapping("/listCustomer")
    public ResponseEntity listCustomer(){
        List<Customer> customerList = service.list();
        ResponseEntity<List<Customer>> ok = ResponseEntity.ok(customerList);
        return ok;
    }


}
