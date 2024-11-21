package InsuranceSystem.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import InsuranceSystem.model.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public List<User> findAllUsers() {
		List<User> users = new ArrayList<>();
		String sql = "select Id,Role,CardNumber,Name,Salt,Password,Phone,Mail,Active from account";
		try(Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery(sql)){
			while(rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("Id"));
				user.setUserRole(rs.getString("Role"));
				user.setUserCardNumber(rs.getString("CardNumber"));
				user.setUserName(rs.getString("Name"));
				user.setSalt(rs.getString("Salt"));
				user.setUserPassword(rs.getString("Password"));
				user.setUserPhone(rs.getString("Phone"));
				user.setUserMail(rs.getString("Mail"));
				user.setUserActive(rs.getBoolean("Active"));
				users.add(user);	
			}					
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUser(String username) {
		String sql = "select Id,Role,CardNumber,Name,Salt,Password,Phone,Mail,Active from account where Name = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, username);
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					User user = new User();
					user.setUserId(rs.getInt("Id"));
					user.setUserRole(rs.getString("Role"));
					user.setUserCardNumber(rs.getString("CardNumber"));
					user.setUserName(rs.getString("Name"));
					user.setSalt(rs.getString("Salt"));
					user.setUserPassword(rs.getString("Password"));
					user.setUserPhone(rs.getString("Phone"));
					user.setUserMail(rs.getString("Mail"));
					user.setUserActive(rs.getBoolean("Active"));
					return user;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addUser(User user) {
		String sql = "insert into account(Role,CardNumber,Name,Salt,Password,Phone,Mail,Active) values(?,?,?,?,?,?,?,?)";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, user.getUserRole());			
			pstmt.setString(2, user.getUserCardNumber());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getSalt());
			pstmt.setString(5, user.getUserPassword());
			pstmt.setString(6, user.getUserPhone());
			pstmt.setString(7, user.getUserMail());
			pstmt.setBoolean(8, user.isUserActive());
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("新增失敗");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUserActive(Integer userId, Boolean active) {
		String sql = "update account set Active = ? where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setBoolean(1, active);
			pstmt.setInt(2, userId);
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("修改失敗 userId:" + userId + " active:" + active);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateUserRole(Integer userId, String role) {
		String sql = "update account set Role = ? where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, role);
			pstmt.setInt(2, userId);
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("修改失敗 userId:" + userId + " role:" + role);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}	

	@Override
	public void updateUserInfo(Integer userId, String phone, String email) {
		String sql = "update account set Phone = ?,email = ? where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, phone);
			pstmt.setString(2, email);
			pstmt.setInt(3, userId);
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("修改失敗 userId:" + userId + " phone:" + phone + " email:" + email);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(Integer userId) {
		String sql = "delete from account where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, userId);
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount != 1) {
				throw new RuntimeException("刪除失敗 userId:" + userId);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePasswordHash(Integer userId, String newPasswordHash) {
		String sql = "update account set Password = ? where Id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, newPasswordHash);
			pstmt.setInt(2, userId);
			
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
