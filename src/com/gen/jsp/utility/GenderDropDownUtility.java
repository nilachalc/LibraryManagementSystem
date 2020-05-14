package com.gen.jsp.utility;

import java.util.HashMap;
import java.util.Map;

import com.gen.util.ApplicationConstants;

public class GenderDropDownUtility {
	public static Map<String, String> genderList = new HashMap<String, String>();
	
	static {
		genderList.put(ApplicationConstants.GENDER_MALE, ApplicationConstants.GENDER_MALE);
		genderList.put(ApplicationConstants.GENDER_FEMALE, ApplicationConstants.GENDER_FEMALE);
		genderList.put(ApplicationConstants.GENDER_OTHER, ApplicationConstants.GENDER_OTHER);
	}
}
