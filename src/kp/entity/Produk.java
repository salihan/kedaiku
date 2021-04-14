package kp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name="produk_prod")
public class Produk {
	
	@Id @Column(length=50)
	private String prod_id;
	
	@Column(length=50)
	private String prod_name;
	
	@Column(length=50)
	private String prod_keterangan;
	
	@Column(length=50)
	private String prod_gambar;
	
	@Column(length=50)
	private String prod_kat_id;
	
	
//	@ManyToOne (fetch=FetchType.LAZY)
//    @JoinTable(
//            name = "kategori_kat",
//            joinColumns = {@JoinColumn(name = "prod_kat_id")},
//            inverseJoinColumns = {@JoinColumn(name = "kat_id")}
//	)
	
	
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

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getProd_keterangan() {
		return prod_keterangan;
	}

	public void setProd_keterangan(String prod_keterangan) {
		this.prod_keterangan = prod_keterangan;
	}

	public String getProd_gambar() {
		return prod_gambar;
	}

	public void setProd_gambar(String prod_gambar) {
		this.prod_gambar = prod_gambar;
	}

	public String getProd_kat_id() {
		return prod_kat_id;
	}

	public void setProd_kat_id(String prod_kat_id) {
		this.prod_kat_id = prod_kat_id;
	}

	

}
