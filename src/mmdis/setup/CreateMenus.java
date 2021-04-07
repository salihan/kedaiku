package mmdis.setup;


import java.util.ArrayList;
import java.util.List;

import lebah.db.entity.Menu;
import lebah.db.entity.Persistence;
import lebah.db.entity.Role;

public class CreateMenus {
	
	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		
		Persistence db = Persistence.db();
				
		db.execute("delete from Menu m where m.parent is not null");
		db.execute("delete from Menu m");
		
		String[][] parents = {
				{"administration","Administration"}
		};
		
		List<Menu> parentMenus = new ArrayList<>();
		int i = 0;
		for ( String[] item : parents ) {
			i++;
			Menu menu = new Menu();
			menu.setGrouped(1);
			menu.setId(item[0]);
			menu.setTitle(item[1]);
			menu.setOrderNo(i);
			menu.setIcon("fa fa-square");
			parentMenus.add(menu);
		}
		
		db.save(parentMenus.toArray());
		
		
		String[][] childs = {
				{"administration", "shipregistration","Ship Registration", "mmdis.module.ShipRegistrationModule"}
	
		};
		
		List<Menu> childMenus = new ArrayList<>();
		i = 0;
		for ( String[] item : childs ) {
			i++;
			Menu parentMenu = db.find(Menu.class, item[0]);
			Menu menu = new Menu();
			menu.setParent(parentMenu);
			menu.setId(item[1]);
			menu.setTitle(item[2]);
			menu.setModuleClassName(item[3]);
			menu.setOrderNo(i);
			menu.setIcon("fa fa-square-o");
			
			Role role = db.find(Role.class, "perkapalan");
			menu.getRoles().add(role);
			
			childMenus.add(menu);
		}
		
		db.save(childMenus.toArray());
		
		
	}

}
