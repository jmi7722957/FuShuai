package cn.kingcity.order.controller;


import cn.kingcity.order.entity.OrderTable;
import cn.kingcity.order.mapper.PhotoMapper;
import cn.kingcity.order.service.IOrderService;
import cn.kingcity.order.service.IPhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    IPhotoService photoService;
    @Resource
    PhotoMapper photoMapper;


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
            order.setCustomerId(Integer.parseInt(map.get("p_customerId").toString()));
            order.setOrderAddress(map.get("p_orderAddress").toString());
            order.setDeposit(Double.parseDouble(map.get("p_deposit").toString()));
            order.setRemarks(map.get("p_remarks").toString());
            order.setCreatePerson(map.get("p_userName").toString());
            order.setCreateTime(new Date());
            saveFlag = orderService.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return saveFlag;
    }

    @PostMapping("delete")
    public List<Integer> delOrder(@RequestBody Map map){
        List<Integer> resultList=new ArrayList<Integer>();
        try {
            List<Long> orderIdList=(List) map.get("p_del_list");

            //检查订单照片是否已经删除照片
            resultList=photoMapper.findAllDel(orderIdList);
            if (resultList.size()==0) {
                //从数据库删除
                System.out.println("111");
                orderService.removeByIds(orderIdList);
            }else {
                return resultList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultList;
        }
        return resultList;
    }

}
