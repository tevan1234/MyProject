package InsuranceSystem.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import InsuranceSystem.model.entity.Prodouct;

public class ProdouctDaoImpl extends BaseDao implements ProdouctDao{

	@Override
	public List<Prodouct> findALLProdoucts() {
		List<Prodouct> prodoucts = new ArrayList<>();
		String sql = "select Id, Type, Price, Status, Sales from prodouct";
		try(Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery(sql)){
			while(rs.next()) {
				Prodouct prodouct = new Prodouct();
				prodouct.setProdouctId(rs.getInt("Id"));
				prodouct.setProdouctType(rs.getString("Type"));
				prodouct.setPrice(rs.getDouble("Price"));
				prodouct.setProdouctStatus(rs.getBoolean("Status"));
				prodouct.setSales(rs.getInt("Sales"));
				prodoucts.add(prodouct);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return prodoucts;
	}

	@Override
	public Prodouct getProdouct(Integer prodouctId) {
		String sql = "select Id, Type, Price, Status, Sales from prodouct where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, prodouctId);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					Prodouct prodouct = new Prodouct();
					prodouct.setProdouctId(rs.getInt("Id"));
					prodouct.setProdouctType(rs.getString("Type"));
					prodouct.setPrice(rs.getDouble("Price"));
					prodouct.setProdouctStatus(rs.getBoolean("Status"));
					prodouct.setSales(rs.getInt("Sales"));
					return prodouct;
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Prodouct getProdouctByName(String productType) {
		String sql = "select Id, Type, Price, Status, Sales from prodouct where Type = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, productType);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					Prodouct prodouct = new Prodouct();
					prodouct.setProdouctId(rs.getInt("Id"));
					prodouct.setProdouctType(rs.getString("Type"));
					prodouct.setPrice(rs.getDouble("Price"));
					prodouct.setProdouctStatus(rs.getBoolean("Status"));
					prodouct.setSales(rs.getInt("Sales"));
					return prodouct;
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addProduct(Prodouct product) {
		String sql = "insert into prodouct(Type,Price,Status,Sales) values(?,?,?,?)";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, product.getProdouctType());
			pstmt.setDouble(2, product.getPrice());
			pstmt.setBoolean(3, product.getProdouctStatus());
			pstmt.setInt(4, product.getSales());
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("新增失敗");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateProductPrice(Integer prodouctId, double price) {
		String sql = "update prodouct set Price = ? where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setDouble(1, price);
			pstmt.setInt(2,prodouctId);
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("修改失敗 prodouctId:" + prodouctId + " price:" + price);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProdouctStatus(Integer prodouctId, Boolean prodouctStatus) {
		String sql = "update prodouct set Status = ? where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setBoolean(1, prodouctStatus);
			pstmt.setInt(2,prodouctId);
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("修改失敗 prodouctId:" + prodouctId + " prodouctStatus:" + prodouctStatus);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	@Override
	public void deleteProdouct(Integer prodouctId) {
		String sql = "delete from prodouct where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, prodouctId);
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("刪除失敗 prodouctId:" + prodouctId);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Integer> Ranking() {
		String sql = "SELECT p.Type,(p.Sales + COALESCE(COUNT(o.Type), 0)) AS Sales FROM prodouct p LEFT JOIN orders o ON p.Type = o.Type GROUP BY p.Type, p.Sales";
				
		Map<String, Integer> map = new LinkedHashMap<>();
		try(Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery(sql)){
			while(rs.next()) {
				String key = rs.getString("Type");
				Integer value = rs.getInt("Sales");
				map.put(key, value);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

}
