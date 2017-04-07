package ua.dto.filter;

import java.util.regex.Pattern;

public class SizeFilter {
	
	private static final Pattern PEATTERN = Pattern.compile("[0-9]+");
	
	private String maxSize = "";
	
	private String minSize = "";
	
	private Integer max;
	
	private Integer min;

	public String getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(String maxSize) {
		if(PEATTERN.matcher(maxSize).matches())max = Integer.valueOf(maxSize);
		this.maxSize = maxSize;

	}

	public String getMinSize() {
		return minSize;
	}

	public void setMinSize(String minSize) {
		if(PEATTERN.matcher(minSize).matches())min = Integer.valueOf(minSize);
		this.minSize = minSize;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}
	
	}