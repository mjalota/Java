package com.munish.lms.libraryassets;

public class VHS extends Item {
	private String writer;
	private String VHSNumber ;

	public VHS(Long assetId, String title, String writer, String VHSNumber, Long initialNumberOfCopies,
			Long remainingNumberOfCopies) {
		super(assetId, title, initialNumberOfCopies, remainingNumberOfCopies);
		this.writer = writer;
		this.VHSNumber = VHSNumber;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getVHSNumber() {
		return VHSNumber;
	}

	public void setVHSNumber(String vHSNumber) {
		VHSNumber = vHSNumber;
	}
	
	@Override
	public assetType getAssetType() {
		return assetType.VHS;
	}

}
