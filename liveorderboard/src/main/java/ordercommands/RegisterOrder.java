package ordercommands;

import java.util.Set;

import order.OrderAttributes;
import order.OrderType;
import datastore.Data;
import instrument.SilverBarOrder;

public class RegisterOrder implements OrderOperations {
	private Data data ;

	public RegisterOrder(Data data ) {
		this.data = data ;
	}

	public boolean  execute(SilverBarOrder silverBarOrder) {
		return data.registerOrder(silverBarOrder) ;
	}

	public boolean execute(int userId) 						 {		return false;	}
	public Set<OrderAttributes> execute(OrderType orderType) {		return null;	}

}
