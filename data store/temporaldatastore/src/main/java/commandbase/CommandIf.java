package commandbase;

public interface CommandIf {
	   String execute(int id, long timeStamp, String observation);
	   boolean validateParameters(int id, long timeStamp, String observation);
	   public boolean validateParameters(int id, long timeStamp, boolean isTimeStampPresent) ;
	   
	   public static final int CREATE_UPDATE_COMMAND_ARGS = 4;
	   public static final int DELETE_GET_COMMAND_ARGS = 2;
	   public static final int DELETE_COMMAND_WITH_TIMESTAMP_ARGS = 2;
	   public static final int LATEST_COMMAND_ARGS = 1;
	   public static final int COMMAND_ARGS = 1;
	   public static final long TIMESTAMP_INVALID_PARAM = -1;
	   
}
