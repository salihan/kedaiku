package kp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name="kategori_kat")
public class Kategori {
	
	@Id @Column(length=50)
	private String kat_id;
	@Column(length=50)
	private String kat_nama;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy="kategori")
    private List<Produk> produks;
		
	public List<Produk> getProduks() {
		if ( produks == null ) produks = new ArrayList<>();
		return produks;
	}

	public void setProduks(List<Produk> produks) {
		this.produks = produks;
	}
	

	public Kategori() {
		setId(lebah.util.UIDGenerator.getUID());
	}	
	
	private void setId(String uid) {
		this.kat_id = uid;		
	}


	public String getKat_id() {
		return kat_id;
	}
	public void setKat_id(String kat_id) {
		this.kat_id = kat_id;
	}
	public String getKat_nama() {
		return kat_nama;
	}
	public void setKat_nama(String kat_nama) {
		this.kat_nama = kat_nama;
	}

}
