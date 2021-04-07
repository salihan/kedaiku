package lebah.db.entity;

import java.util.ArrayList;
import java.util.List;

public class AssignedMenuGroup {
	
	private Menu menu;
	private String title;
	
	private List<AssignedMenu> menus = new ArrayList<>();
	
	public AssignedMenuGroup(Menu menu) {
		this.menu = menu;
		this.title = menu.getTitle();
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AssignedMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<AssignedMenu> menus) {
		this.menus = menus;
	}
	
	@Override
	public boolean equals(Object o) {
		AssignedMenuGroup m = (AssignedMenuGroup) o;
		return m.getMenu().getId().equals(menu.getId());
	}
	
	@Override
	public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (menu.getId() != null ? menu.getId().hashCode() : 0);
        return hash;
	}

}
