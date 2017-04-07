package ua.entity;

public enum Season {
	
	WINTER("зима",0),SPRING("весна",1),SUMMER("літо",2),AUTMN("осінь",3);

	private String description;
	private int id;
	
	

	private Season(String description, int id) {
		this.description = description;
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	 public String getValue() {
	        return name();
	    }

	 public void setValue(String value) {}

	public int getId() {
		return id;
	}
}
