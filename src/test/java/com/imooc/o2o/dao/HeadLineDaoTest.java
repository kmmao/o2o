package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HeadLineDaoTest extends BaseTest {

    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testHeadLine(){
        List<HeadLine> headLines = headLineDao.queryHeadLine(new HeadLine());
        System.out.println("一共数据："+headLines.size());
    }

}
