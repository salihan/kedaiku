package lebah.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Shamsul Bahrin
 *
 */
@Entity
@Table(name="access_level")
public class AccessLevel {
	
	@Id @Column(length=50)
	private String id;

	@ManyToOne @JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne @JoinColumn(name="menu_id")
	private Menu menu;
		
	private int level; //0, 1, 2, 3
	
	
	public AccessLevel() {
		setId(lebah.util.UIDGenerator.getUID());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public String getModuleClassName() {
		return menu.getModuleClassName();
	}


	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	
	
	
}
