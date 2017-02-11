package com.munish.lms.library;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.munish.lms.Transctions;
import com.munish.lms.datasourceinteractionlayer.DataInteractionLayer;
import com.munish.lms.library.ILibrary;
import com.munish.lms.library.Library;
import com.munish.lms.libraryassets.Book;
import com.munish.lms.libraryassets.Item;
import com.munish.lms.libraryusers.LibUsers;
import com.munish.lms.libraryusers.Member;
import com.munish.lms.libraryusers.LibUsers.associationType;

public class LibraryTest {
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm");
	protected Item  bookObj1 = null  ;
	protected Item  bookObj2 = null  ;
	protected Item  bookObj3 = null  ;
	
	protected LibUsers userObj1 = null ; 
	protected LibUsers userObj2 = null ;
	protected Transctions  tr1 = null ;
	protected Transctions  tr2 = null ; 
	protected Transctions  tr3 = null ;
	protected Transctions  tr4 = null ;
	protected Transctions  tr5 = null ;
	
	

	@Before
	public void setUp() throws Exception {
		bookObj1  = new Book (1L, "The Art Of Computer Prog.", "author2", "22222", 10L, 10L ); 
		bookObj2  = new Book (4L, "Introduction to Algorithms ", "author6", "6666", 10L, 10L );
		bookObj3  = new Book (9L, "The Imitation Game ", "author8", "8888", 10L, 10L );
		userObj1 = new Member("Munish" ,"Jalota", "TW3", associationType.MEMBER);
		userObj2 = new Member("Munish1" ,"Jalota1", "TW2", associationType.MEMBER);
		tr1 = new Transctions(1234L, 7L);
		tr2 = new Transctions(9111L, 6L);
		tr3 = new Transctions(9111L, 4L);
		tr4 = new Transctions(1234L, 5L);
		tr5 = new Transctions(1234L, 6L);
		
		HashMap<Long , Item> inventrymap = DataInteractionLayer.getLibraryinventory();
		HashMap<Long , LibUsers> Usersmap  = DataInteractionLayer.getLibraryusers();
		Map<Transctions, Date> TransctionTableMap = DataInteractionLayer.getUsertransctiontable();
		
		inventrymap.put(2L, bookObj1);
		inventrymap.put(6L, bookObj2);
		inventrymap.put(9L, bookObj3);

		Usersmap.put(1234L, userObj1);
		Usersmap.put(1235L, userObj2);
		
		TransctionTableMap.put(tr1, sdf.parse("2016/02/16/19/22"));
		TransctionTableMap.put(tr2, sdf.parse("2016/02/06/19/22"));
		TransctionTableMap.put(tr3, sdf.parse("2016/02/06/19/22"));
		TransctionTableMap.put(tr4, sdf.parse("2016/02/06/19/22"));
		TransctionTableMap.put(tr5, sdf.parse("2016/02/06/19/22"));
		
	}

	@After
	public void tearDown() throws Exception {
		bookObj1 = null ;
		bookObj2 = null ; 
		bookObj3 = null ; 
		userObj1 = null ; 
		userObj2 = null ;
		tr1 = null ;
		tr2 = null ; 
		tr3 = null ;
		tr4 = null ;
		tr5 = null ;
		}

	@Test
	public void testBorrowBook() throws Exception {
		ILibrary l = new Library();
		assertTrue(l.borrowBook(2L, 1234L)) ;
	}

	@Test
	public void testReturnBook() throws Exception {
		ILibrary l = new Library();
		assertTrue(l.returnBook(2L, 1234L)) ;
	}

	@Test
	public void testGetInitialInventory() throws Exception {
		ILibrary l = new Library();
		List <Item> list = l.getInitialInventory() ;
		assertTrue(list.size() >= 3);
	}
	
	@Test
	public void testGetInitialInventoryReturnTitles() throws Exception {
		ILibrary l = new Library();
		List <String> list = l.getInitialInventoryReturnTitles() ;
		assertTrue(list.size() >= 3);
	}
	
	@Test
	public void testGetCurrentInventory() throws Exception {
		ILibrary l = new Library();
		List <Item> list = l.getCurrentInventory() ;
		assertTrue(list.size() >= 3);
	}
	
	@Test
	public void testGetCurrentInventoryReturnTitles() throws Exception {
		ILibrary l = new Library();
		List <String> list = l.getCurrentInventoryReturnTitles() ;
		assertTrue(list.size() >= 3);
	}
	
	@Test
	public void testGetAllOverdueItems() throws Exception {
		ILibrary l = new Library();
		List <Long> list = l.getAllOverdueItems() ;
		assertTrue(list.size() >= 5);
	}
	
	@Test
	public void testGetUserBorrowedItems() throws Exception {
		ILibrary l = new Library();
		List <Long> list = l.getUserBorrowedItems(1234L) ;
		assertTrue(list.size() >= 3);
	}
}
