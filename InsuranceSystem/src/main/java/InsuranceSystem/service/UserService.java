package InsuranceSystem.service;

import java.util.ArrayList;
import java.util.List;

import InsuranceSystem.exception.PasswordInvalidException;
import InsuranceSystem.exception.UserNotFoundException;
import InsuranceSystem.model.dto.UserDto;
import InsuranceSystem.model.entity.User;
import InsuranceSystem.repository.UserDao;
import InsuranceSystem.repository.UserDaoImpl;
import InsuranceSystem.utils.Hash;

public class UserService {
	private UserDao userDao = new UserDaoImpl();
	
	//列出所有使用者
	public List<UserDto> findAll(){
		List<UserDto> userDtos = new ArrayList<>();
		List<User> users = userDao.findAllUsers();
		
		for (User user : users) {
			UserDto userDto = new UserDto();
			userDto.setUid(user.getUserId());
			userDto.setUrole(user.getUserRole());
			userDto.setUcardNumber(user.getUserCardNumber());
			userDto.setUname(user.getUserName());
			userDto.setUphone(user.getUserPhone());
			userDto.setUmail(user.getUserMail());
			userDto.setUactive(user.isUserActive());
			
			userDtos.add(userDto);			
		}
		return userDtos;
	}
	
	//查詢使用者
	public UserDto getUser(String username) {
		User user = userDao.getUser(username);
		if(user == null) {
			return null;
		}
		
		UserDto userDto = new UserDto();
		userDto.setUid(user.getUserId());
		userDto.setUcardNumber(user.getUserCardNumber());
		userDto.setUrole(user.getUserRole());
		userDto.setUname(user.getUserName());
		userDto.setUphone(user.getUserPhone());
		userDto.setUmail(user.getUserMail());
		userDto.setUactive(user.isUserActive());
		return userDto;
		
	}
	
	//新增使用者
	public void appendUser(String CardNumber,String username, String password,String phone,String mail,String role) {
		String salt = Hash.getSalt();
		String newpassword = Hash.getHash(password,salt);
		Boolean active = false;
		
		User user = new User();
		user.setUserCardNumber(CardNumber);
		user.setUserName(username);
		user.setSalt(salt);
		user.setUserPassword(newpassword);
		user.setUserPhone(phone);
		user.setUserMail(mail);
		user.setUserRole(role);
		user.setUserActive(active);
		
		userDao.addUser(user);
	}
	
	//更新使用者帳號狀態
	public void updateUser(String userId,String active,String role) {
		if(!active.isEmpty()) {
			userDao.updateUserActive(Integer.parseInt(userId), Boolean.parseBoolean(active));
		}
		if(!role.isEmpty()) {
			userDao.updateUserRole(Integer.parseInt(userId), role);
		}
	}
	//使用者更新密碼
	public void updatePassword(Integer userId,String username,String oldPassword,String newPassword) throws UserNotFoundException, PasswordInvalidException{
		User user = userDao.getUser(username);
		if(user == null) {
			throw new UserNotFoundException();
		}
		
		String oldPasswordHash = Hash.getHash(oldPassword,user.getSalt());
		if(!oldPasswordHash.equals(user.getUserPassword())){
			throw new PasswordInvalidException("原密碼輸入錯誤");
		}
		
		String newPasswordHash = Hash.getHash(newPassword, user.getSalt());
		userDao.updatePasswordHash(userId, newPasswordHash);
	}
	
	// 使用者更新資料
	public void updateInfo(String userId,String phone,String mail) {
		if(!phone.isEmpty() && !mail.isEmpty()) {
			userDao.updateUserInfo(Integer.parseInt(userId), phone, mail);
		}
	}
	
	//刪除使用者
	public void deleteUser(String userId) {
		userDao.deleteUser(Integer.parseInt(userId));
	}
}
