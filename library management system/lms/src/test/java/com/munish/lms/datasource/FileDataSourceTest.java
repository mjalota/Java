package com.munish.lms.datasource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.munish.lms.Transctions;
import com.munish.lms.datasource.FileDataSource;
import com.munish.lms.libraryassets.Book;
import com.munish.lms.libraryassets.DVD;
import com.munish.lms.libraryassets.Item;
import com.munish.lms.libraryassets.VHS;
import com.munish.lms.libraryusers.LibUsers;
import com.munish.lms.libraryusers.Member;
import com.munish.lms.libraryusers.LibUsers.associationType;

public class FileDataSourceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetBookObject() {
		FileDataSource fs = mock(FileDataSource.class) ;
		String[] strline = null;
		Book bObj = new Book (1L, "The Art Of Computer Prog.", "author2", "22222", 10L, 10L ); 
		when (fs.getBookObject(strline)).thenReturn(bObj);
		
		assertEquals(fs.getBookObject(strline), bObj);
	}

	@Test
	public void testGetDVDObject() {
		FileDataSource fs = mock(FileDataSource.class) ;
		String[] strline = null;
		DVD dObj = new DVD(2L, "Title1", "Writer1", "recordNumber1", 10L, 9L);
		when (fs.getDVDObject(strline)).thenReturn(dObj);
		
		assertEquals(fs.getDVDObject(strline), dObj);
	}
	
	@Test
	public void testGetVHSObject() {
		FileDataSource fs = mock(FileDataSource.class) ;
		String[] strline = null;
		VHS vObj = new VHS(9L, "The Imitation Game ", "author8", "8888", 10L, 10L );
		when (fs.getVHSObject(strline)).thenReturn(vObj);
		
		assertEquals(fs.getVHSObject(strline), vObj);
	}
	
	@Test
	public void testGetMemberObject() {
		FileDataSource fs = mock(FileDataSource.class) ;
		String[] strline = null;
		Member mObj = new Member("Munish", "Jalota", "TW3", associationType.MEMBER);
		when (fs.getMemberObject(strline)).thenReturn(mObj);
		
		assertEquals(fs.getMemberObject(strline), mObj);
	}
	
	@Test
	public void testGetInventoryTable() throws IOException {
		FileDataSource fs = mock(FileDataSource.class) ;
		String a = "abc" ; 
		String b = "bbc" ;
		HashMap<Long , Item>  testMap = null ; 
		doThrow(IOException.class).when(fs).getInventoryTable( a ,  b, testMap);
	}
	
	@Test
	public void testGetUserTable() throws IOException {
		FileDataSource fs = mock(FileDataSource.class) ;
		String a = "abc" ; 
		String b = "bbc" ;
		HashMap<Long , LibUsers>  testMap = null ; 
		doThrow(IOException.class).when(fs).getUserTable( a ,  b, testMap);
	}
	
	@Test
	public void testGetTransctionTable() throws IOException, ParseException {
		FileDataSource fs = mock(FileDataSource.class) ;
		String a = "abc" ; 
		String b = "bbc" ;
		HashMap<Transctions, Date>  testMap = null ; 
		doThrow(IOException.class).when(fs).getTransctionTable( a ,  b, testMap);
	}
	
}
