package orderboard;

import java.util.Set;

import datastore.Data;
import instrument.SilverBarOrder;
import order.OrderAttributes;
import order.OrderType;
import ordercommands.CancelOrder;
import ordercommands.OrderOperations;
import ordercommands.RegisterOrder;

public class LiveOrderBoardImpl implements LiveOrderBoard {
	private final Data data ; 

	public LiveOrderBoardImpl(Data data ) {
		this.data = data ;
	}

	public Data getData() {
		return data;
	}

	public boolean registerAnOrder(int userId, int quantity, double price, OrderType orderType) {
		boolean result ;
		
		/*
		 * Builder pattern is added if any default attributes are added to  SilverBarOrder 
		 */
		SilverBarOrder inst = new SilverBarOrder.Builder(userId)
												.quantity(quantity)
												.price(price)
												.orderType(orderType)
												.build();
		OrderOperations o = new RegisterOrder(data);
		result = o.execute(inst);
		
		return result;
	}

	public boolean cancelAnOrder(int userId) {
		boolean result ;
		OrderOperations o = new CancelOrder(data);
		result = o.execute(userId);

		return result;
	}

	public Set<OrderAttributes> getSummary(OrderType orderType) {
		return data.getSummary(orderType);
	}
}
