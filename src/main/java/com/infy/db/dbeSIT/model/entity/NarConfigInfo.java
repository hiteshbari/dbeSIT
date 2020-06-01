package com.infy.db.dbeSIT.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tr_narconfiginfo")
public class NarConfigInfo {

	@Id
	@GeneratedValue
	@Column(name = "nId")
	private int nId;

	@Column(name = "reqId")
	private String reqId;

	@Column(name = "narId")
	private String narId;

	public NarConfigInfo() {
		super();
	}

	public int getnId() {
		return nId;
	}

	public void setnId(int nId) {
		this.nId = nId;
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

	@Override
	public String toString() {
		return "NarConfigInfo [nId=" + nId + ", reqId=" + reqId + ", narId=" + narId + "]";
	}

}
