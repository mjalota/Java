package commands;

import commandbase.CommandImpl;
import datastore.Data;

public class UpdateData extends CommandImpl {

	private Data data ;


	public UpdateData(Data d) {
		data  = d ;
	}


	public String execute( int id, long timeStamp, String observation) {
		String result = data.updateData(id, timeStamp, observation);
		return result;
	}


	@Override
	public boolean validateParameters(int id, long timeStamp,
			boolean isTimeStampPresent) {
		return false;
	}




}
