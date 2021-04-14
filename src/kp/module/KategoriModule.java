package kp.module;

import java.util.List;

import kp.entity.Kategori;
import lebah.module.LebahUserModule;
import lebah.portal.action.Command;
import mmdis.entity.Owner;

public class KategoriModule extends LebahUserModule {
	
	String path = "apps/kategoriRegistration";
	
	@Override
	public String start() {
		kategoriList();
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
		//return path + "/start.vm";
		return kategoriList();
	}
	
	@Command("kategoriList")
	public String kategoriList() {
		List<Kategori> kategories = db.list("select k from Kategori k order by k.kat_nama asc");
		context.put("kategories", kategories);
		return path + "/kategoriList.vm";
	}
	
	@Command("kategoriDelete")
	public String kategoriDelete() {
		context.remove("delete_error");
		Kategori kategori = db.find(Kategori.class, getParam("kat_id"));
		try {
			db.delete(kategori);
		} catch ( Exception e ) {
			context.put("delete_error", e.getMessage());
		}
		return kategoriList();
	}
	
	@Command("kategoriEdit")
	public String kategoriEdit() {
		Kategori kategori = db.find(Kategori.class, getParam("kat_id"));
		context.put("kategori", kategori);
		return path + "/kategori_reg.vm";
	}
	
	@Command("kategoriUpdate")
	public String kategoriUpdate() {
		Kategori kategori = db.find(Kategori.class, getParam("kat_id"));
		kategori.setKat_nama(getParam("kat_nama"));
		db.update(kategori);
		return kategoriList();
	}

}
