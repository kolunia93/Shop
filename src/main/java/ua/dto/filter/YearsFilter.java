package ua.dto.filter;

import java.util.regex.Pattern;

public class YearsFilter {
	
	private static final Pattern PEATTERN = Pattern.compile("[0-9]+");
	
	private String maxYearsSerch = "";
	
	private String minYearsSerch = "";
	
	private Integer max;
	
	private Double min;

	public String getMaxYearsSerch() {
		return maxYearsSerch;
	}

	public void setMaxYearsSerch(String maxYearsSerch) {
		if(PEATTERN.matcher(maxYearsSerch).matches())max = Integer.valueOf(maxYearsSerch);
		this.maxYearsSerch = maxYearsSerch;

	}

	public String getMinYearsSerch() {
		return minYearsSerch;
	}

	public void setMinYears(String minYearsSerch) {
		if(PEATTERN.matcher(minYearsSerch).matches())min = Double.valueOf(minYearsSerch);
		this.minYearsSerch = minYearsSerch;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}
	
	
	}