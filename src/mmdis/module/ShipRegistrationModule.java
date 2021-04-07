package mmdis.module;

import lebah.module.LebahUserModule;

public class ShipRegistrationModule extends LebahUserModule {
	
	String path = "apps/shipRegistration";
	
	@Override
	public String start() {
		return path + "/start.vm";
	}

}
