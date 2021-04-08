package mmdis.module;

import java.util.List;

import lebah.module.LebahUserModule;
import lebah.portal.action.Command;
import mmdis.entity.Owner;
import mmdis.entity.Vessel;

public class OwnerRegistrationModule extends LebahUserModule {

	String path = "apps/ownerRegistration";
	
	@Override
	public String start() {
		listOwners();
		return path + "/start.vm";
	}
	
	@Command("listOwners")
	public String listOwners() {
		List<Owner> owners = db.list("select o from Owner o");
		context.put("owners", owners);
		return path + "/listOwners.vm";
	}
	
	@Command("addNewOwner")
	public String addNewOwner() {
		context.remove("owner");
		return path + "/owner.vm";
	}
	
	@Command("saveNewOwner")
	public String saveNewOwner() {
		Owner owner = new Owner();
		owner.setName(getParam("ownerName"));
		db.save(owner);
		return listOwners();
	}

	@Command("editOwner")
	public String editOwner() {
		Owner owner = db.find(Owner.class, getParam("ownerId"));
		context.put("owner", owner);
		return path + "/owner.vm";
	}
	
	@Command("updateOwner")
	public String updateOwner() {
		Owner owner = db.find(Owner.class, getParam("ownerId"));
		owner.setName(getParam("ownerName"));
		db.update(owner);
		return listOwners();
	}
	
	@Command("deleteOwner")
	public String deleteOwner() {
		context.remove("delete_error");
		Owner owner = db.find(Owner.class, getParam("ownerId"));
		try {
			db.delete(owner);
		} catch ( Exception e ) {
			context.put("delete_error", e.getMessage());
		}
		return listOwners();
	}
	
	@Command("addOwnerVessel")
	public String addOwnerVessel() {
		Vessel vessel = db.find(Vessel.class, getParam("vesselId"));
		Owner owner = db.find(Owner.class, getParam("ownerId"));
		
		owner.getVessels().add(vessel);
		db.update(owner);
		
		
		return path + "/owner.vm";
	}
}
