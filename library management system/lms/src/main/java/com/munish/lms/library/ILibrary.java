package com.munish.lms.library;

import java.util.List;

import com.munish.lms.libraryassets.Item;
import com.munish.lms.myexception.BBException;

public interface ILibrary {

	// items are loaned out for a period of one week.
	// For example, a customer can borrow WarGames on 21st February and they will be expected to return it 
	// by 28th February.
	public boolean borrowBook(Long bookId , Long userId) throws BBException;		
	public boolean returnBook(Long bookId , Long userId) throws BBException;
	
/*	Determine current inventory - this should show you the current items that are loanable. 
	You should make allowances for multiple copies of the same item (i.e. there can be multiple 
	copies of the same book/movie). 
	For example, if you choose to use the initial inventory, the current inventory should return 
    the titles. */
	public List<Item> getInitialInventory() ;
	public List<String> getInitialInventoryReturnTitles(); 
	public List<Item> getCurrentInventory() ;
	public List<String> getCurrentInventoryReturnTitles();

/*	 Determine overdue items. i.e. all items that should have been returned before today.
 	 For example, if a book was due on 12th February and today is 15th February, that book 
 	 should be flagged as overdue. */
	public List<Long> getAllOverdueItems();
	
	//Determine the borrowed items for a user.
	public List<Long> getUserBorrowedItems(Long userId) ;


}
