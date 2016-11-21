package com.tedpros.cms.model;

import java.io.Serializable;
import java.util.Date;

public class InsertResponse implements Serializable {

	private static final long serialVersionUID = -6482543284961330952L;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
