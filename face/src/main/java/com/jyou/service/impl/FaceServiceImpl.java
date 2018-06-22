package com.jyou.service.impl;

import com.jyou.dao.IFaceDao;
import com.jyou.service.IFaceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: RenXin
 * @Date: Created in 2018/6/19
 * @Description:
 */
@Service
public class FaceServiceImpl implements IFaceService {
    @Resource
    private IFaceDao faceDao;
    @Override
    public String selectId(int id) {
        String s = faceDao.selectId(id);
        return s;
    }
}
