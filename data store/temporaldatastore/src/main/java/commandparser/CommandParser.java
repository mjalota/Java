package commandparser;

import commandbase.CommandIf;
import commandbase.CommandImpl;
import commandbase.Commands;
import commands.CreateData;
import commands.DeleteData;
import commands.GetData;
import commands.LatestData;
import commands.UpdateData;
import datastore.Data;

public class CommandParser {
	private static final Data data = new Data() ;

	public String handlerCreateCommand(String[] createCommandArgs) {
		String result = "" ;
		if (createCommandArgs.length < CommandIf.CREATE_UPDATE_COMMAND_ARGS ) {
			throw new RuntimeException("Invalid Command Arguments : USAGE CREATE <id><timestamp><data> ");
		}
		int id = Integer.valueOf(createCommandArgs[1]) ;
		long timeStamp = Long.valueOf(createCommandArgs[2]);
		String observation = createCommandArgs[3] ;

		CommandImpl commandImpl = new CreateData(data);
		if ( commandImpl.validateParameters(id, timeStamp, observation)){
			result = commandImpl.execute(id, timeStamp, observation);
		}else {
			throw new RuntimeException("Invalid Data: Either supplied ID is negative or supplied data got invalid char <space>");
		}
		return result ;
	}

	public String handlerUpdateCommand(String[] updateCommandArgs) {
		String result = "" ;
		if (updateCommandArgs.length < CommandIf.CREATE_UPDATE_COMMAND_ARGS ) {
			throw new RuntimeException("Invalid Command Arguments : USAGE UPDATE <id><timestamp><data> ");
		}
		int id = Integer.valueOf(updateCommandArgs[1]) ;
		long timeStamp = Long.valueOf(updateCommandArgs[2]);
		String observation = updateCommandArgs[3] ;

		CommandImpl commandImpl = new UpdateData(data);
		if ( commandImpl.validateParameters(id, timeStamp, observation)){
			result = commandImpl.execute(id, timeStamp, observation);
		}else {
			throw new RuntimeException("Invalid Data: Either supplied ID is negative or supplied data got invalid char <space>");
		}
		return result ;
	}

	public String handlerDeleteCommand(String[] deleteCommandArgs) {
		String result = "" ;
		if (deleteCommandArgs.length < CommandIf.DELETE_GET_COMMAND_ARGS ) {
			throw new RuntimeException("Invalid Command Arguments : USAGE DELETE <id> [timestamp] ");
		}
		int id = Integer.valueOf(deleteCommandArgs[1]) ;
		long timeStamp = -1 ;
		boolean isTimeStampPresent = false ;
		if (deleteCommandArgs.length > CommandIf.DELETE_COMMAND_WITH_TIMESTAMP_ARGS) {
			timeStamp = Long.valueOf(deleteCommandArgs[2]);
			isTimeStampPresent = true ;
		}

		CommandImpl commandImpl = new DeleteData(data);
		if ( commandImpl.validateParameters(id, timeStamp , isTimeStampPresent)){
			result = commandImpl.execute(id, timeStamp, null);
		} else {
			throw new RuntimeException("Invalid Data: Either supplied ID is negative or supplied data got invalid char <space>");
		}
		return result ;
	}

	public String handlerGetCommand(String[] getCommandArgs) {
		String result = "" ;
		if (getCommandArgs.length < CommandIf.DELETE_GET_COMMAND_ARGS ) {
			throw new RuntimeException("Invalid Command Arguments : USAGE GET <id> <timestamp> ");
		}
		int id = Integer.valueOf(getCommandArgs[1]) ;
		long timeStamp = Long.valueOf(getCommandArgs[2]);

		CommandImpl commandImpl = new GetData(data);
		if ( commandImpl.validateParameters(id, timeStamp, null)){
			result = commandImpl.execute(id, timeStamp, null);
		}else {
			throw new RuntimeException("Invalid Data: Either supplied ID is negative or supplied data got invalid char <space>");
		}
		return result ;
	}

	public String handlerLatestCommand(String[] latestCommandArgs) {
		String result = "" ;
		if (latestCommandArgs.length < CommandIf.LATEST_COMMAND_ARGS ) {
			throw new RuntimeException("Invalid Command Arguments : LATEST <id> ");
		}
		int id = Integer.valueOf(latestCommandArgs[1]) ;

		CommandImpl commandImpl = new LatestData(data);
		if ( commandImpl.validateParameters(id, CommandIf.TIMESTAMP_INVALID_PARAM, null)){
			result = commandImpl.execute(id, CommandIf.TIMESTAMP_INVALID_PARAM, null);
		}else {
			throw new RuntimeException("Invalid Data: Either supplied ID is negative or supplied data got invalid char <space>");
		}
		return result ;
	}

	public void parseCommand(String [] commandArgs){
		String result = "" ; 

		while (true){
			try {
				switch(Commands.valueOf(commandArgs[0]))
				{
				case CREATE:
					result = handlerCreateCommand(commandArgs);
					break ;

				case UPDATE:
					result = handlerUpdateCommand(commandArgs);
					break ;

				case DELETE:
					result = handlerDeleteCommand(commandArgs);
					break ;

				case GET:
					result = handlerGetCommand(commandArgs);
					break ;

				case LATEST:
					result = handlerLatestCommand(commandArgs);
					break ;

				case QUIT:
					return ;
				}
			} catch (Exception e ){
				result = e.getMessage();
			}
			System.out.println(result);
		}
	}

}
