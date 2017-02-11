package com.munish.lms.datasource;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.munish.lms.Transctions;
import com.munish.lms.libraryassets.Item;
import com.munish.lms.libraryusers.LibUsers;

public interface DataSourceIf 
{
	public void getInventoryTable(final String filename , final String spliter, HashMap<Long , Item> tablemap) throws IOException;
	public void getUserTable(final String filename , final String spliter, HashMap<Long , LibUsers> tablemap) throws IOException;
	public void getTransctionTable(final String filename , final String spliter,	Map<Transctions, Date> tablemap) throws IOException, ParseException;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm");

	public enum dataSourceTypes{
		FILE,
		DATABASE,
		HTTP_LINK,
		NONE
	}
}
