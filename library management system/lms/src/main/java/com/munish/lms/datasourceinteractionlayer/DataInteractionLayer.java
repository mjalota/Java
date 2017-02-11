package com.munish.lms.datasourceinteractionlayer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lms.munish.lms.config.Config;

import com.munish.lms.Transctions;
import com.munish.lms.datasource.DataSourceIf;
import com.munish.lms.datasourcefactory.DataSourceFactory;
import com.munish.lms.libraryassets.Item;
import com.munish.lms.libraryusers.LibUsers;
import com.munish.lms.myexception.BBException;

public class DataInteractionLayer {
	public static final HashMap<Long , Item> libraryInventory  = new LinkedHashMap<Long , Item>() ;
	public static final HashMap<Long , LibUsers> libraryUsers  = new HashMap<Long , LibUsers>() ;
	public static final Map<Transctions, Date> userTransctionTable  = new ConcurrentHashMap<Transctions, Date>() ;
	private Config cfg = null;
	
	public DataInteractionLayer() {
		
	}
	
	public void initilizeDataSource () throws BBException, IOException, ParseException
	{
		cfg = Config.instance();
		populateDataSourceCache();
	}

	public static HashMap<Long, Item> getLibraryinventory() {
		return libraryInventory;
	}

	public static HashMap<Long, LibUsers> getLibraryusers() {
		return libraryUsers;
	}

	public static Map<Transctions, Date> getUsertransctiontable() {
		return userTransctionTable;
	}

	public void populateDataSourceCache() throws IOException, ParseException
	{
		try {
			getDataSourceInventroy();
		} catch (BBException e) {
			e.printStackTrace();
		}
		try {
			getDataSourceUsers();
		} catch (BBException e) {
			e.printStackTrace();
		}
		try {
			getDataSourceTransctionTable();
		} catch (BBException e) {
			e.printStackTrace();
		}
	}

	// Book details , total copies , remaining copies
	public void getDataSourceInventroy() throws BBException, IOException
	{
		DataSourceIf db = DataSourceFactory.getDataSource(cfg.dataSourceType);
		if (db == null )
			throw new BBException("Database Type doesn't exist in the database map/table.");
		db.getInventoryTable(cfg.dataSourceRecordFile, cfg.spliter , libraryInventory);	
	}

	//Details of all users and book issues
	// user id , Book id , issue date
	public void getDataSourceUsers() throws BBException, IOException
	{
		DataSourceIf db = DataSourceFactory.getDataSource(cfg.dataSourceType);
		if (db == null )
			throw new BBException("Database Type doesn't exist in the database map/table.");
		db.getUserTable(cfg.dataSourceUserFile, cfg.spliter , libraryUsers);	

	}

	public void getDataSourceTransctionTable() throws BBException, IOException, ParseException
	{
		DataSourceIf db = DataSourceFactory.getDataSource(cfg.dataSourceType);
		if (db == null )
			throw new BBException("Database Type doesn't exist in the database map/table.");
		db.getTransctionTable(cfg.dataSourceTransctionsFile, cfg.spliter , userTransctionTable);	
	}

}
