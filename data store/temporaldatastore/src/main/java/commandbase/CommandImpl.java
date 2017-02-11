package commandbase;



public abstract class CommandImpl implements CommandIf{

	public boolean validateParameters(int id, long timeStamp, String observation) {
		if ( !(id >= 0 && id <= Integer.MAX_VALUE))
			return false;
		if ( !(timeStamp >= 0 && timeStamp <= Long.MAX_VALUE))
			return false;
		if (observation.split(" ").length > 1 )
			return false ;
		
		return true;
	}


}
