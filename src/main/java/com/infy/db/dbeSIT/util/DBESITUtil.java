package com.infy.db.dbeSIT.util;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.db.dbeSIT.components.exceptions.CustomJsonConversionEx;
import com.infy.db.dbeSIT.model.dto.AppDTOi;
import com.infy.db.dbeSIT.model.entity.AppENTITYi;


public class DBESITUtil {

	//Program Status 
	public static final String RETURN_DEF = "DEFAULT_NULL";
	public static final String STATUS_OK = "OK";
	public static final String STATUS_ERROR = "ERROR";
	
	//Functional
	public static final String DBE_POLICY_EDITOR = "POLICY_EDITOR";
	public static final String DBE_POLICY_APPROVER = "POLICY_APPROVER";

	//Env
	public static final String DBE_ENV_UAT = "UAT";
	public static final String DBE_ENV_PROD = "PROD";
	public static final String DBE_EMAIL_SYSTEM = "dbe.system@db.com";

	//Interface Msg
	public static final String DBE_MSG_TYPE_CONFIG_NAR = "CONFIG_NAR";
	public static final String DBE_MSG_TYPE_STATUS_INFO = "STATUS_INFO";
	
	public static final String DBE_ACT_CONFIG_NAR = "ACT_CONFIG_NAR";
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static String ListToString(List<String> myList) {
		String rtn = "";
		if (myList.size() > 1) {
			for (String str : myList) {
				rtn = rtn + str + ":";
			}
			rtn = rtn.substring(0, rtn.length() - 1);
		} else {
			rtn = myList.get(0);
		}

		return rtn;
	}

	public static List<String> StringToList(String str) {
		String [] items = str.split(":");
		List<String> l = Arrays.asList(items);
		Arrays.asList(str.split(":"));
		return l;
	}
	
	public static String getDTOtoJson(AppDTOi appDTO) throws CustomJsonConversionEx {
		String rtn = RETURN_DEF;
		 try {
			 rtn = mapper.writeValueAsString(appDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new CustomJsonConversionEx("Error Processing data : "+appDTO.toString());
		}
		return rtn;
	}
	
	public static String getEntitytoJson(AppENTITYi appENTITYi) throws CustomJsonConversionEx {
		String rtn = RETURN_DEF;
		 try {
			 rtn = mapper.writeValueAsString(appENTITYi);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new CustomJsonConversionEx("Error Processing data : "+appENTITYi.toString());
		}
		return rtn;
	}
	
	public static String getStringtoJson(String str) throws CustomJsonConversionEx {
		String rtn = RETURN_DEF;
		 try {
			 rtn = mapper.writeValueAsString(str);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new CustomJsonConversionEx("Error Processing data : "+str);
		}
		return rtn;
	}
}
