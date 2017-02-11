package com.munish.lms.libraryassets;

import static org.junit.Assert.assertEquals;

import org.junit.Test ;
import org.junit.After;
import org.junit.Before;

import com.munish.lms.libraryassets.Book;
import com.munish.lms.libraryassets.Item;
import com.munish.lms.libraryassets.Asset.assetType;

public class BookTest {
	protected Long assetId = 1234L;
	protected String author = "author1";
	protected String title = "The Art Of Computer Prog." ;
	protected String bookISBN = "121212";
	protected Long initalNoOfCopies = 100L ;
	protected Long remainingNoOfCopies = 5L ; 
	protected Item  bookObj = null ; 
	
	@Before
	public void setUp() throws Exception {		
		bookObj  = new Book (assetId, title, author, bookISBN,  initalNoOfCopies , remainingNoOfCopies);
		
	}

	@After
	public void tearDown() throws Exception {
		bookObj = null ;
	}


	
	@Test
	public void testGetAssetId() {
		assertEquals(bookObj.getAssetId(), assetId);
	}
	
	@Test
	public void testGetBookISBN() {
		if (bookObj instanceof Book)
		{
			Book bObj = (Book) bookObj ;
			assertEquals(bObj.getBookISBN(), bookISBN);
		}
	}
	
	@Test
	public void testGetAuhtor() {
		if (bookObj instanceof Book)
		{
			Book bObj = (Book) bookObj ;
			assertEquals(bObj.getAuthor(), author);
		}
	}	
	
	@Test
	public void testGetAssetType() {
		assertEquals(bookObj.getAssetType(), assetType.BOOK);
	}	
	
	@Test
	public void testGetTitle() {
		assertEquals(bookObj.getTitle(), title);
	}	
	
	@Test
	public void testGetInitialNumberOfCopies() {
		assertEquals(bookObj.getInitialNumberOfCopies(), initalNoOfCopies);
	}	
	
	@Test
	public void testGetRemainingNumberOfCopies() {
		assertEquals(bookObj.getRemainingNumberOfCopies(), remainingNoOfCopies);
	}	
	
	
	@Test
	public void testSetTitle() {
		bookObj.setTitle("New Title");
		assertEquals(bookObj.getTitle(), "New Title");
	}	
	
	@Test
	public void testSetInitialNumberOfCopies() {
		Long inc = 200L;
		bookObj.setInitialNumberOfCopies(inc);
		assertEquals(bookObj.getInitialNumberOfCopies(), inc);
	}	
	
	@Test
	public void testsetRemainingNumberOfCopies() {
		Long rnc = 34L ;
		bookObj.setRemainingNumberOfCopies(rnc);
		assertEquals(bookObj.getRemainingNumberOfCopies(), rnc);
	}	
	

}
