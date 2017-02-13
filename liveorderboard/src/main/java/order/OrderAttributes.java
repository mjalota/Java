package order;

public class OrderAttributes {

	private int quantity  ;
	private double price;
	private OrderType orderType = OrderType.BUY;

	public OrderAttributes(int quantity, double price, OrderType orderType) {
		this.quantity = quantity;
		this.price = price;
		this.orderType = orderType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
}
