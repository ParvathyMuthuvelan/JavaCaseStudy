package com.truyum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truyum.dao.MenuItemDao;
import com.truyum.model.MenuItem;

@Service
public class MenuItemService {
	@Autowired
	private MenuItemDao menuItemDao;
	
	 public List<MenuItem> getMenuItemListAdmin() {
	        
	        return menuItemDao.getMenuItemListAdmin();
	    }
}
