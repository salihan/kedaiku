package lebah.module;

import java.util.ArrayList;
import java.util.List;

import lebah.db.entity.AccessLevel;
import lebah.db.entity.AssignedMenu;
import lebah.db.entity.AssignedMenuGroup;
import lebah.db.entity.Menu;
import lebah.db.entity.Persistence;
import lebah.db.entity.Role;
import lebah.db.entity.User;
import lebah.portal.action.Command;

public class UserAccessLevelModule extends LebahUserModule {
	
	String path = "vtl/modules/userAccessLevel";
	
	@Override
	public String start() {
		
		User user = (User) context.get("user");
		
		System.out.println("current user= " + user.getFirstName());
		
		return path + "/start.vm";
	}
	
	@Command("searchUsers")
	public String searchUsers() {
		List<User> users = db.list("select u from User u where u.firstName like '%" + getParam("searchName") + "%' or u.userName like '%" + getParam("searchName") + "%' order by u.firstName");
		context.put("users", users);
		return path + "/searchUsers.vm";
	}
	
	
	@Command("getUserAccess")
	public String getUserAccess() {

		User usr = db.find(User.class, getParam("userId"));
		context.put("usr", usr);
		
		List<Role> userRoles = new ArrayList<>();
		userRoles.add(usr.getRole());
		userRoles.addAll(usr.getSecondaryRoles());
		
		List<AssignedMenu> assignedMenus = new ArrayList<>();
		userRoles.stream().map(r -> r.getMenus()).forEach(menus -> {
			menus.stream().forEach(m -> {
				assignedMenus.add(new AssignedMenu(m));
			});
		});;
		
		List<AssignedMenuGroup> parents = new ArrayList<>();
		
		assignedMenus.stream().forEach(am -> {
			AssignedMenuGroup g = new AssignedMenuGroup(am.getMenu().getParent());
			if ( !parents.contains(g) ) parents.add(g);
		});
		
		parents.forEach(g -> {
			userRoles.stream().map(r -> r.getMenus()).forEach(menus -> {
				menus.stream().filter(menu -> g.getMenu().getId().equals(menu.getParent().getId()) )
					.forEach(menu -> {
						g.getMenus().add(new AssignedMenu(menu));
					});
			});;
		});
		
		context.put("assignedMenus", parents);
		
		return path + "/userAccess.vm";
	}
	
	
	@Command("updateAccess")
	public String updateAccess() {
		
		String value = getParam("value");
		
		User user = db.find(User.class, getParam("userId"));
		Menu menu = db.find(Menu.class, getParam("menuId"));
		
		AccessLevel accessLevel = db.get("select a from AccessLevel a where a.user.id = '" + user.getId() + "' and a.menu.id = '" + menu.getId() + "'");
		
		if ( "true".equals(value)) {
			if ( accessLevel == null ) {

				accessLevel = new AccessLevel();
				accessLevel.setUser(user);
				accessLevel.setMenu(menu);
				accessLevel.setLevel(0);
				
				db.save(accessLevel);
				
				menu.getAccessLevels().add(accessLevel);
				db.update(menu);
				
				user.getAccessLevels().add(accessLevel);
				db.update(user);
				
			}
		} else {
			if ( accessLevel != null ) {
				db.delete(accessLevel);
				
				menu.getAccessLevels().remove(accessLevel);
				db.update(menu);
				
				user.getAccessLevels().remove(accessLevel);
				db.update(user);
				
			}
		}
		
		return path + "/updateAccess.vm";
	}
	
	
	public static void main(String[] args) {
		Persistence db = Persistence.db();
		User usr = db.find(User.class, "3362f8a530c7c5b998c693437918dd629d57e00e");
		
		List<Role> userRoles = new ArrayList<>();
		userRoles.add(usr.getRole());
		userRoles.addAll(usr.getSecondaryRoles());
		
		List<AssignedMenu> assignedMenus = new ArrayList<>();
		userRoles.stream().map(r -> r.getMenus()).forEach(menus -> {
			menus.stream().forEach(m -> {
				assignedMenus.add(new AssignedMenu(m));
			});
		});;
		
		List<AssignedMenuGroup> parents = new ArrayList<>();
		
		assignedMenus.stream().forEach(am -> {
			AssignedMenuGroup g = new AssignedMenuGroup(am.getMenu().getParent());
			if ( !parents.contains(g) ) parents.add(g);
		});
		
		parents.forEach(g -> {
			userRoles.stream().map(r -> r.getMenus()).forEach(menus -> {
				menus.stream().filter(menu -> g.getMenu().getId().equals(menu.getParent().getId()) ).forEach(menu -> {
					g.getMenus().add(new AssignedMenu(menu));
				});
			});;
		});
		
		System.out.println(parents.size());
		
		parents.forEach(g -> {
			System.out.println(g.getTitle());
			System.out.println("---");
			g.getMenus().stream().forEach(am -> {
				System.out.println(am.getTitle());
			});
		});
		
	}
	
	
}
