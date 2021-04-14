package kp.module;

import kp.entity.Kategori;
import lebah.module.LebahUserModule;
import lebah.portal.action.Command;
import mmdis.entity.Owner;

public class KategoriModule extends LebahUserModule {
	
	String path = "apps/kategoriRegistration";
	
	@Override
	public String start() {
		
		return path + "/start.vm";
	}
	
	@Command("addNewKategori")
	public String addNewKategori() {
		context.remove("kategori");
		return path + "/kategori_reg.vm";
	}
	
	@Command("saveNewKategori")
	public String saveNewKategori() {
		Kategori kategori = new Kategori();
		kategori.setKat_nama(getParam("kat_nama"));
		db.save(kategori);		
		return path + "/start.vm?status='success'";
	}

}
