package InsuranceSystem.repository;

import java.util.List;

import InsuranceSystem.model.entity.Order;

public interface OrderDao {
	List<Order> findAllOrders();
	
	List<Order> filterOrders(String holderCardNumber);
	
	Order getOrderbyId(Integer orderId);
	
	Order getOrderbyCardNumber(String holderCardNumber);
	
	void addOrder(Order order);
	
	void updateOrder(Integer orderId, Boolean orderStatus,String comment);
	
	void updateOrderCharge(Integer orderId, String charge, String Contact);
	
	void deleteOrder(Integer orderId);
}
