package com.jyou.dao;

import org.springframework.stereotype.Repository;

/**
 * @Author: RenXin
 * @Date: Created in 2018/6/19
 * @Description:
 */
@Repository
public interface IFaceDao {
    String selectId(int id);
}
