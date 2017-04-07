package ua.dto.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import ua.entity.Season;


public class ItemFilter {
	
	private static final Pattern PEATTERN = Pattern.compile("[0-9]+");

	private String maxPrice = "";
	
	private String minPrice = "";
	
	private String search = "";

	private Integer maxPriceInt;
	
	private Integer minPriceInt;
	
	private String remain = "";
	
	private Integer remainInt;
	
	private List<Integer> venderId=new ArrayList<>();
	
	private List <Integer> seasonSerch=new ArrayList<>();

	private List <Integer> genderSerch=new ArrayList<>();
	
	private List<Integer> descriptId=new ArrayList<>();
	
	private String minYears = "";
	
	private String maxYears = "";
	
	private Integer minYearsInt;
	
	private Integer maxYearsInt;
	
	private String maxSize = "";
	
	private List<Integer> categoryId = new ArrayList<>();

	private String minSize = "";
	
	private Integer maxS;
	
	private Integer minS;

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		if(PEATTERN.matcher(maxPrice).matches())maxPriceInt = Integer.valueOf(maxPrice);
		this.maxPrice = maxPrice;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		if(PEATTERN.matcher(minPrice).matches())minPriceInt = Integer.valueOf(minPrice);
		this.minPrice = minPrice;
	}

	public String getMaxSize() {
		return maxSize;
	}	

	public String getMinSize() {
		return minSize;
	}
	
	public Integer getMaxPriceInt() {
		return maxPriceInt;
	}

	public void setMaxPriceInt(Integer maxPriceInt) {
		this.maxPriceInt = maxPriceInt;
	}

	public Integer getMinPriceInt() {
		return minPriceInt;
	}

	public void setMinPriceInt(Integer minPriceInt) {
		this.minPriceInt = minPriceInt;
	}

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
		if(PEATTERN.matcher(remain).matches())remainInt = Integer.valueOf(remain);
		this.remain = remain;
	}

	public Integer getRemainInt() {
		return remainInt;
	}

	public void setRemainInt(Integer remainInt) {
		this.remainInt = remainInt;
	}

	public List<Integer> getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(List<Integer> categoryId) {
		this.categoryId = categoryId;
	}
	
	public void setCategoryId(int id) {
		System.out.println(id);
		if(id==1){
		this.categoryId=null;
		}else{
			this.categoryId.add(id);
		}
	}

	public List<Integer> getVenderId() {
		return venderId;
	}

	public void setVenderId(List<Integer> venderId) {
		this.venderId = venderId;
	}
	
	public void setVenderId(int id) {
		System.out.println(id);
		if(id==1){
		this.venderId=null;
		}else{
			this.venderId.add(id);
		}
	}

	

	public List<Integer> getDescriptId() {
		return descriptId;
	}

	public void setDescriptId(List<Integer> descriptId) {
		this.descriptId = descriptId;
	}

	public String getMinYears() {
		return minYears;
	}

	public void setMinYears(String minYears) {
		this.minYears = minYears;
	}

	public String getMaxYears() {
		return maxYears;
	}

	public void setMaxYears(String maxYears) {
		this.maxYears = maxYears;
	}

	public Integer getMinYearsInt() {
		return minYearsInt;
	}

	public void setMinYearsInt(Integer minYearsInt) {
		this.minYearsInt = minYearsInt;
	}

	public Integer getMaxYearsInt() {
		return maxYearsInt;
	}

	public void setMaxYearsInt(Integer maxYearsInt) {
		this.maxYearsInt = maxYearsInt;
	}


	public Integer getMaxS() {
		return maxS;
	}

	public void setMaxS(Integer maxS) {
		this.maxS = maxS;
	}

	public Integer getMinS() {
		return minS;
	}

	public void setMinS(Integer minS) {
		this.minS = minS;
	}


	public void setMaxSize(String maxSize) {
		if(PEATTERN.matcher(maxSize).matches())maxS = Integer.valueOf(maxSize);
		this.maxSize = maxSize;
	}
	public void setMinSize(String minSize) {
		if(PEATTERN.matcher(minSize).matches())minS = Integer.valueOf(minSize);
		this.minSize = minSize;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String serch) {
		this.search = serch;
	}

	

	public List<Integer> getSeasonSerch() {
		return seasonSerch;
	}

	public void setSeasonSerch(List<Integer> seasonSerch) {
		this.seasonSerch = seasonSerch;
	}

	public List<Integer> getGenderSerch() {
		return genderSerch;
	}

	public void setGenderSerch(List<Integer> genderSerch) {
		System.out.println(genderSerch);
		this.genderSerch = genderSerch;
	}

	public void setGenderSerch(int i) {
		this.genderSerch.add(i);
		
	}

	public void setSeasonSerch(Season season) {
		this.seasonSerch.add(season.getId());
		
	}
	
}
