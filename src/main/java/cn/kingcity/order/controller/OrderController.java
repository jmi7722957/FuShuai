package cn.kingcity.order.controller;


import cn.kingcity.main.entity.Customer;
import cn.kingcity.order.entity.OrderTable;
import cn.kingcity.order.service.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    IOrderService service;
    @Resource
    OrderTable order;

    @GetMapping("/list")
    public ResponseEntity listOrder(){
        List<OrderTable> list=service.list();
        ResponseEntity<List<OrderTable>> ok = ResponseEntity.ok(list);
        return ok;
    }

    //最正统的request传参,- -好麻烦，还是axios比较好，还可以js先解析完
    //MultipartHttpServletRequest是Request的子类，刚好合适
    @PostMapping ("/updatePhoto")
    public boolean updatePhoto(MultipartHttpServletRequest  multiRequest){
        Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
        MultipartFile file=fileMap.get("file");

        OutputStream os = null;
        InputStream inputStream = null;
        String fileName = null;
        try {
            inputStream = file.getInputStream();
            fileName = file.getOriginalFilename();
            System.out.println(inputStream+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String path = "C:\\test\\";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + "MyFileName.png");
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
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
            saveFlag = service.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return saveFlag;
    }
}
