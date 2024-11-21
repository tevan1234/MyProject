package InsuranceSystem.model.entity;

import lombok.Data;

@Data
public class User {
	private int userId;
	private String userRole;
	private String userCardNumber;
	private String userName;
	private String Salt;
	private String userPassword;
	private String userPhone;
	private String userMail;
	private boolean userActive;
}
