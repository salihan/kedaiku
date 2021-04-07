package mmdis.setup;

import lebah.db.entity.Persistence;
import lebah.db.entity.Role;
import lebah.db.entity.User;

public class InitializeSetupDatabase {
	
	public static void main(String[] args) throws Exception {
		
		Persistence.db().execute("delete from User u");
		Persistence.db().execute("delete from Role r");
		
		createRoles();
		createAdmin();
		
		Persistence.db().close();
		
	}
	
	
	public static void createRoles() {
		
		String[][] roles = { {"admin", "Admin"}, {"anon", "Anon"}, {"moduleadmin", "Module Admin"}, {"user1", "User1"}, {"perkapalan", "Perkapalan"} };
		for ( String[] r : roles ) {
			
			Role role = new Role();
			role.setId(r[0]);
			role.setName(r[1]);
			
			Persistence.db().save(role);
		}
	}
	
	public static void createAdmin() {
		
		String[][] users = { 
				{"admin","admin","Administrator", "admin"}, 
				{"ali","123","Ali", "user1"}, 
				{"mamat","mamat","Mamat", "perkapalan"}, 
				{"siti","siti","Siti", "perkapalan"},
				{"faizal","faizal","Faizal","moduleadmin"}
		};
		
		for ( String[] u : users ) {
			User user = new User();
			user.setUserName(u[0]);
			user.setUserPassword(u[1]);
			user.setFirstName(u[2]);
			Role role = Persistence.db().find(Role.class, u[3]);
			if ( role != null) user.setRole(role);
			
			Persistence.db().save(user);
		
		}
		
		
		
	}

}
