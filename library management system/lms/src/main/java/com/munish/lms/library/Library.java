package com.munish.lms.library;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.munish.lms.Transctions;
import com.munish.lms.borrowingrule.BorrowingRule;
import com.munish.lms.datasourceinteractionlayer.DataInteractionLayer;
import com.munish.lms.libraryassets.Item;
import com.munish.lms.libraryusers.LibUsers;
import com.munish.lms.myexception.BBException;
import com.munish.lms.utils.Utils;

public class Library implements ILibrary{
	private static Logger logger = Logger.getLogger(Library.class);
	DataInteractionLayer dataInteractionlayer ;
	
	public Library() {
		this.initilize();	
	}
	
	public void initilize() {
		dataInteractionlayer = new DataInteractionLayer();

		try {
			dataInteractionlayer.initilizeDataSource();
		} catch (BBException | IOException | ParseException e) {
			logger.info("Can not initilize library interafaces/ data interaction layer" + e.getMessage());
		}
	}

	@Override
	public boolean borrowBook(Long bookId , Long userId) throws BBException {		
		Item bookObj = DataInteractionLayer.getLibraryinventory().get(bookId)  ;
		LibUsers userObj = DataInteractionLayer.getLibraryusers().get(userId) ;

		if ( (bookObj== null ) || (userObj== null ) )
		{
			logger.info("Either Userid = " + userId + "or Book = " + bookId + "doesn't exists in the inventory " );
			throw new BBException("Either Userid = " + userId + "or Book = " + bookId + "doesn't exists in the inventory ");
		}

		if (bookObj.getRemainingNumberOfCopies() < 1 )
		{
			logger.info("Book = " + bookId + "No more copy exists in the inventory " );
			throw new BBException("Book = " + bookId + "No more copy exists in the inventory");
		}

		synchronized(bookObj) {
			bookObj.decrementRemainingNumberOfCopies();
		}
		DataInteractionLayer.getUsertransctiontable().put(new Transctions(userId, bookId) , new Date());

		logger.info("Book Id = " + bookId + "is issued to User Id =  " + userId);
		return true;
	}
	
	@Override
	public boolean returnBook(Long bookId , Long userId) throws BBException {
		Item bookObj = DataInteractionLayer.getLibraryinventory().get(bookId)  ;
		LibUsers userObj = DataInteractionLayer.getLibraryusers().get(userId) ;

		if ( (bookObj== null ) || (userObj== null ) )
		{
			logger.info("Either Userid = " + userId + "or Book = " + bookId + "doesn't exists in the inventory " );
			throw new BBException("Either Userid = " + userId + "or Book = " + bookId + "doesn't exists in the inventory ");
		}

		synchronized(bookObj) {
			bookObj.incrementRemainingNumberOfCopies();
		}
		Transctions transctionObj = new Transctions(userId, bookId) ;
		DataInteractionLayer.getUsertransctiontable().remove(transctionObj);

		logger.info("Book Id = " + bookId + "is returned from  User Id =  " + userId);
		return true;
	}

	@Override
	public List<Item> getInitialInventory() {
		for (Map.Entry<Long , Item> entry : DataInteractionLayer.getLibraryinventory().entrySet())
		{
			Item item = entry.getValue();
			logger.info("Title = " + item.getTitle()+ " Initial copies = " + item.getInitialNumberOfCopies() );	
		}

		return new ArrayList<Item>(DataInteractionLayer.getLibraryinventory().values());		
	}

	@Override
	public List<String> getInitialInventoryReturnTitles() {
		ArrayList<String> initialInventoryList = new ArrayList<String>();

		for (Map.Entry<Long , Item> entry : DataInteractionLayer.getLibraryinventory().entrySet())
		{
			Item item = entry.getValue();
			logger.info("Title = " + item.getTitle()+ " Initial copies = " + item.getInitialNumberOfCopies() );
			initialInventoryList.add(entry.getValue().getTitle());
		}

		return initialInventoryList ;	
	}

	@Override
	public List<Item> getCurrentInventory() {
		ArrayList<Item> currentInventoryList = new ArrayList<Item>();

		for (Map.Entry<Long , Item> entry : DataInteractionLayer.getLibraryinventory().entrySet())
		{
			Item item = entry.getValue();
			if (item.getRemainingNumberOfCopies() >0)
			{
				logger.info("Title = " + item.getTitle()+ " Initial copies = " + item.getInitialNumberOfCopies() + " remaining Copies " + item.getInitialNumberOfCopies() );	
				currentInventoryList.add(entry.getValue());
			}
		}
		return currentInventoryList;		
	}

	@Override
	public List<String> getCurrentInventoryReturnTitles() {
		ArrayList<String> currentInventoryList = new ArrayList<String>();

		for (Map.Entry<Long , Item> entry : DataInteractionLayer.getLibraryinventory().entrySet())
		{
			Item item = entry.getValue();
			if (item.getRemainingNumberOfCopies() >0)
			{
				logger.info("Title = " + item.getTitle()+ " Initial copies = " + item.getInitialNumberOfCopies() + " remaining Copies " + item.getInitialNumberOfCopies() );	
				currentInventoryList.add(entry.getValue().getTitle());
			}
		}
		return currentInventoryList;		
	}

	@Override
	public List<Long> getAllOverdueItems() {
		Date todayDate = new Date();
		ArrayList<Long> overdueItems = new ArrayList<Long>();

		for (Map.Entry<Transctions, Date> entry : DataInteractionLayer.getUsertransctiontable().entrySet())
		{
			if (Utils.daysBetween(todayDate,entry.getValue()) > BorrowingRule.BOOK_RULE) 
			{
				overdueItems.add(entry.getKey().getUniqueAssetId());
			}
		}
		return overdueItems;
	}

	@Override
	public List<Long> getUserBorrowedItems(Long userId) {
		ArrayList<Long> userItems = new ArrayList<Long>();

		for (Map.Entry<Transctions, Date> entry : DataInteractionLayer.getUsertransctiontable().entrySet())
		{
			if (entry.getKey().getUniqueUserId().equals(userId)) 
			{
				userItems.add(entry.getKey().getUniqueAssetId());
			}
		}
		return userItems;
	}
}
