package datastore;

import commandbase.CommandIf;
import commandparser.CommandParser;

public class TemporalDataStore {
	public static void main(String[] args) {
		if (args.length < CommandIf.COMMAND_ARGS ) {
			throw new RuntimeException("No Command Supplied !");
		}
		
		CommandParser commandParser = new CommandParser();
		commandParser.parseCommand(args);

	}
}
