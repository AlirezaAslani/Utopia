package utopia.webservices.rest;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utopia.enumeration.UserTypeEnum;
import utopia.service.AuthenticationService;
import utopia.service.IAuthentication;
import utopia.service.IUserService;
import utopia.service.UserService;
import utopia.session.IConfigImplLocal;
import utopia.session.IGroupImplLocal;
import utopia.session.IUserImplLocal;
import utopia.utils.EncoderUtils;

@Path("/login")
@Stateless
public class LoginRest {
	
	@EJB(name = "user")
	private IUserImplLocal userFacade;

	@EJB(name = "group")
	private IGroupImplLocal groupFacade;
	
	@EJB(name = "config")
	private IConfigImplLocal configFacade;
	
	private IAuthentication authentication;
	private IUserService userService;



	@PostConstruct
	public void init() {
		userService = new UserService(userFacade);
		authentication = new AuthenticationService(userFacade, groupFacade);
	}

	   @Path("/normaluser")
	   @POST
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   public String login(String body) throws ParseException {
		   
			JSONParser parser = new JSONParser();  
			JSONObject json = (JSONObject) parser.parse(body);  
			Object obj = parser.parse(body);
			Map<String, Object> objectMap = (Map<String, Object>) obj;

			int result = 0;
			String password = null;
			String userName = null;
			HashMap<String, HashMap<String, String>> userNameList = new HashMap<String, HashMap<String, String>>();
				
			for (Map.Entry<String, Object> user : objectMap.entrySet()) {
				userNameList.put(user.getKey(), (HashMap<String, String>) user.getValue());
			}

			for (Map.Entry<String, HashMap<String, String>> user : userNameList.entrySet()) {
				HashMap<String, String> userValue = user.getValue();
				userName = user.getKey();
				if (userValue.get("PASSWORD") != null && !userValue.get("PASSWORD").isEmpty())
				password = EncoderUtils.encoder(userValue.get("PASSWORD"));
			}
			try {
				 System.out.println("****Rest Call recived");
				 result = authentication.authentication(UserTypeEnum.ADMIN.getValue(), userName, password);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.getMessage();
			}
		return String.valueOf(result);

	   }
}
