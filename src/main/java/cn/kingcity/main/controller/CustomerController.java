package cn.kingcity.main.controller;


import cn.kingcity.main.entity.Customer;
import cn.kingcity.main.mapper.CustomerMapper;
import cn.kingcity.main.service.ICustomerService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jason
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    ICustomerService service;
    @Resource
    CustomerMapper mapper;
    @Resource
    Customer customer;

    @GetMapping("/test")
    public String test(){
        System.out.println("去虐企斩蔗");
        return "GG思密达";
    }

    @GetMapping("/list")
    public ResponseEntity listCustomer(){
        List<Customer> customerList = service.list();
        ResponseEntity<List<Customer>> ok = ResponseEntity.ok(customerList);
        return ok;
    }
    @GetMapping("/addressEcharts")
    public ResponseEntity addressEcharts(){
        List li=mapper.addressCharts();
        ResponseEntity<Object> ok = ResponseEntity.ok(li);
        return ok;
    }

    @PostMapping("/add")
    public boolean addCustomer(@RequestBody Map map){
        boolean savaFlag = false;
        try {
            //parseInt(ph)最长支持10位
            customer.setName(map.get("p_name").toString());
            customer.setSex(map.get("p_sex").toString());
            customer.setAddress(map.get("p_address").toString());
            customer.setIntroducer(map.get("p_introducer").toString());
            String ph=map.get("p_phone").toString();
            Long ph2=Long.parseLong(ph);
            customer.setPhone(ph2);
            customer.setCreate_person(map.get("p_create_person").toString());
            customer.setCreate_time(new Date());

            savaFlag = service.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return savaFlag;
    }

    @PostMapping("/edit")
    public boolean editCustomer(@RequestBody Map map){
        boolean editFlag = false;
        try {
            int cusid=Integer.parseInt(map.get("p_id").toString());
            //customer.setId(Integer.parseInt(map.get("p_id").toString()));
            customer.setName(map.get("p_name").toString());
            customer.setSex(map.get("p_sex").toString());
            customer.setAddress(map.get("p_address").toString());
            customer.setIntroducer(map.get("p_introducer").toString());
            customer.setPhone(Long.parseLong(map.get("p_phone").toString()));
            UpdateWrapper wrapper=new UpdateWrapper();
            wrapper.eq("id",cusid);
            editFlag = service.update(customer,wrapper);
            //editFlag = service.updateById(customer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return editFlag;
    }

    @PostMapping("/delete")
    public boolean deleteCus(@RequestBody Map map){
        boolean flag = false;
        try {
            List list =new ArrayList();
            list= (List) map.get("p_del_list");
            flag = service.removeByIds(list);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return flag;
    }

    @RequestMapping("/uploadExcel")
    public ResponseEntity upload(String[] excelArr,String excelData,Long onBankId) {
        System.out.println(excelArr.toString());
        System.out.println(excelData.toString());
        System.out.println(onBankId.toString());
        ResponseEntity<Object> ok = ResponseEntity.ok(true);
        return null;
    }

}
