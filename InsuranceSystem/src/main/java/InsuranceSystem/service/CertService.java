package InsuranceSystem.service;

import InsuranceSystem.exception.CertException;
import InsuranceSystem.exception.PasswordInvalidException;
import InsuranceSystem.exception.UserNotFoundException;
import InsuranceSystem.model.dto.UserCert;
import InsuranceSystem.model.entity.User;
import InsuranceSystem.repository.UserDao;
import InsuranceSystem.repository.UserDaoImpl;
import InsuranceSystem.utils.Hash;
	
public class CertService {
	private UserDao userDao = new UserDaoImpl();
	public UserCert getCert(String username,String password) throws CertException{
		User user = userDao.getUser(username);
		if(user == null) {
			throw new UserNotFoundException();
		}
		String passwordHash = Hash.getHash(password,user.getSalt());
		if(!passwordHash.equals(user.getUserPassword())) {
			throw new PasswordInvalidException();			
		}
		UserCert userCert = new UserCert(user.getUserId(), user.getUserCardNumber(), user.getUserName(), user.getUserRole(), user.getUserPhone(),user.getUserMail());
		return userCert;
	}
}
