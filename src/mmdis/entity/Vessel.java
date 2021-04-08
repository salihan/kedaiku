package mmdis.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="vessel")
public class Vessel {
	
	@Id @Column(length=50)
	private String id;
	@Column(length=50)
	private String name;
	
	@ManyToMany (fetch=FetchType.LAZY)
    @JoinTable(
            name = "vessel_owner",
            joinColumns = {@JoinColumn(name = "vessel_id")},
            inverseJoinColumns = {@JoinColumn(name = "owner_id")}
    )
	private List<Owner> owners;
	
	public Vessel() {
		setId(lebah.util.UIDGenerator.getUID());
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}
	
	
	

}
