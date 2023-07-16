package utopia.webservices.soap;

import javax.ejb.EJB;
import javax.inject.Singleton;
import javax.jws.*;
import utopia.service.IAuthentication;
import utopia.session.IGroupImplLocal;
import utopia.session.IUserImplLocal;

@WebService
public class FirstWS {
	
	@EJB(name = "user")
	private IUserImplLocal userFacade;
	
	@EJB(name = "group")
	private IGroupImplLocal groupFacade;
	
	@Singleton
	private IAuthentication authentication;
	
	/*
	 * @PostConstruct public void init() { authentication = new
	 * AuthenticationService(userFacade, roleFacade); }
	 */
	
	@WebResult(name = "Result")
	@WebMethod(operationName = "Adding")
	public int sum(@WebParam(name = "FirstNumber") int n1,@WebParam(name = "SecondNumber") int n2) {
		return n1+n2;
	}
	
	/*
	 * @WebResult(name = "aut")
	 * 
	 * @WebMethod(operationName = "login") public int login(@WebParam(name =
	 * "Panel") int n1,@WebParam(name = "UserName") String n2,@WebParam(name =
	 * "Password") String n3) {
	 * 
	 * return authentication.authentication(n1, n2, n3);
	 * 
	 * }
	 */
}
