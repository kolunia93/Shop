package ua.dto.filter;

import java.util.ArrayList;
import java.util.List;

public class VenderFilter {
	private List<Integer> countryId=new ArrayList<>();
	private String vender="";
	
	
	
	
	
	public List<Integer> getCountryId() {
		return countryId;
	}
	public void setCountryId(List<Integer> countryId) {
		this.countryId = countryId;
	}
	public String getVender() {
		return vender;
	}
	public void setVender(String vender) {
		this.vender = vender;
	}
	

}
