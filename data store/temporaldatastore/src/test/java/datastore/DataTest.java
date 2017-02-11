package datastore;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataTest {

	Data data ;
	
	@Before
	public void setUp() throws Exception {
		data = new Data();
	}

	@After
	public void tearDown() throws Exception {
		data = null ;
	}

	@Test
	public void testCreateData() {
		int id = 0 ; 
		long timeStamp = 100 ;
		String observation = "1.5" ;
		
		String result = data.createData(id, timeStamp, observation);
		assertEquals(result, "OK 1.5");
	}

	@Test
	public void testUpdateData() {
		int id = 0 ; 
		long timeStamp = 100 ;
		String observation = "1.5" ;
		
		String result = data.createData(id, timeStamp, observation);
		assertEquals(result, "OK 1.5");
		
		//Update case 
		timeStamp = 105 ;
		observation = "1.6" ;
		result = data.updateData(id, timeStamp, observation);
		assertEquals(result, "OK 1.5");
	}
	
	@Test
	public void testComplexUpdateData() {
		int id = 0 ; 
		long timeStamp = 100 ;
		String observation = "1.5" ;
		
		String result = data.createData(id, timeStamp, observation);
		assertEquals(result, "OK 1.5");
		
		id = 1 ; 
		timeStamp = 110 ;
		observation = "1.6" ;
		
		result = data.createData(id, timeStamp, observation);
		assertEquals(result, "OK 1.6");
		
		//Update case 
		id = 0;
		timeStamp = 105 ;
		observation = "1.6" ;
		result = data.updateData(id, timeStamp, observation);
		assertEquals(result, "OK 1.5");
		
		id = 1 ;
		timeStamp = 125 ;
		observation = "1.6" ;
		result = data.updateData(id, timeStamp, observation);
		assertEquals(result, "OK 1.6");
	}
	
	
	@Test
	public void testAllCommands(){
		//CREATE 0 100 1.5
		//OK 1.5
		int id = 0 ; 
		long timeStamp = 100 ;
		String observation = "1.5" ;
		
		String result = data.createData(id, timeStamp, observation);
		assertEquals(result, "OK 1.5");
		
		//UPDATE 0 105 1.6
		//OK 1.5
		id = 0;
		timeStamp = 105 ;
		observation = "1.6" ;
		result = data.updateData(id, timeStamp, observation);
		assertEquals(result, "OK 1.5");
		
		//GET 0 100
		//OK 1.5
		id = 0;
		timeStamp = 100 ;
		
		result = data.getData(id, timeStamp);
		assertEquals(result, "OK 1.5");	
		
		//GET 0 110
		//OK 1.6
		id = 0;
		timeStamp = 110 ;
		
		result = data.getData(id, timeStamp);
		assertEquals(result, "OK 1.6");	
		
		//LATEST 0 
		//OK 105 1.6
		id = 0;
				
		result = data.getLatest(id);
		assertEquals(result, "OK 105 1.6");
		
		
		//LATEST 1
		//ERR No history exists for identifier '1'
		id = 1;
		
		result = data.getLatest(id);
		System.out.println(result );
		assertEquals(result, "ERR No history exists for identifier '1'");
		
		//CREATE 1 110 2.5
		//OK 2.5
		id = 1 ; 
		timeStamp = 110 ;
		observation = "2.5" ;
		
		result = data.createData(id, timeStamp, observation);
		assertEquals(result, "OK 2.5");
		
		//CREATE 1 115 2.4
		//ERR A history already exists for identifier '1'
		
		id = 1 ; 
		timeStamp = 115 ;
		observation = "2.4" ;
		
		result = data.createData(id, timeStamp, observation);
		assertEquals(result, "ERR A history already exists for identifier '1'");
		
		//UPDATE 1 115 2.4
		//OK 2.5
		id = 1;
		timeStamp = 115 ;
		observation = "2.4" ;
		result = data.updateData(id, timeStamp, observation);
		assertEquals(result, "OK 2.5");
		
		//UPDATE 1 120 2.3
		//OK 2.4
		id = 1;
		timeStamp = 120 ;
		observation = "2.3" ;
		result = data.updateData(id, timeStamp, observation);
		assertEquals(result, "OK 2.4");
		
		//UPDATE 1 125 2.2
		//OK 2.3
		
		id = 1;
		timeStamp = 125 ;
		observation = "2.2" ;
		result = data.updateData(id, timeStamp, observation);
		assertEquals(result, "OK 2.3");
		
		//LATEST 1 
		//OK 125 2.2
		id = 1;
		
		result = data.getLatest(id);
		assertEquals(result, "OK 125 2.2");
		
		
		//GET 1 120
		//OK 2.3
		id = 1;
		timeStamp = 120 ;
		
		result = data.getData(id, timeStamp);
		assertEquals(result, "OK 2.3");
		
		//UPDATE 1 120 2.35
		//OK 2.3
		id = 1;
		timeStamp = 120 ;
		observation = "2.35" ;
		result = data.updateData(id, timeStamp, observation);
		assertEquals(result, "OK 2.3");
		
		//GET 1 122
		//OK 2.35
		id = 1;
		timeStamp = 122 ;
		
		result = data.getData(id, timeStamp);
		assertEquals(result, "OK 2.35");
		
		
		//DELETE 1 122
		//OK 2.35
		id = 1;
		timeStamp = 122 ;
		
		result = data.deleteData(id, timeStamp);
		assertEquals(result, "OK 2.35");
		
		
		//GET 1 125
		//OK 2.35
		id = 1;
		timeStamp = 125 ;
		
		result = data.getData(id, timeStamp);
		assertEquals(result, "OK 2.35");
		
		//DELETE 1
		//OK 2.35
		id = 1;
		timeStamp = -1 ;
		
		result = data.deleteData(id, timeStamp);
		assertEquals(result, "OK 2.35");
	}
}
