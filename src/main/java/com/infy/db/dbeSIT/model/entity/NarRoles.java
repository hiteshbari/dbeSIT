package com.infy.db.dbeSIT.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tr_narroles")
public class NarRoles {

	@Id
	@GeneratedValue
	@Column(name = "rId")
	private int rId;

	@Column(name = "nId")
	private int nId;

	@Column(name = "userEmail")
	private String userEmail;

	@Column(name = "dbeRole")
	private String dbeRole;

	public NarRoles() {
		super();
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int getnId() {
		return nId;
	}

	public void setnId(int nId) {
		this.nId = nId;
	}

	public String getDbeRole() {
		return dbeRole;
	}

	public void setDbeRole(String dbeRole) {
		this.dbeRole = dbeRole;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "NarRoles [rId=" + rId + ", nId=" + nId + ", userEmail=" + userEmail + ", dbeRole=" + dbeRole + "]";
	}

}
