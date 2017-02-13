package datastore;


import instrument.SilverBarOrder;

import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import order.OrderAttributes;
import order.OrderType;

public class Data {
	private static final ConcurrentMap<Integer, OrderAttributes> cache = new ConcurrentHashMap<Integer, OrderAttributes>();

	public void clearCache(){
		cache.clear();
	}
	
	private boolean validateOrder(SilverBarOrder silverBarOrder ){
		if (silverBarOrder.getQuantity() < OrderConstants.VALID_QUANTITY )
			return false ;
		if(silverBarOrder.getPrice() <= OrderConstants.VALID_PRICE)
			return false ; 
		
		return true;
	}
	
	public boolean registerOrder(SilverBarOrder silverBarOrder){
		
		if (!validateOrder(silverBarOrder))
			return false ;

		OrderAttributes abc = new OrderAttributes(silverBarOrder.getQuantity() , 
										silverBarOrder.getPrice(), 
										silverBarOrder.getOrderType());
		
		OrderAttributes existingOrder = cache.putIfAbsent(silverBarOrder.getUserId(), abc);
		if(existingOrder != null )
			throw new RuntimeException("UserId/OrderId is not unique " + silverBarOrder.getUserId());

		return true;
	}
	
	public boolean cancelOrder( int orderId){
		OrderAttributes existingOrder = cache.remove(orderId);
		if (existingOrder == null )
			throw new RuntimeException("UserId/OrderId not found " + orderId);
		
		return true;
	}
	
	public Set <OrderAttributes> getSummary(OrderType orderType){
	
		if ( cache.isEmpty())
			throw new RuntimeException("Empty Order Book");   
		
	       Set <OrderAttributes> result1 = cache.values()
								                .parallelStream()
								                .filter(orderAttribute -> orderAttribute.getOrderType() == orderType)
								                .collect(Collectors.groupingBy(OrderAttributes::getPrice ))
								                .entrySet()
								                .stream()
								                .map(userAndOrderAttributeMap -> userAndOrderAttributeMap.getValue()
								                		   .stream()
								                		   .reduce(	(orderAttribute1,orderAttribute2) -> 
								                		   			new OrderAttributes(orderAttribute1.getQuantity() + orderAttribute2.getQuantity(),
								                		   								orderAttribute1.getPrice() , 
								                		   								orderType)
								                		   			)
								                	)
							                    .map(orderAttributeOption -> orderAttributeOption.get())
							                    .sorted(Comparator.comparing(OrderAttributes::getPrice))
							                    .collect(Collectors.toSet());
	       return result1;
	}
}
