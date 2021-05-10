package com.annotation;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truyum.model.MenuItem;
import com.truyum.service.MenuItemService;

public class TruyumMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		MenuItemService menuItemService = (MenuItemService) context.getBean("menuItemService");
		//System.out.println(menuItemService);
		List<MenuItem> adminList = menuItemService.getMenuItemListAdmin();
		adminList.forEach(obj->System.out.println(obj));
		
	}

}
