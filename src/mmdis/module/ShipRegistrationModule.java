package mmdis.module;

import lebah.db.entity.User;
import lebah.module.LebahVerifyUserAccessModule;

public class ShipRegistrationModule extends LebahVerifyUserAccessModule {
	
	String path = "apps/shipRegistration";
	
	@Override
	public String start() {
		
		if ( !userHasAccess() ) return accessDeniedPage();
		
		return path + "/start.vm";
	}
	


}
