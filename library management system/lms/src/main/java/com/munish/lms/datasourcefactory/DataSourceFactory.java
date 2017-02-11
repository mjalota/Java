package com.munish.lms.datasourcefactory;

import com.munish.lms.datasource.DataSourceIf;
import com.munish.lms.datasource.DatabaseDataSource;
import com.munish.lms.datasource.FileDataSource;

public class DataSourceFactory {

	public DataSourceFactory() {
	}
	
	public static DataSourceIf getDataSource(String dbType)
	{
		if (dbType.equalsIgnoreCase(DataSourceIf.dataSourceTypes.FILE.toString()))
		{
			return new FileDataSource();
		}
		else if (dbType.equalsIgnoreCase(DataSourceIf.dataSourceTypes.DATABASE.toString()))
		{
			return  new DatabaseDataSource();
		}		
		return null  ;
	}
}
