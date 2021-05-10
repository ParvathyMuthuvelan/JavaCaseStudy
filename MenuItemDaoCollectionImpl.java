package com.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.truyum.model.MenuItem;



//@Repository
public class MenuItemDaoCollectionImpl implements MenuItemDao {
 private List<MenuItem> menuItemList;
 public MenuItemDaoCollectionImpl()
 {}
 public MenuItemDaoCollectionImpl(List<MenuItem> menuItemList)
 
 {
	 this.menuItemList=menuItemList;
        /* menuItem=new MenuItem(3, "Pizza", 149.00f, true, DateUtil.convertToDate("21/08/2018"), "Main Course", false);
         menuItemList.add(menuItem);
         menuItem=new MenuItem(4, "French Fries", 57.00f, false, DateUtil.convertToDate("02/07/2017"), "Starters", true);
         menuItemList.add(menuItem);
         menuItem=new MenuItem(5, "Chocolate Brownie", 32.00f, true, DateUtil.convertToDate("02/11/2022"), "Desert", true);
         menuItemList.add(menuItem);*/
     }
     
 

    public List<MenuItem> getMenuItemListAdmin() {
        
        return menuItemList;
    }
  
    public List<MenuItem> getMenuItemListCustomer() {
     List<MenuItem> customerList=new ArrayList<MenuItem>();
     Date date=new Date();
     for(MenuItem obj:menuItemList)
     {
         if((obj.getDateOfLaunch().before(date) || obj.getDateOfLaunch().equals(date)) && obj.isActive())
             customerList.add(obj);
     }
        return customerList;
    }
 
    public void modifyMenuItem(MenuItem menuItem) {
    	int size=0;
    	size=menuItemList.size();
    	  for(int i=0;i<size;i++)
          {
              if (menuItemList.get(i).equals(menuItem))
              {
            	  menuItemList.set(i, menuItem);
            	  break;
              }
          }
    	
    }
 
    public MenuItem getMenuItem(long id) {
        MenuItem menuItem=null;
      for(MenuItem item:menuItemList)
      {
          if (item.getId()==id)
              menuItem=item;
      }
      return menuItem;
    }

}






