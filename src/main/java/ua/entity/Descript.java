package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="descript")
public class Descript extends AbstractEntity{
	@Column(name="descript")
	String descript;

	@OneToMany(mappedBy="descript" )
	private List<Item> items = new ArrayList<>();

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Descript() {	}

	
}
