package commands;

import commandbase.CommandImpl;
import datastore.Data;

public class GetData extends CommandImpl {

	private Data data ;
	public GetData(Data d) {
		data = d ;
	}

	@Override
	public String execute(int id, long timeStamp, String observation) {
		String result = data.getData(id, timeStamp);
		return result;
	}

	public boolean validateParameters(int id, long timeStamp, String observation) {
		if ( !(id >= 0 && id <= Integer.MAX_VALUE))
			return false;
		if ( !(timeStamp >= 0 && timeStamp <= Long.MAX_VALUE))
			return false;
		
		return true;
	}
	
	@Override
	public boolean validateParameters(int id, long timeStamp,
			boolean isTimeStampPresent) {
		return false;
	}

}
