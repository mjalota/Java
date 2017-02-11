package commandparser;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CommandParserTest {

	CommandParser commandParser ;
	
	@Before
	public void setUp() throws Exception {
		commandParser = new CommandParser() ;
	}

	@After
	public void tearDown() throws Exception {
		commandParser = null ;
	}
	
	
	@Test
	public void testHandlerCreateCommand() {
		String[] arr = {"CREATE" , "10", "100", "1.5"} ;
		String result = commandParser.handlerCreateCommand(arr);
		assertEquals(result, "OK 1.5");
	}

	@Test(expected=RuntimeException.class)
	public void testHandlerCreateCommandForInvalidParam() {

		String[] arr = {"CREATE" , "-1", "100", "1.5"} ;
		commandParser.handlerCreateCommand(arr);
	}
	
	@Test
	public void testHandlerUpdateCommand() {
		String[] arr = {"CREATE" , "21", "100", "1.5"} ;
		String result = commandParser.handlerCreateCommand(arr);
		assertEquals(result, "OK 1.5");
		
		String[] arrUpdate = {"UPDATE" , "21", "105", "1.6"} ;
		result = commandParser.handlerUpdateCommand(arrUpdate);
		assertEquals(result, "OK 1.5");
	}
	
	@Test
	public void testHandlerGetCommand() {

		String[] arr = {"CREATE" , "20", "100", "1.5"} ;
		String result = commandParser.handlerCreateCommand(arr);
		assertEquals(result, "OK 1.5");
		
		String[] arrGet = {"GET" , "0", "100"} ;
		result = commandParser.handlerGetCommand(arrGet);
		assertEquals(result, "OK 1.5");
	}
	
	@Test
	public void testHandlerLatestCommand() {
		String[] arr = {"CREATE" , "0", "100", "1.5"} ;
		String result = commandParser.handlerCreateCommand(arr);
		assertEquals(result, "OK 1.5");
		
		String[] arrUpdate = {"UPDATE" , "0", "105", "1.6"} ;
		result = commandParser.handlerUpdateCommand(arrUpdate);
		assertEquals(result, "OK 1.5");
		
		String[] arrLatest = {"LATEST" , "0" } ;
		result = commandParser.handlerLatestCommand(arrLatest);
		assertEquals(result, "OK 105 1.6");
	}
}
