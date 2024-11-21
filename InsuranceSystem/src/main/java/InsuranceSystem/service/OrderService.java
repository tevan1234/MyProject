package InsuranceSystem.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import InsuranceSystem.model.dto.OrderDto;
import InsuranceSystem.model.entity.Order;
import InsuranceSystem.model.entity.Prodouct;
import InsuranceSystem.repository.OrderDao;
import InsuranceSystem.repository.OrderDaoImpl;
import InsuranceSystem.repository.ProdouctDao;
import InsuranceSystem.repository.ProdouctDaoImpl;

public class OrderService {
	private OrderDao orderDao = new OrderDaoImpl();
	private ProdouctDao prodouctDao = new ProdouctDaoImpl();
	public List<OrderDto> findAll() {
		List<OrderDto> orderDtos = new ArrayList<>();
		List<Order> orders = orderDao.findAllOrders();
		List<Prodouct> products = prodouctDao.findALLProdoucts();
		
		for(Order order : orders) {
			OrderDto orderDto = new OrderDto();
			orderDto.setOid(order.getOrderId());
			orderDto.setHolder(order.getHolder());
			orderDto.setHolderCardNumber(order.getHolderCardNumber());
			orderDto.setInsured(order.getInsured());
			orderDto.setInsuredCardNumber(order.getInsuredCardNumber());
			orderDto.setOtype(order.getOrderType());
			orderDto.setOpayType(order.getPayType());
			orderDto.setOstatus(order.getOrderStatus());
			orderDto.setOdate(order.getOrderDate());
			orderDto.setOcharge(order.getCharge());
			orderDto.setOcontact(order.getContact());
			orderDto.setOcomment(order.getComment());
			
			Optional<Prodouct> optProdouct = products.stream()
										.filter(p -> p.getProdouctType().equals(orderDto.getOtype())).findFirst();
			if(optProdouct.isPresent()) {
				orderDto.setOrderPrice(optProdouct.get().getPrice() * order.getPayType());
			}
			
			orderDtos.add(orderDto);
		}
		return orderDtos;
	}
	
	public List<OrderDto> filterAll(String orderCardNumber){
		List<OrderDto> orderDtos = new ArrayList<>();
		List<Order> orders = orderDao.filterOrders(orderCardNumber);
		List<Prodouct> products = prodouctDao.findALLProdoucts();
		
		for(Order order : orders) {
			OrderDto orderDto = new OrderDto();
			orderDto.setOid(order.getOrderId());
			orderDto.setHolder(order.getHolder());
			orderDto.setHolderCardNumber(order.getHolderCardNumber());
			orderDto.setInsured(order.getInsured());
			orderDto.setInsuredCardNumber(order.getInsuredCardNumber());
			orderDto.setOtype(order.getOrderType());
			orderDto.setOpayType(order.getPayType());
			orderDto.setOstatus(order.getOrderStatus());
			orderDto.setOdate(order.getOrderDate());
			orderDto.setOcharge(order.getCharge());
			orderDto.setOcontact(order.getContact());
			orderDto.setOcomment(order.getComment());
			
			Optional<Prodouct> optProdouct = products.stream()
										.filter(p -> p.getProdouctType().equals(orderDto.getOtype())).findFirst();
			if(optProdouct.isPresent()) {
				orderDto.setOrderPrice(optProdouct.get().getPrice() * order.getPayType());
			}
			
			orderDtos.add(orderDto);
		}
		return orderDtos;
	}
	
	public OrderDto getOrderbyId(String orderId) {
		Order order = orderDao.getOrderbyId(Integer.parseInt(orderId));
		List<Prodouct> products = prodouctDao.findALLProdoucts();
		if(order == null) {
			return null;
		}
		OrderDto orderDto = new OrderDto();
		orderDto.setOid(order.getOrderId());
		orderDto.setHolder(order.getHolder());
		orderDto.setHolderCardNumber(order.getHolderCardNumber());
		orderDto.setInsured(order.getInsured());
		orderDto.setInsuredCardNumber(order.getInsuredCardNumber());
		orderDto.setOtype(order.getOrderType());
		orderDto.setOpayType(order.getPayType());
		orderDto.setOstatus(order.getOrderStatus());
		orderDto.setOdate(order.getOrderDate());
		orderDto.setOcharge(order.getCharge());
		orderDto.setOcontact(order.getContact());
		orderDto.setOcomment(order.getComment());
		
		Optional<Prodouct> optProdouct = products.stream()
				.filter(p -> p.getProdouctType().equals(orderDto.getOtype())).findFirst();
		if(optProdouct.isPresent()) {
		orderDto.setOrderPrice(optProdouct.get().getPrice() * order.getPayType());
		}
		
		return orderDto;
	}
	
	public OrderDto getOrderbyCardNumber(String HolderCardNumber) {
		Order order = orderDao.getOrderbyCardNumber(HolderCardNumber);
		List<Prodouct>products = prodouctDao.findALLProdoucts();
		if(order == null) {
			return null;
		}
		OrderDto orderDto = new OrderDto();
		orderDto.setOid(order.getOrderId());
		orderDto.setHolder(order.getHolder());
		orderDto.setHolderCardNumber(order.getHolderCardNumber());
		orderDto.setInsured(order.getInsured());
		orderDto.setInsuredCardNumber(order.getInsuredCardNumber());
		orderDto.setOtype(order.getOrderType());
		orderDto.setOpayType(order.getPayType());
		orderDto.setOstatus(order.getOrderStatus());
		orderDto.setOdate(order.getOrderDate());
		orderDto.setOcharge(order.getCharge());
		orderDto.setOcontact(order.getContact());
		orderDto.setOcomment(order.getComment());
		
		Optional<Prodouct> optProdouct = products.stream()
				.filter(p -> p.getProdouctType().equals(orderDto.getOtype())).findFirst();
		
		if(optProdouct.isPresent()) {
		orderDto.setOrderPrice(optProdouct.get().getPrice() * order.getPayType());
		}
		
		return orderDto;
	}
	
	public void updateOrder(String orderId,String orderStatus, String Charge, String Contact,String Comment) {
		if(!orderStatus.isEmpty() || !Comment.isEmpty()) {
			orderDao.updateOrder(Integer.parseInt(orderId), Boolean.parseBoolean(orderStatus),Comment);
		}
		if(!Charge.isEmpty() && !Contact.isEmpty() ) {
			orderDao.updateOrderCharge(Integer.parseInt(orderId), Charge,Contact);
		}
	}
	
	public void addOrder(String Holder,String HolderCN,String Insured,String InsuredCN,String OrderType,String PayType,String orderDate,String charge,String contact,String comment) {
		Boolean orderStatus = false;
		Order order = new Order();
		order.setHolder(Holder);
		order.setHolderCardNumber(HolderCN);
		order.setInsured(Insured);
		order.setInsuredCardNumber(InsuredCN);
		order.setOrderType(OrderType);
		order.setPayType(Integer.parseInt(PayType));
		order.setOrderStatus(orderStatus);
		order.setOrderDate(orderDate);
		order.setCharge(charge);
		order.setContact(contact);
		order.setComment(comment);
		
		orderDao.addOrder(order);
	}
	
	public void deleteOrder(String orderId) {
		orderDao.deleteOrder(Integer.parseInt(orderId));
	}
}
