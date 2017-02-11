package com.munish.lms.datasource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.munish.lms.Transctions;
import com.munish.lms.libraryassets.Item;
import com.munish.lms.libraryusers.LibUsers;

public class DatabaseDataSource implements DataSourceIf {

	public DatabaseDataSource() {
	}


	@Override
	public void getInventoryTable(String filename, String spliter, HashMap<Long, Item> tablemap) {
	}

	@Override
	public void getUserTable(String filename, String spliter, HashMap<Long, LibUsers> tablemap) {
	}


	@Override
	public void getTransctionTable(String filename, String spliter, Map<Transctions, Date> tablemap) {

	}

}
