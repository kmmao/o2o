package com.imooc.o2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.imooc.o2o.dao.AreaDaoTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.service.AreaService;

@Controller
@RequestMapping("/superController")
public class SuperController {

	private Logger logger = LoggerFactory.getLogger(SuperController.class);

	
	@Autowired
	private AreaService areaService;
	
	
	@RequestMapping("/listarea")
	@ResponseBody
	public Map<String,Object> listArea(){
		logger.info("===start===");
	    long startTime = System.currentTimeMillis();
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<Area> list = new ArrayList<Area>();
		
		try {
			
		  list = areaService.selectAll();
		  map.put("rows",list);
		  map.put("toal",list.size());
			
		}catch (Exception e) {
		
			e.printStackTrace();
			map.put("success",false);
			map.put("errmap",e.toString());
		
		}
		logger.error("test error!");
		long endTime = System.currentTimeMillis();
		logger.debug("costTime:[{}ms]",endTime - startTime);
		logger.info("===end===");
		logger.info("===测试完毕===");
		return map;
		
	}
	
}
