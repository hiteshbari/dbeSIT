package com.infy.db.dbeSIT.model.dto;

public class StatusInfoDTO implements AppDTOi {

	private String narId;
	private String reqId;
	private String reqStatus;
	private String reqAction;
	private String actionby;
	private String dbeEnv;

	public StatusInfoDTO() {
		super();
	}

	public String getNarId() {
		return narId;
	}

	public void setNarId(String narId) {
		this.narId = narId;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	public String getActionby() {
		return actionby;
	}

	public void setActionby(String actionby) {
		this.actionby = actionby;
	}

	public String getDbeEnv() {
		return dbeEnv;
	}

	public void setDbeEnv(String dbeEnv) {
		this.dbeEnv = dbeEnv;
	}

	public String getReqAction() {
		return reqAction;
	}

	public void setReqAction(String reqAction) {
		this.reqAction = reqAction;
	}

	@Override
	public String toString() {
		return "StatusInfoDTO [narId=" + narId + ", reqId=" + reqId + ", reqStatus=" + reqStatus + ", reqAction="
				+ reqAction + ", actionby=" + actionby + ", dbeEnv=" + dbeEnv + "]";
	}

}
