package com.munish.lms.datasource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.munish.lms.Transctions;
import com.munish.lms.libraryassets.Book;
import com.munish.lms.libraryassets.DVD;
import com.munish.lms.libraryassets.Item;
import com.munish.lms.libraryassets.VHS;
import com.munish.lms.libraryassets.Asset.assetType;
import com.munish.lms.libraryusers.LibUsers;
import com.munish.lms.libraryusers.Member;
import com.munish.lms.libraryusers.LibUsers.associationType;

public class FileDataSource implements DataSourceIf {
	private static Logger logger = Logger.getLogger(FileDataSource.class);


	public FileDataSource() {
	}

	//UniqueID,Asset ID,Type,Title,author,bookISBN, InitialnumberOfCopies, emainingnumberOfCopies
	public Book getBookObject(String[] strline) 
	{
		return  new Book(Long.parseLong(strline[1].trim()),
				strline[3].trim(),
				strline[4].trim(),
				strline[5].trim(),
				Long.parseLong(strline[6].trim()),
				Long.parseLong(strline[7].trim()));
	}

	public DVD getDVDObject(String[] strline)
	{
		return  new DVD(Long.parseLong(strline[1].trim()),
				strline[3].trim(),
				strline[4].trim(),
				strline[5].trim(),
				Long.parseLong(strline[6].trim()),
				Long.parseLong(strline[7].trim()));		
	}

	public VHS getVHSObject(String[] strline) 
	{
		return  new VHS(Long.parseLong(strline[1].trim()),
				strline[3].trim(),
				strline[4].trim(),
				strline[5].trim(),
				Long.parseLong(strline[6].trim()),
				Long.parseLong(strline[7].trim()));			
	}

	//#UserType , UniqueUserAssocId , firstname , lastname , postcode 
	public Member getMemberObject(String[] userLine) {
		return  new Member( userLine[2].trim(),
				userLine[3].trim(),
				userLine[4].trim(),
				associationType.valueOf(userLine[0].trim().toUpperCase()));	
	}

	//#UniqueID, Asset ID, Type , Title                          ,author   , bookISBN , InitialnumberOfCopies, remainingnumberOfCopies
	@Override
	public void getInventoryTable(final String filename , 
			final String spliter,
			HashMap<Long , Item> tablemap) throws IOException
	{
		BufferedReader br = null;
		String line = "";

		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				if (line.charAt(0) == '#')
					continue;

				String[] bookline = line.split(spliter.trim());
				Long inventoryID = Long.parseLong(bookline[0].trim());

				if (bookline[2].trim().equalsIgnoreCase(assetType.BOOK.toString()))
				{
					Book b = getBookObject(bookline);
					tablemap.put(inventoryID, b);
				}
				else if (bookline[2].trim().equalsIgnoreCase(assetType.DVD.toString()))
				{
					DVD b = getDVDObject(bookline);
					tablemap.put(inventoryID, b);
				}
				else if (bookline[2].trim().equalsIgnoreCase(assetType.VHS.toString()))
				{
					VHS b = getVHSObject(bookline);
					tablemap.put(inventoryID, b);
				}
			}
		} catch (FileNotFoundException e) {
			logger.info("File not found to load  Inventory table " + e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.info("IO exception occured during  Inventory table " + e.getMessage());
			throw e; 
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.info("IO exception occured during close Inventory table " + e.getMessage());
					throw e;
				}
			}
		}
	}

	//#UserType , UniqueUserAssocId , firstname , lastname , postcode 
	@Override
	public void getUserTable(final String filename , 
			final String spliter,
			HashMap<Long , LibUsers> tablemap) throws IOException
	{
		BufferedReader br = null;
		String line = "";

		try {
			br = new BufferedReader(new FileReader(filename));

			while ((line = br.readLine()) != null) {
				if (line.charAt(0) == '#')
					continue;
				String[] userLine = line.split(spliter);
				tablemap.put(Long.parseLong(userLine[1].trim()), getMemberObject(userLine));
			}
		} catch (FileNotFoundException e) {
			logger.info("file not found suring get user table " + e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.info("IO exception occured during get user table " + e.getMessage());
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.info("IO exception occured during close user table " + e.getMessage());
					throw e;
				}
			}
		}
	}

	//#UniqueUserAssocId , UniqueAssetId , issuedate 
	@Override
	public void getTransctionTable(final String filename , 
			final String spliter,
			Map<Transctions, Date> tablemap) throws IOException, ParseException
	{
		BufferedReader br = null;
		String line = "";
		Transctions transctionObj = null ;

		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				if (line.charAt(0) == '#')
					continue;
				String[] userLine = line.split(spliter);
				transctionObj = new Transctions(Long.parseLong(userLine[0].trim()), 
						Long.parseLong(userLine[1].trim()));

				try {
					tablemap.put(transctionObj, sdf.parse(userLine[2].trim()));
				} catch (ParseException e) {
					logger.info("Invalid transction and could not parse User Id = " + Long.parseLong(userLine[0]));
					throw e;
				}
			}
		} catch (FileNotFoundException e) {
			logger.info("file not found during get transction table " + e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.info("IO exception occured during get transction table " + e.getMessage());
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.info("IO exception occured during close transction table " + e.getMessage());
					throw e;
				}
			}
		}
	}

}
