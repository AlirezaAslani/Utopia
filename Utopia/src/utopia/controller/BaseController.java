package utopia.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public abstract class BaseController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	Map<String, Object> sessionMap = externalContext.getSessionMap();

	public String getUserName() {
		String userName = (String) sessionMap.get("user");
		return userName;
	}

	public Map<String, Object> getSessionMap() {

		return sessionMap;
	}
}
