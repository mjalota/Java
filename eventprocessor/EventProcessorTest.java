package test1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventProcessorTest {

	public EventProcessor eventProcessor ;
	
	@Before
	public void setUp() throws Exception {
		eventProcessor = new EventProcessor();
	}

	@After
	public void tearDown() throws Exception {
		eventProcessor = null ;
	}

	@Test(expected=RuntimeException.class)
	public void testWhenInputIsEmpty() {
		int[] input = new int[]{};
		int winner = eventProcessor.getWinner(input);
	}

	@Test
	public void testgetWinnerWhenAllUnique() {
		int[] input = new int[]{1, 2, 3, 4 , 5 };
		int winner = eventProcessor.getWinner(input);
		assertTrue(winner == 1);
	}
	
	@Test
	public void testgetWinner() {
		int[] input = new int[]{1, 2, 3, 4 , 4 };
		int winner = eventProcessor.getWinner(input);
		assertTrue(winner == 4);
	}
	
	@Test
	public void testgetWinnerWhen2Winners() {
		int[] input = new int[]{1, 2, 3, 4 , 4 , 5, 6, 7, 5 };
		int winner = eventProcessor.getWinner(input);
		assertTrue(winner == 4);
	}
	
	@Test
	public void testgetWinnerWhen3Winners() {
		int[] input = new int[]{1, 2, 3, 4 , 4 , 5, 6, 7, 5 };
		int winner = eventProcessor.getWinner(input);
		assertTrue(winner == 4);
	}
	
	@Test
	public void testgetWinnerWhenOnly1TypeOfEvents() {
		int[] input = new int[]{4, 4, 4, 4, 4, 4 };
		int winner = eventProcessor.getWinner(input);
		assertTrue(winner == 4);
	}
	
	
}
