package ua.dto.form;



import org.springframework.web.multipart.MultipartFile;

import ua.entity.Category;
import ua.entity.Gender;
import ua.entity.Season;
import ua.entity.Vender;

public class ItemForm {

	private int id;
	
	private String price;
	
	private String remain;
	
	private Category category;
	
	private String name;
	
	private Vender vender;
	
	private Season season;

	private Gender gender;
	
	private String descript;
	
	private String sizeMin;
	
	private String sizeMax;
	
	private String minYears;
	
	private String maxYears;
	
	private MultipartFile file;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getSizeMin() {
		return sizeMin;
	}

	public void setSizeMin(String sizeMin) {
		this.sizeMin = sizeMin;
	}

	public String getSizeMax() {
		return sizeMax;
	}

	public void setSizeMax(String sizeMax) {
		this.sizeMax = sizeMax;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vender getVender() {
		return vender;
	}

	public void setVender(Vender vender) {
		this.vender = vender;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {

		this.season = season;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender=null;
		this.gender = gender;
	}
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
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

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
		if(remain.isEmpty()){
			this.remain="0";
		}else{
		this.remain = remain;}
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		if(descript.isEmpty()){
			this.descript="не вказано";
		}else{
		this.descript = descript;}
	}
	
}
