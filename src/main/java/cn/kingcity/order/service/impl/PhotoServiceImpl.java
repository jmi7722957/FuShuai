package cn.kingcity.order.service.impl;

import cn.kingcity.order.entity.Photo;
import cn.kingcity.order.mapper.PhotoMapper;
import cn.kingcity.order.service.IPhotoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jason
 * @since 2021-01-15
 */
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements IPhotoService {
}
