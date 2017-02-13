package ordercommands;

import java.util.Set;

import instrument.SilverBarOrder;
import order.OrderAttributes;
import order.OrderType;
import datastore.Data;

public class SummaryOrder implements OrderOperations{
	private Data data ;

	public SummaryOrder(Data data) {
		this.data = data ;
	}
	
	public Set <OrderAttributes> execute(OrderType orderType) {			
		return data.getSummary(orderType) ;
	}

	public boolean execute(int userId) 					  {		return false;	}
	public boolean execute(SilverBarOrder silverBarOrder) {		return false;	}
	
	
}
