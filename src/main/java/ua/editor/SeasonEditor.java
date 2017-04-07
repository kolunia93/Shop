package ua.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;

import ua.entity.Season;

public class SeasonEditor extends PropertyEditorSupport{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.isBlank(text))
            return;

		setValue(Season.valueOf(text));
	}
	
	 @Override
     public String getAsText() {
         if(getValue() == null)
             return "";

         return ((Season) getValue()).name();
     }

}
