package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="vender")
public class Vender extends AbstractEntity{
	
	@Column(name="vender")
	private String vender;
		
	@ManyToOne()
	@JoinColumn(name="country")
	private Country country;
		
	@OneToMany(mappedBy="vender")
	private List<Item> items = new ArrayList<>();
	
	public Vender(){
		
	}
	
	public String getVender() {
		return vender;
	}


	public void setVender(String vender) {
		this.vender = vender;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}



	

	

	
}
