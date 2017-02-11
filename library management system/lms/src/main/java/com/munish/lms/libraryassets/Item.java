package com.munish.lms.libraryassets;

public abstract class Item implements Asset {
	private Long assetId;
	public String title;
	private long initialNumberOfCopies;
	private long remainingNumberOfCopies ;

	public Item(Long assetId, String title, long initialNumberOfCopies, long remainingNumberOfCopies) {
		this.assetId = assetId;
		this.title = title;
		this.initialNumberOfCopies = initialNumberOfCopies;
		this.remainingNumberOfCopies = remainingNumberOfCopies;
	}

	public Long getAssetId() {
		return assetId;
	}
	
	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getInitialNumberOfCopies() {
		return initialNumberOfCopies;
	}

	public void setInitialNumberOfCopies(Long initialNumberOfCopies) {
		this.initialNumberOfCopies = initialNumberOfCopies;
	}

	public Long getRemainingNumberOfCopies() {
		return remainingNumberOfCopies;
	}

	public void setRemainingNumberOfCopies(Long remainingNumberOfCopies) {
		this.remainingNumberOfCopies = remainingNumberOfCopies;
	}

	public void decrementRemainingNumberOfCopies() {
		this.remainingNumberOfCopies--;
	}
	
	public void incrementRemainingNumberOfCopies() {
		this.remainingNumberOfCopies++ ;
	}

}
