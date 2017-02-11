package commands;

import commandbase.CommandImpl;

import datastore.Data;

public class CreateData extends CommandImpl{
	private Data data ;
	
	public CreateData(Data d) {
		data  = d;
	}

	@Override
	public String execute( int id, long timeStamp, String observation) {
		String result = data.createData(id, timeStamp, observation);
		return result;
	}

	@Override
	public boolean validateParameters(int id, long timeStamp,
			boolean isTimeStampPresent) {
		return false;
	}


}
