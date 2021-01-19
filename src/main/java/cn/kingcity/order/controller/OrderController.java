package cn.kingcity.order.controller;


import cn.kingcity.order.entity.OrderTable;
import cn.kingcity.order.service.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jason
 * @since 2021-01-15
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    IOrderService orderService;
    @Resource
    OrderTable order;


    @GetMapping("/list")
    public ResponseEntity listOrder(){
        List<OrderTable> list=orderService.list();
        ResponseEntity<List<OrderTable>> ok = ResponseEntity.ok(list);
        return ok;
    }


    @PostMapping("/add")
    public boolean addOrder(@RequestBody Map map){
        boolean saveFlag = false;
        try {
            //parseInt(ph)最长支持10位
            order.setCustomerId(Integer.parseInt(map.get("p_customer_id").toString()));
            order.setOrderAddress(map.get("p_order_address").toString());
            order.setDeposit(Double.parseDouble(map.get("p_deposit").toString()));
            order.setTotalMoney(Double.parseDouble(map.get("p_total_money").toString()));
            order.setReturnMoney(Double.parseDouble(map.get("p_return_money").toString()));
            order.setEndMoney(Double.parseDouble(map.get("p_end_money").toString()));
            order.setRemarks(map.get("p_remarks").toString());
            order.setCreatePerson(map.get("p_create_person").toString());
            order.setCreateTime(new Date());
            saveFlag = orderService.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return saveFlag;
    }
}
