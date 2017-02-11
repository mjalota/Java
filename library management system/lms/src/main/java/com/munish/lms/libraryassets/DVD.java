package com.munish.lms.libraryassets;

public class DVD  extends Item {

	private String writer;
	private String recordNumber ;

	public DVD(Long assetId, String title, String writer, String recordNumber, Long initialNumberOfCopies,
			Long remainingNumberOfCopies) {
		super(assetId, title, initialNumberOfCopies, remainingNumberOfCopies);
		this.writer = writer;
		this.recordNumber = recordNumber;
	}

	@Override
	public assetType getAssetType() {
		return assetType.DVD;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}
}
