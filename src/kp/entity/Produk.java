package kp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="produk_prod")
public class Produk {
	
	@Id @Column(length=50)
	private String prod_id;
	
	@Column(length=50)
	private String prod_nama;
	
	@Column(length=100)
	private String prod_keterangan;
	
	@Column(columnDefinition = "double(30,2)")
	private double prod_harga;	

	@Column(length=100)
	private String prod_gambar;
	
//	@Column(length=50)
//	private String prod_kat_id;
	
	
	@ManyToOne @JoinColumn(name="prod_kat_id")	
	private Kategori kategori;	
	

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}
	

	public Produk() {
		setId(lebah.util.UIDGenerator.getUID());
	}	
	
	private void setId(String uid) {
		this.prod_id = uid;		
	}



	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_nama() {
		return prod_nama;
	}

	public void setProd_name(String prod_nama) {
		this.prod_nama = prod_nama;
	}

	public String getProd_keterangan() {
		return prod_keterangan;
	}

	public void setProd_keterangan(String prod_keterangan) {
		this.prod_keterangan = prod_keterangan;
	}
	
	public double getProd_harga() {
		return prod_harga;
	}

	public void setProd_harga(double prod_harga) {
		this.prod_harga = prod_harga;
	}

	public String getProd_gambar() {
		return prod_gambar;
	}

	public void setProd_gambar(String prod_gambar) {
		this.prod_gambar = prod_gambar;
	}

//	public String getProd_kat_id() {
//		return prod_kat_id;
//	}
//
//	public void setProd_kat_id(String prod_kat_id) {
//		this.prod_kat_id = prod_kat_id;
//	}

	public void setProd_nama(String prod_nama) {
		this.prod_nama = prod_nama;
		
	}

	

}
