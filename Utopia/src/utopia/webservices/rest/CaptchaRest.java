package utopia.webservices.rest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utopia.service.CaptchaService;
import utopia.service.ConfigService;
import utopia.service.IConfigService;
import utopia.session.IConfigImplLocal;

@Path("/captcha")
@Stateless
public class CaptchaRest {

	@EJB(name = "config")
	private IConfigImplLocal configFacade;
	
	@EJB(name="CaptchaService")
	private CaptchaService captchaService;

	private IConfigService configService;
	private HashMap<String, String> configMap;


	@PostConstruct
	public void init() {
		configService = new ConfigService(configFacade);
		configMap = configService.getConfigMap();
		
	}

	// Specifies that the method processes HTTP POST requests and return all users
	// object from DB
	@Path("/captchaimg")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getCaptchaImage(String body) throws ParseException {

		String[] captchaData=captchaService.generateCaptchaImage(null);
		JSONObject response = new JSONObject();
		response.put("captchaId", captchaData[1]);
		response.put("captchaImg", captchaData[0]);
		return response.toString();
	}
	
	@Path("/reloadcaptchaimage")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String reloadCaptchaImage(String body) throws ParseException {

		String[] captchaData=captchaService.generateCaptchaImage(null);
		JSONObject response = new JSONObject();
		response.put("captchaId", captchaData[1]);
		response.put("captchaImg", captchaData[0]);
		return response.toString();
	}
	
	@Path("/validatecaptchaimage")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String validateCaptchaImage(String body) throws ParseException {
		
		JSONParser parser = new JSONParser();  
		JSONObject json = (JSONObject) parser.parse(body);  
		Object obj = parser.parse(body);
		Map<String, String> objectMap = (Map<String, String>) obj;
		 String captchaAnswer = objectMap.get("captchaAnswer");
		 String captchaId = objectMap.get("captchaId");

		boolean challange=false;
		if((captchaAnswer!=null && captchaAnswer.trim().length()!=0) && (captchaId!=null && captchaId.trim().length()!=0))
			challange=captchaService.validateCaptcha(captchaId, captchaAnswer);
		
		JSONObject response = new JSONObject();
		response.put("challange", challange?"success":"fail");
		return response.toString();
	}

}
