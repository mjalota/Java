package ordercommands;

import java.util.Set;

import order.OrderAttributes;
import order.OrderType;
import instrument.SilverBarOrder;

public interface OrderOperations{
	
	boolean execute(SilverBarOrder silverBarOrder);
	/*
	 * Added the below execute operation to support the cancel with only userId argument.
	 * Assumption user will not enter all parameters to cancel his order. 
	 */
	boolean execute(int userId);
	Set  <OrderAttributes> execute(OrderType orderType);
}
