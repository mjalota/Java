package instrument;

import order.OrderType;

public class SilverBarOrder {
	private final int userId ;
	private final int quantity ;
	private final double price   ;
	private final OrderType orderType ;


	private SilverBarOrder(Builder builder) {
		this.userId 	= builder.userId;
		this.quantity 	= builder.quantity;
		this.price 		= builder.price;
		this.orderType 	= builder.orderType;
	}
	
	public static class Builder{
		private int userId ;
		
		private int quantity = 0   ;
		private double price  = 0.0 ;
		private OrderType orderType = OrderType.BUY;
		
		public Builder(int userId) {
			this.userId = userId;
		}
		
		public Builder quantity(int quantity){
			this.quantity = quantity;
			return this;
		}
		public Builder price(double price ){
			this.price = price;
			return this;
		}
		public Builder orderType(OrderType orderType){
			this.orderType = orderType;
			return this;
		}
		
		public SilverBarOrder build(){
			return new SilverBarOrder(this);
		}
	}
	
	public int getUserId() 			{		return userId;		}
	public int getQuantity() 		{		return quantity;	}
	public double getPrice() 		{		return price;		}
	public OrderType getOrderType() {		return orderType;	}


	
	
}
