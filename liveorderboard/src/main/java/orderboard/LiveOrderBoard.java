package orderboard;

import java.util.Set;

import order.OrderAttributes;
import order.OrderType;

public interface LiveOrderBoard {
	public boolean registerAnOrder(int userId , int quantity, double price, OrderType orderType);
	public boolean cancelAnOrder(int userID) ;
	public Set<OrderAttributes> getSummary(OrderType orderType);
}
