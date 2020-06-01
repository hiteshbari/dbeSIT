package com.infy.db.dbeSIT.model.dto;

import java.util.List;

public class NarConfigDTO implements AppDTOi {
	private String reqId;
	private String narId;
	private List<String> polEditors;
	private List<String> polApprovers;
	private String dbeEnv;

	public NarConfigDTO() {
		super();
	}

	public NarConfigDTO(String dbeEnv) {
		super();
		this.dbeEnv = dbeEnv;
	}


	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getNarId() {
		return narId;
	}

	public void setNarId(String narId) {
		this.narId = narId;
	}

	public List<String> getPolEditors() {
		return polEditors;
	}

	public void setPolEditors(List<String> polEditors) {
		this.polEditors = polEditors;
	}

	public List<String> getPolApprovers() {
		return polApprovers;
	}

	public void setPolApprovers(List<String> polApprovers) {
		this.polApprovers = polApprovers;
	}

	public String getDbeEnv() {
		return dbeEnv;
	}

	public void setDbeEnv(String dbeEnv) {
		this.dbeEnv = dbeEnv;
	}

	@Override
	public String toString() {
		return "NarConfigDTO [narId=" + narId + ", polEditors=" + polEditors + ", polApprovers=" + polApprovers
				+ ", dbeEnv=" + dbeEnv + "]";
	}
}
