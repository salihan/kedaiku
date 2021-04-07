package lebah.db.entity;

public class AssignedMenu {
	
	private Menu menu;
	private Menu parent;
	
	private String title;
	private String className;
	
	public AssignedMenu(Menu menu) {
		this.menu = menu;
		this.title = menu.getTitle();
		this.className = menu.getModuleClassName();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}
	
	
	
	

}
