package commands;

import commandbase.CommandImpl;
import datastore.Data;

public class LatestData extends CommandImpl {
	private Data data ;

	public LatestData(Data d) {
		data = d ;
	}

	@Override
	public String execute(int id, long timeStamp, String observation) {
		String result =data.getLatest(id);
		return result;
	}

	public boolean validateParameters(int id, long timeStamp, String observation) {
		if ( !(id >= 0 && id <= Integer.MAX_VALUE))
			return false;

		return true;
	}
	
	@Override
	public boolean validateParameters(int id, long timeStamp,
			boolean isTimeStampPresent) {
		return false;
	}

}
