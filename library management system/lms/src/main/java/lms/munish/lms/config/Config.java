package lms.munish.lms.config;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.munish.lms.utils.Utils;

public class Config {
	private static Logger logger = Logger.getLogger(Config.class);

	static private Config _instance = null;
	public  String dataSourceType  = null;
	public  String dataSourceRecordFile = null;
	public  String spliter = null;
	public  String dataSourceUserFile = null;
	public  String dataSourceTransctionsFile = null;
	
     protected Config() {
        try{
    		Properties prop = Utils.loadProperties("config.properties");
    		this.dataSourceType = prop.getProperty("datasource"); 
    		if (dataSourceType != null && dataSourceType.equals("file"))
    		{
    			this.dataSourceRecordFile = prop.getProperty("datasource.record.file.name");
    			this.spliter = prop.getProperty("datasource.file.spliter") ;
    			this.dataSourceUserFile = prop.getProperty("datasource.user.file.name") ; 
    			this.dataSourceTransctionsFile = prop.getProperty("datasource.transction.file.name") ;
    		}
    		else if (dataSourceType != null && dataSourceType.equals("database"))
    		{
    			//read DB connection details 
    		}
        } catch(Exception e){
            System.out.println("error" + e);
            logger.info("Exceptiojn while reading Config file = " + e.getMessage() );
        }
 
    }
 
    static public Config instance() {
        if (_instance == null) {
            _instance = new Config();
        }
        return _instance;
    }
    
}
