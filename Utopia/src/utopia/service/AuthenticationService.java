package utopia.service;

import utopia.entities.TblUser;
import utopia.enumeration.AuthenticationStatusEnum;
import utopia.enumeration.StatusEnum;
import utopia.enumeration.UserTypeEnum;
import utopia.session.IGroupImplLocal;
import utopia.session.IUserImplLocal;

public class AuthenticationService implements IAuthentication{

	
	private IUserService userService;

	
	public AuthenticationService(IUserImplLocal userEJB,IGroupImplLocal groupEJB) {
		super();
		this.userService = new UserService(userEJB);
	}

	@Override
	public int authentication(int userType, String userName, String password) {
		if(userType == UserTypeEnum.NORNALUSER.getValue()){
			TblUser user = userService.findByUserName(userName);
			if(user.getUserPassword().equals(password)) {
				if(user.getUserStatus() == StatusEnum.ACTIVE.getValue())
					return AuthenticationStatusEnum.SUCCESSFUL.getValue();
				else
					return AuthenticationStatusEnum.DEACTIVE.getValue();
			}else {
				return AuthenticationStatusEnum.NOTEXIST.getValue();
			}
		}else if(userType == UserTypeEnum.ADMIN.getValue()){
			TblUser user = userService.findByUserName(userName);
			
			if(user != null) {
				System.out.println("****UserName:"+userName+"<|>System Password:"+password+"<|>UserPassword:"+user.getUserPassword());
				if(user.getUserPassword().equals(password)) {
					System.out.println("****UserName:"+userName+"Password:"+password);
					if(user.getUserStatus() == StatusEnum.ACTIVE.getValue())
					{
						return AuthenticationStatusEnum.SUCCESSFUL.getValue();
					}
					else
						return AuthenticationStatusEnum.DEACTIVE.getValue();
				}else 
					return AuthenticationStatusEnum.UNSUCCESSFUL.getValue();
			}else
				return AuthenticationStatusEnum.NOTEXIST.getValue();
		}else {
			AuthenticationStatusEnum.UNKNOWN.getValue();
		}
		return AuthenticationStatusEnum.UNKNOWN.getValue();
	}

}
