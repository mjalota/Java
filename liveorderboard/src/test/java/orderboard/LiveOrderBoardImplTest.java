package orderboard;

import static org.junit.Assert.*;

import java.util.Set;

import instrument.SilverBarOrder;
import order.OrderAttributes;
import order.OrderType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastore.Data;

public class LiveOrderBoardImplTest {

	Data data ;
	LiveOrderBoard liveOrderBoard ;
	
	@Before
	public void setUp() throws Exception {
		data = new Data();
		liveOrderBoard = new LiveOrderBoardImpl(data);
	}

	@After
	public void tearDown() throws Exception {
		data.clearCache();
		data = null ;
		liveOrderBoard = null; 
	}

	@Test
	public void testRegisterAnOrder() {
		boolean result = false; 
		
		result = liveOrderBoard.registerAnOrder(11, 200, 20.20, OrderType.BUY);
		assertTrue(result == true);
		result = liveOrderBoard.registerAnOrder(12, 100, 20.30, OrderType.SELL);
		assertTrue(result == true);
	}

	
	@Test(expected=RuntimeException.class)
	public void testRegisterAnOrderWithDuplicateUserId() {
		boolean result = false; 
		
		result = liveOrderBoard.registerAnOrder(11, 200, 20.20, OrderType.BUY);
		assertTrue(result == true);
		result = liveOrderBoard.registerAnOrder(11, 100, 20.30, OrderType.SELL);
	}
	
	@Test
	public void testCancelAnOrder() {
		boolean result = false; 
		
		result = liveOrderBoard.registerAnOrder(11, 200, 20.20, OrderType.BUY);
		assertTrue(result == true);
		result = liveOrderBoard.registerAnOrder(12, 100, 20.30, OrderType.SELL);
		assertTrue(result == true);
		
		int userId = 11 ;
		result = liveOrderBoard.cancelAnOrder(userId );
		assertTrue(result == true);
	}

	@Test(expected=RuntimeException.class)
	public void testCancelAnOrderWithInvalidUserId() {
		boolean result = false; 
		
		result = liveOrderBoard.registerAnOrder(11, 200, 20.20, OrderType.BUY);
		assertTrue(result == true);
		result = liveOrderBoard.registerAnOrder(12, 100, 20.30, OrderType.SELL);
		assertTrue(result == true);
		
		int userId = 13 ;
		result = liveOrderBoard.cancelAnOrder(userId );
	}

	
	@Test
	public void testGetSummaryForBuyOrder() {
		boolean result = false ;
		
		result = liveOrderBoard.registerAnOrder(21, 120, 20, OrderType.BUY);
		assertTrue(result == true);
		result = liveOrderBoard.registerAnOrder(22, 125, 20.30, OrderType.BUY);
		assertTrue(result == true);
		result = liveOrderBoard.registerAnOrder(23, 140, 20, OrderType.BUY);
		assertTrue(result == true);
		result = liveOrderBoard.registerAnOrder(24, 165, 20.30, OrderType.BUY);
		assertTrue(result == true);
		
		Set<OrderAttributes>  result1 = liveOrderBoard.getSummary(OrderType.BUY);
		for (OrderAttributes o : result1){
			System.out.println(o.getQuantity() + "  £" + o.getPrice());
		}
		assertTrue(result1.size() == 2);
	}
	
	@Test
	public void testGetSummaryForSellOrder() {
		boolean result = false ;
		
		result = liveOrderBoard.registerAnOrder(11, 100, 20, OrderType.SELL);
		assertTrue(result == true);
		result = liveOrderBoard.registerAnOrder(12, 100, 20.30, OrderType.SELL);
		assertTrue(result == true);
		result = liveOrderBoard.registerAnOrder(13, 100, 20, OrderType.SELL);
		assertTrue(result == true);
		result = liveOrderBoard.registerAnOrder(14, 100, 20.30, OrderType.SELL);
		assertTrue(result == true);
		
		Set<OrderAttributes>  result1 = liveOrderBoard.getSummary(OrderType.SELL);
		for (OrderAttributes o : result1){
			System.out.println(o.getQuantity() + "  £" + o.getPrice());
		}
		assertTrue(result1.size() == 2);
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetSummaryForNoneOrder() {
		liveOrderBoard.getSummary(OrderType.SELL);
	}
	
}
