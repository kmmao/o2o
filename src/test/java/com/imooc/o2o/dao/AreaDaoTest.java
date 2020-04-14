package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

//import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.service.AreaService;

//import ch.qos.logback.classic.Logger;
//import com.imooc.o2o.service.AreaService;
//import com.imooc.o2o.service.impl.AreaServiceImpl;

public class AreaDaoTest extends BaseTest {

	
	@Autowired
	private AreaDao areaDao;

	@Autowired
	private AreaService areaService;

	@Test
	public void test() {

		List<Area> area = areaDao.selectArea();
		assertEquals(3, area.size());
		for (Area area1 : area) {
			System.out.println(area1.toString());
		}
	}

	@Test
	public void test1() {

		List<Area> area = areaService.selectAll();
		assertEquals("东苑", area.get(0).getAreaName());
		for (Area area2 : area) {
			System.err.println(area2.toString());
		}
	}

}
