package lebah.module;

import java.io.IOException;

import lebah.db.entity.User;
import lebah.portal.action.LebahModule;

public class LebahVerifyUserAccessModule extends LebahModule {
	
	
	protected User loginUser = null;
	
	
	@Override
	public void preProcess() {
		User user = (User) context.get("user");
		if ( user == null ) {
			try {
				response.sendRedirect("../expired.jsp?msg=1");
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		}
		else {
			loginUser = user;
		}
		
		System.out.println("command = " + command);
		context.put("command", command);
	}

	@Override
	public String start() {
		return null;
	}
	
	
	
	public boolean userHasAccess() {
		User user = (User) context.get("user");
		return user.getAccessLevels().stream().filter(a -> a.getModuleClassName().equals(this.className)).findAny().isPresent();
	
	}
	
	public String accessDeniedPage() {
		return "vtl/modules/accessDenied/start.vm";
	}
	
	

}
