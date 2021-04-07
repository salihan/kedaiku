package lebah.module;

import java.util.List;

import lebah.db.entity.Menu;
import lebah.db.entity.Persistence;
import lebah.db.entity.User;
import lebah.portal.action.Command;

public class AssignAdminAccessModule extends LebahUserModule {
	
	String path = "vtl/modules/assignAdminAccess";
	
	@Override
	public String start() {
		
		listMenus();
		
		return path + "/start.vm";
	}
	
	@Command("listMenus")
	public String listMenus() {
		
		List<Menu> parents = db.list("select m from Menu m where m.parent is null order by m.orderNo");
		context.put("parents", parents);
		context.remove("parent");
		
		parents.stream().forEach(p -> {
			p.getAccessAdminUsers();
		});
		
		return path + "/listMenus.vm";
	}
	
	@Command("assignAdmins")
	public String assignAdmins() {
		
		Menu menu = db.find(Menu.class, getParam("menuId"));
		context.put("menu", menu);
		
		return path + "/assignAdmins.vm";
	}
	
	@Command("searchUsers")
	public String searchUsers() {
		List<User> users = db.list("select u from User u where u.firstName like '%" + getParam("searchName") + "%' or u.userName like '%" + getParam("searchName") + "%' order by u.firstName");
		context.put("users", users);
		return path + "/searchUsers.vm";
	}
	
	@Command("selectUser")
	public String selectUser() {
		
		Menu menu = db.find(Menu.class, getParam("menuId"));
		context.put("menu", menu);
		
		User usr = db.find(User.class, getParam("userId"));
		context.put("usr", usr);
		
		return path + "/selectUser.vm";
	}
	
	@Command("assignUserAsAdmin")
	public String updateUser() {
		
		Menu menu = db.find(Menu.class, getParam("menuId"));
		context.put("menu", menu);
		
		User usr = db.find(User.class, getParam("userId"));
		context.put("usr", usr);
				
		menu.getAccessAdminUsers().add(usr);
		usr.getAccessAdminMenus().add(menu);
		
		db.update(menu);
		db.update(usr);
		
		return listMenus();
	}
	
	@Command("removeAdmin")
	public String removeAdmin() {
		
		User usr = db.find(User.class, getParam("userId"));
		context.put("usr", usr);
		
		Menu menu = db.find(Menu.class, getParam("menuId"));
		
		menu.getAccessAdminUsers().remove(usr);
		usr.getAccessAdminMenus().remove(menu);
		
		db.update(menu);
		db.update(usr);		
		
		return listMenus();
	}
	
	
	public static void main(String[] args) {
		
		Persistence db = Persistence.db();
		
		List<Menu> parents = db.list("select m from Menu m where m.parent is null order by m.orderNo");

		
		parents.stream().forEach(p -> {
			System.out.println(p.getTitle());
			p.getAccessAdminUsers().stream().forEach(u -> {
				System.out.println(u.getUserName());
			});
			
		});
		
	}
	
	

}
