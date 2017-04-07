package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country extends AbstractEntity{
	
	@Column(name="country")
	String country;

	@OneToMany(mappedBy="country")
	private List<Vender> venders = new ArrayList<>();

	public Country(){
		
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Vender> getVender() {
		return venders;
	}

	public void setVender(List<Vender> venders) {
		this.venders = venders;
	}

}
