package com.munish.lms;

import java.text.SimpleDateFormat;

public class Transctions {
	private Long uniqueUserId;
	private Long uniqueAssetId;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm");

	public Transctions() {
	}

	public Transctions(Long uniqueUserId, Long uniqueAssetId ) {
		this.uniqueUserId = uniqueUserId ;
		this.uniqueAssetId = uniqueAssetId ;
	}

	public Long getUniqueUserId() {
		return uniqueUserId;
	}

	public void setUniqueUserId(Long uniqueUserId) {
		this.uniqueUserId = uniqueUserId;
	}

	public Long getUniqueAssetId() {
		return uniqueAssetId;
	}

	public void setUniqueAssetId(Long uniqueAssetId) {
		this.uniqueAssetId = uniqueAssetId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((uniqueAssetId == null) ? 0 : uniqueAssetId.hashCode());
		result = prime * result	+ ((uniqueUserId == null) ? 0 : uniqueUserId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transctions other = (Transctions) obj;
		if (uniqueAssetId == null) {
			if (other.uniqueAssetId != null)
				return false;
		} else if (!uniqueAssetId.equals(other.uniqueAssetId))
			return false;
		if (uniqueUserId == null) {
			if (other.uniqueUserId != null)
				return false;
		} else if (!uniqueUserId.equals(other.uniqueUserId))
			return false;
		return true;
	}
	
}
