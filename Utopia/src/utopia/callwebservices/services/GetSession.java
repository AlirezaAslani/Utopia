package utopia.callwebservices.services;

import java.util.Iterator;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import utopia.webservices.entities.Logon;


public class GetSession {

	private void createSoapEnvelope(SOAPMessage soapMessage, String Action, Logon logon)
			throws SOAPException {

		SOAPBody soapBody = null;
		SOAPElement soapBodyElem = null;
		
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();

		// Other Envelope NameSpace
		// envelope.addNamespaceDeclaration("xmlns",
		// "http://schemas.xmlsoap.org/soap/envelope/");

		switch (Action) {
		case "logon":

			 soapBody = envelope.getBody();
			 soapBodyElem = soapBody.addChildElement("logon","","urn://oracle.bi.webservices/v12");
			
			SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("name","","");
			soapBodyElem1.addTextNode(logon.getUserName());
			SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("password","","");
			soapBodyElem2.addTextNode(logon.getPassword());
			
			break;
		}

	}

	private SOAPMessage createSOAPRequest(String action, Logon logon)
			throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		createSoapEnvelope(soapMessage, action, logon);


		switch (action) {
		case "logon":

			// If we need Headers, use headerParameters and below commands
			 MimeHeaders headers = soapMessage.getMimeHeaders();
			 headers.addHeader("SOAPAction",action );

			break;

		}

		soapMessage.saveChanges();

		/* Print the request message, just for debugging purposes */
		System.out.println("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println("\n");

		return soapMessage;
	}

	private SOAPMessage callSoapWebService(String soapEndpointUrl, String action,Logon logon) throws Exception {
		try {

			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			// Send SOAP Message to SOAP Server
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(action, logon),
					soapEndpointUrl);

			// Print the SOAP Response
			System.out.println("Response SOAP Message:");
			soapResponse.writeTo(System.out);
			System.out.println();

			soapConnection.close();

			return soapResponse;
		} catch (SOAPException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}

	}

	private SOAPMessage callObiWebService(String soapEndpointUrlStr,Logon logon, String action)
			throws Exception {

		StringBuffer soapEndpointUrlStrBuffer = new StringBuffer();
		SOAPMessage sOAPMessage = null;
		soapEndpointUrlStrBuffer.append(soapEndpointUrlStr);
		switch (action) {
		case "logon":
			soapEndpointUrlStrBuffer.append("nQSessionService");
			break;
		}
		
		try {

			sOAPMessage = callSoapWebService(soapEndpointUrlStrBuffer.toString(), action, logon);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return sOAPMessage;

	}

	private Logon getSessionID(SOAPMessage sOAPMessage ,Logon logon) {

		try {
			SOAPPart sp = sOAPMessage.getSOAPPart();
			SOAPEnvelope se = sp.getEnvelope();
			SOAPBody sb = se.getBody();
			Iterator it = sb.getChildElements();
			Iterator itr=sOAPMessage.getSOAPBody().getChildElements();
			while (itr.hasNext()) {
			    Node node=(Node)itr.next();
			    if (node.getNodeType()==Node.ELEMENT_NODE) {
			    	SOAPElement ele=(SOAPElement)node;
			        Iterator it3 = ele.getChildElements();
					while (it3.hasNext()) {
						Node node2 = (Node) it3.next();
						if (node2.getValue() != null) {
							logon.setSessionId(node2.getValue());
							return logon;
						}
					}
			    } else if (node.getNodeType()==Node.TEXT_NODE) {
			        //do nothing here most likely, as the response nearly never has mixed content type
			        //this is just for your reference
			    }
			}

		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return logon;
	}

	
	public Logon getResult(String userName,String password, String url) {
		
		GetSession c = new GetSession();
		Logon logon = new Logon();
		Logon logonResult = null;
		logon.setUserName(userName);
		logon.setPassword(password);
		
		SOAPMessage sOAPMessage = null;
		try {
			sOAPMessage = c.callObiWebService(url,logon, "logon");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sOAPMessage != null)
			logonResult = c.getSessionID(sOAPMessage,logon); 
		
		return logonResult;
		
	}
	public static void main(String args[]) {
		GetSession c = new GetSession();
		Logon logon = new Logon();
		logon.setUserName("weblogic");
		logon.setPassword("oracle12C");
		
		try {
			SOAPMessage sOAPMessage = c.callObiWebService("http://5.201.178.229:9502/analytics-ws/saw.dll?SoapImpl=",logon, "logon");
					Logon l = c.getSessionID(sOAPMessage,logon); 
					System.out.println(l.getSessionId());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
