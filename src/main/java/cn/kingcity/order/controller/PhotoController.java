package cn.kingcity.order.controller;


import cn.kingcity.order.entity.Photo;
import cn.kingcity.order.service.IPhotoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.*;
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
@RequestMapping("/photo")
public class PhotoController {

    @Resource
    IPhotoService service;
    @Resource
    Photo photo;

    //最正统的request传参,- -好麻烦，还是axios比较好，还可以js先解析完
    //MultipartHttpServletRequest是Request的子类，刚好合适
    @PostMapping("/uploadPhoto/{orderId}")
    public boolean uploadPhoto(MultipartHttpServletRequest multiRequest, @PathVariable String orderId){
        OutputStream os = null;
        InputStream inputStream = null;
        boolean isExist=false;//检测文件是否存在的标记
        boolean saveFlag=false;//是否成功保存
        String fileName = null;
        String writePath="";//要写入的文件夹url
        int intOrderId;
        //先把图片放到文件夹
        try {
            Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
            MultipartFile file=fileMap.get("file");
            inputStream = file.getInputStream();
            fileName = file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String path = "/www/vueDemo/static/baseData/image";
            //String path = "D:\\workspace\\FuShuai_Vue\\static\\baseData\\image\\";

        //先来验证下文件是否存在
            File fileBean=new File(path+fileName);
            if (fileBean.exists())
            {
                isExist=true;
                return false;
            }

            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                //不存在此目录则创建
                tempFile.mkdirs();
            }
            //File.separator智能分隔符/多平台的
            writePath=tempFile.getPath() + File.separator + fileName;
            os = new FileOutputStream(writePath);
            // 每次写1m直到写完
            //从临时读到m内存里面，顺便还跟len做个判断，比较精妙
            while ((len = inputStream.read(bs)) != -1) {
                //写入len长度，每次写bs单位，直到0停止
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                //都跳出了，没有调用io所以不用关闭
                if (!isExist) {
                    os.close();
                    inputStream.close();
                }
                //下面开始把图片标记到数据库

                if(orderId!=null&&!isExist){
                    intOrderId=Integer.parseInt(orderId);
                    photo.setOrderId(intOrderId);
                    photo.setName(fileName);
                    photo.setUrl(writePath);
                    saveFlag=service.save(photo);
                }
                if (saveFlag==true){

                    saveFlag=true;
                }else {
                    saveFlag=false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return saveFlag;
    }

    @PostMapping("/showPhoto")
    public List showPhoto(@RequestBody Map map){
        boolean delFlag=false;
        List list=null;
        int orderId=0;
        if (map.size()!=0) {
            orderId=Integer.parseInt(map.get("p_orderId").toString());
        }else {
            return null;
        }
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("order_id",orderId);
        list=service.list(wrapper);
        return list;
    }

    @PostMapping("/del")
    public boolean delPhoto(@RequestBody Map map){
        boolean delFlag=false;
        try {
            //从文件夹删除文件
            String url=map.get("p_photo_url").toString();
            File file=new File(url);
            if(file.exists()){
                file.delete();
            }else {
                return delFlag;
            }
            //从表中删除
            int photo_id=Integer.parseInt(map.get("p_photo_id").toString());
            delFlag=service.removeById(photo_id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return delFlag;
        }
        return delFlag;
    }
}
