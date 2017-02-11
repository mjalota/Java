package com.munish.lms.libraryassets;

public class Book extends Item {

	private String author;
	private String bookISBN;

	public Book(Long assetId, String title, String author, String bookISBN, Long initialNumberOfCopies,
			Long remainingNumberOfCopies) {
		super(assetId, title, initialNumberOfCopies, remainingNumberOfCopies);
		this.setAuthor(author);
		this.bookISBN = bookISBN;
	}

	@Override
	public assetType getAssetType() {
		return assetType.BOOK;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
