package utopia.webservices.soap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Singleton;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.net.MalformedURLException;
import utopia.service.AuthenticationService;
import utopia.service.ConfigService;
import utopia.service.IAuthentication;
import utopia.service.IConfigService;
import utopia.session.IConfigImplLocal;
import utopia.session.IGroupImplLocal;
import utopia.session.IUserImplLocal;

@WebService
public class AuthenticationWS {

	@Resource
	private WebServiceContext webServiceContext;

	@EJB(name = "user")
	private IUserImplLocal userFacade;

	@EJB(name = "group")
	private IGroupImplLocal groupFacade;

	
	@EJB(name = "config")
	private IConfigImplLocal configFacade;

	@Singleton
	private IAuthentication authentication;
	
	@Singleton
	private IConfigService configService;

	@PostConstruct
	public void init() {
		authentication = new AuthenticationService(userFacade, groupFacade);
		configService = new ConfigService(configFacade);
	}
	
	@WebResult(name = "adminAuthentication")
	@WebMethod(operationName = "adminLogin")
	public int adminLogin(@WebParam(name = "UserType") int userType, @WebParam(name = "UserName") String userName,
			@WebParam(name = "Password") String password) throws MalformedURLException {

		int result = authentication.authentication(userType, userName, password);

			MessageContext mc = webServiceContext.getMessageContext();
			HttpServletRequest request = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			HttpServletResponse response = (HttpServletResponse) mc.get(MessageContext.SERVLET_RESPONSE);
			
		return result;
	}
}
