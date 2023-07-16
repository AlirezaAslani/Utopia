package utopia.webservices.entities;

public class Logon {
    private java.lang.String userName;

    private java.lang.String password;
    
    private java.lang.String sessionId;

    public Logon() {
    }

    public Logon(String name,String password) {
           this.userName = name;
           this.password = password;
    }

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getSessionId() {
		return sessionId;
	}

	public void setSessionId(java.lang.String sessionId) {
		this.sessionId = sessionId;
	}
	
	
    
}
