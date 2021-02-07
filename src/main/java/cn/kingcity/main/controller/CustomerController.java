package cn.kingcity.main.controller;


import cn.kingcity.main.entity.Customer;
import cn.kingcity.main.mapper.CustomerMapper;
import cn.kingcity.main.service.ICustomerService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @Resource
    ICustomerService service;
    @Resource
    CustomerMapper mapper;
    @Resource
    Customer customer;

    //普通xml查询
    @GetMapping("/xml")
    public ResponseEntity test(){
        List<Customer> customer = mapper.test();
        ResponseEntity<List<Customer>> ok = ResponseEntity.ok(customer);
        return ok;
    }

    //带参联表查询
    @GetMapping("/xml2")
    public ResponseEntity test2(){
        Map map=new HashMap();
        map.put("p_cid",1);
        List<Map> customer = mapper.test2(map);
        ResponseEntity<List<Map>> ok = ResponseEntity.ok(customer);
        return ok;
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
            if (ph!="") {
                Long ph2=Long.parseLong(ph);
                customer.setPhone(ph2);
            }
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
    public boolean upload(@RequestBody Map map) {
        System.out.println(map);
        Map dataMap= (Map) map.get("p_data");
        //以防万一空了个值，统一进一
        Double n= Double.valueOf(dataMap.size());
        int h= (int) Math.ceil(n/5);
        List<Customer> cusList=new ArrayList<Customer>();
        for(int s=2;s<=h;s++){
            Customer cu=new Customer();
            cu.setName(dataMap.get("A"+s).toString());
            cu.setSex(dataMap.get("B"+s).toString());
            cu.setAddress(dataMap.get("C"+s).toString());
            cu.setPhone(Long.parseLong(dataMap.get("D"+s).toString()));
            cu.setIntroducer(dataMap.get("E"+s).toString());
            cusList.add(cu);
        }
        return service.saveBatch(cusList);
    }

}
