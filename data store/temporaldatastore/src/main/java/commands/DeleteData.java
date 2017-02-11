package commands;

import commandbase.CommandImpl;
import datastore.Data;

public class DeleteData extends CommandImpl {

	private Data data ;

	public DeleteData(Data d) {
		data  = d ;
	}

	public String execute( int id, long timeStamp, String observation) {
		String result = data.deleteData(id, timeStamp);
		return result;
	}

	public boolean validateParameters(int id, long timeStamp, boolean isTimeStampPresent) {
		if ( !(id >= 0 && id <= Integer.MAX_VALUE))
			return false;
		if ( isTimeStampPresent && !(timeStamp >= 0 && timeStamp <= Long.MAX_VALUE))
			return false;
		
		return true;
	}

	


}
