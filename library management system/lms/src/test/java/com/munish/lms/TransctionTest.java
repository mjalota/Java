package com.munish.lms;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.munish.lms.Transctions;

public class TransctionTest {

	protected Long userId = 123l ;
	protected Long newUserId = 124l ;
	protected Long assetId = 125l ;
	protected Long newAssetId = 124l ;
	protected Transctions tObj = null ;

	
	@Before
	public void setUp() throws Exception {
		tObj =  new Transctions(userId, assetId);
	}

	@After
	public void tearDown() throws Exception {
		tObj = null ; 
	}

	@Test
	public void testUniqueUserId() throws Exception {
		assertEquals(tObj.getUniqueUserId(), userId);
		
		tObj.setUniqueUserId(newUserId);
		assertEquals(tObj.getUniqueUserId(), newUserId);
	}
	
	@Test
	public void testuniqueAssetId() throws Exception {
		assertEquals(tObj.getUniqueAssetId(), assetId);

		tObj.setUniqueAssetId(newAssetId);
		assertEquals(tObj.getUniqueAssetId(), newAssetId);
	}

}
