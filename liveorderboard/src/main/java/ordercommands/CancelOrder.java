package ordercommands;

import java.util.Set;

import order.OrderAttributes;
import order.OrderType;
import instrument.SilverBarOrder;
import datastore.Data;

public class CancelOrder implements OrderOperations {
	private Data data ;

	public CancelOrder(Data data) {
		this.data = data ;
	}

	public boolean  execute(int userId) {
		return data.cancelOrder(userId) ;
	}

	public boolean execute(SilverBarOrder silverBarOrder) 	 {		return false;	}
	public Set<OrderAttributes> execute(OrderType orderType) {		return null;	}

}
