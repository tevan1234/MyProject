package InsuranceSystem.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import InsuranceSystem.model.entity.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao{

	@Override
	public List<Order> findAllOrders() {
		List<Order>orders = new ArrayList<>();
		String sql = "select Id, Holder, HolderCN, Insured, InsuredCN, Type, PayType, Status, OrderDate, Charge, Contact, Comment from orders";
		try(Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery(sql)){
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("Id"));
				order.setHolder(rs.getString("Holder"));
				order.setHolderCardNumber(rs.getString("HolderCN"));
				order.setInsured(rs.getString("Insured"));
				order.setInsuredCardNumber(rs.getString("InsuredCN"));
				order.setOrderType(rs.getString("Type"));
				order.setPayType(rs.getInt("PayType"));
				order.setOrderStatus(rs.getBoolean("Status"));
				order.setOrderDate(rs.getString("OrderDate"));
				order.setCharge(rs.getString("Charge"));
				order.setContact(rs.getString("Contact"));
				order.setComment(rs.getString("Comment"));
				
				orders.add(order);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Order> filterOrders(String holderCardNumber) {
		List<Order>orders = new ArrayList<>();
		String sql = "select Id, Holder, HolderCN, Insured, InsuredCN, Type, PayType, Status, OrderDate, Charge, Contact, Comment from orders where HolderCN = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, holderCardNumber);
			try(ResultSet rs = pstmt.executeQuery()){			
				while (rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getInt("Id"));
					order.setHolder(rs.getString("Holder"));
					order.setHolderCardNumber(rs.getString("HolderCN"));
					order.setInsured(rs.getString("Insured"));
					order.setInsuredCardNumber(rs.getString("InsuredCN"));
					order.setOrderType(rs.getString("Type"));
					order.setPayType(rs.getInt("PayType"));
					order.setOrderStatus(rs.getBoolean("Status"));
					order.setOrderDate(rs.getString("OrderDate"));
					order.setCharge(rs.getString("Charge"));
					order.setContact(rs.getString("Contact"));
					order.setComment(rs.getString("Comment"));
					
					orders.add(order);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	@Override
	public Order getOrderbyId(Integer orderId) {
		String sql = "select Id, Holder, HolderCN, Insured, InsuredCN, Type, PayType, Status, OrderDate, Charge, Contact, Comment from orders where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, orderId);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getInt("Id"));
					order.setHolder(rs.getString("Holder"));
					order.setHolderCardNumber(rs.getString("HolderCN"));
					order.setInsured(rs.getString("Insured"));
					order.setInsuredCardNumber(rs.getString("InsuredCN"));
					order.setOrderType(rs.getString("Type"));
					order.setPayType(rs.getInt("PayType"));
					order.setOrderStatus(rs.getBoolean("Status"));
					order.setOrderDate(rs.getString("OrderDate"));
					order.setCharge(rs.getString("Charge"));
					order.setContact(rs.getString("Contact"));
					order.setComment(rs.getString("Comment"));
					
					return order;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Order getOrderbyCardNumber(String holderCardNumber) {
		String sql = "select Id, Holder, HolderCN, Insured, InsuredCN, Type, PayType, Status, OrderDate, Charge, Contact, Comment from orders where HolderCN = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, holderCardNumber);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getInt("Id"));
					order.setHolder(rs.getString("Holder"));
					order.setHolderCardNumber(rs.getString("HolderCN"));
					order.setInsured(rs.getString("Insured"));
					order.setInsuredCardNumber(rs.getString("InsuredCN"));
					order.setOrderType(rs.getString("Type"));
					order.setPayType(rs.getInt("PayType"));
					order.setOrderStatus(rs.getBoolean("Status"));
					order.setOrderDate(rs.getString("OrderDate"));
					order.setCharge(rs.getString("Charge"));
					order.setContact(rs.getString("Contact"));
					order.setComment(rs.getString("Comment"));
					
					return order;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addOrder(Order order) {
		String sql = "insert into orders(Holder, HolderCN, Insured, InsuredCN, Type, PayType, Status, OrderDate, Charge, Contact,Comment) values(?,?,?,?,?,?,?,?,?,?,?)";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, order.getHolder());
			pstmt.setString(2, order.getHolderCardNumber());
			pstmt.setString(3, order.getInsured());
			pstmt.setString(4, order.getInsuredCardNumber());
			pstmt.setString(5, order.getOrderType());
			pstmt.setInt(6, order.getPayType());
			pstmt.setBoolean(7, order.getOrderStatus());
			pstmt.setString(8, order.getOrderDate());
			pstmt.setString(9, order.getCharge());
			pstmt.setString(10, order.getContact());
			pstmt.setString(11, order.getComment());

			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("新增失敗");
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrder(Integer orderId, Boolean orderStatus,String Comment) {
		String sql = "update orders set Status = ?,Comment=? where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setBoolean(1, orderStatus);
			pstmt.setString(2, Comment);
			pstmt.setInt(3, orderId);
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("修改失敗 orderId:" + orderId + " orderStatus:" + orderStatus + " Comment:" + Comment);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrderCharge(Integer orderId, String Charge, String Contact) {
		String sql = "update orders set Charge = ?,Contact = ? where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, Charge);
			pstmt.setString(2, Contact);
			pstmt.setInt(3, orderId);
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("修改失敗 orderId:" + orderId + " Charge:" + Charge + " Contact:" + Contact);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrder(Integer orderId) {
		String sql = "delete from orders where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, orderId);
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("刪除失敗 orderId:" + orderId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	

}
