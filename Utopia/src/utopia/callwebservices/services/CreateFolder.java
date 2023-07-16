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

import utopia.enumeration.WebserviceResultEnum;
import utopia.webservices.entities.Logon;

public class CreateFolder {
	

	public CreateFolder(Logon logon, String path, String createIfNotExists, String createIntermediateDirs) {
		super();
		this.logon = logon;
		this.path = path;
		this.createIfNotExists = createIfNotExists;
		this.createIntermediateDirs = createIntermediateDirs;
	}


	private Logon logon;
	private String path;
	private String createIfNotExists;
	private String createIntermediateDirs;

	public Logon getLogon() {
		return logon;
	}

	public void setLogon(Logon logon) {
		this.logon = logon;
	}
	

	private void createSoapEnvelope(SOAPMessage soapMessage, String Action, Logon logon) throws SOAPException {

		SOAPBody soapBody = null;
		SOAPElement soapBodyElem = null;

		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();

		// Other Envelope NameSpace
		// envelope.addNamespaceDeclaration("xmlns",
		// "http://schemas.xmlsoap.org/soap/envelope/");

		switch (Action) {
		case "createFolder":

			 soapBody = envelope.getBody();
			 soapBodyElem = soapBody.addChildElement("createFolder","","urn://oracle.bi.webservices/v12");
			
			SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("path","","");
			soapBodyElem1.addTextNode(this.path);
			SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("createIfNotExists","","");
			soapBodyElem2.addTextNode(this.createIfNotExists);
			SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("createIntermediateDirs","","");
			soapBodyElem3.addTextNode(this.createIntermediateDirs);
			SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("sessionID","","");
			soapBodyElem4.addTextNode(logon.getSessionId());
			
			break;
		}

	}

	private SOAPMessage createSOAPRequest(String action, Logon loogn) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		createSoapEnvelope(soapMessage, action, logon);

		switch (action) {

		case "createFolder":
			MimeHeaders headers2 = soapMessage.getMimeHeaders();
			headers2.addHeader("SOAPAction", "createFolder");
			break;
		}

		soapMessage.saveChanges();

		/* Print the request message, just for debugging purposes */
		System.out.println("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println("\n");

		return soapMessage;
	}

	private SOAPMessage callSoapWebService(String soapEndpointUrl, String action, Logon logon) throws Exception {
		try {

			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			// Send SOAP Message to SOAP Server
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(action,logon), soapEndpointUrl);

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

	private SOAPMessage callObiWebService(String soapEndpointUrlStr, Logon logon, String action) throws Exception {

		StringBuffer soapEndpointUrlStrBuffers = new StringBuffer();
		SOAPMessage sOAPMessage = null;
		soapEndpointUrlStrBuffers.append(soapEndpointUrlStr);
		switch (action) {

		case "createFolder":
			soapEndpointUrlStrBuffers.append("webCatalogService");
			break;
		}

		try {

			sOAPMessage = callSoapWebService(soapEndpointUrlStrBuffers.toString(), action, logon);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return sOAPMessage;

	}

	private String getCreateFolderResult(SOAPMessage sOAPMessage) {
	
		String result = WebserviceResultEnum.CreateFolder_Done.getValue();
		
		try {
			SOAPPart sp = sOAPMessage.getSOAPPart();
			SOAPEnvelope se = sp.getEnvelope();
			SOAPBody sb = se.getBody();
			Iterator soapBodyIterator = sb.getChildElements();
		
			while (soapBodyIterator.hasNext()) {
				Node soapBodyNodes = (Node) soapBodyIterator.next();
				if (soapBodyNodes.getNodeType() == Node.ELEMENT_NODE) {
					SOAPElement soapBodyElement = (SOAPElement) soapBodyNodes;
					Iterator getGlobalPrivilegesACLResultIterator = soapBodyElement.getChildElements();
					if(getGlobalPrivilegesACLResultIterator.hasNext()) {
						return WebserviceResultEnum.CreateFolder_Failed.getValue();
					}else {
						return WebserviceResultEnum.CreateFolder_Done.getValue();
					}

				} else if (soapBodyNodes.getNodeType() == Node.TEXT_NODE) {
					// do nothing here most likely, as the response nearly never has mixed content
					// type
					// this is just for your reference
				}
			}

		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return result;
	}
	
	public String getResult(String url){
		
		String createFolderResult= null;
	
		try {

			SOAPMessage sOAPMessage2 = callObiWebService(url,
					logon,"createFolder");
			createFolderResult = getCreateFolderResult(sOAPMessage2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return createFolderResult;
		
	}

	
	public static void main(String args[]) {
		GetSession getSession = new GetSession();
		Logon logon = null;
		try {
			
			 logon =  getSession.getResult("weblogic", "oracle12C", "http://5.201.178.229:9502/analytics-ws/saw.dll?SoapImpl=");
			 //logon =  getSession.getResult("weblogic", "oracle12C", "http://192.168.1.250:9502/analytics-ws/saw.dll?SoapImpl=");

			 
			 CreateFolder c = new CreateFolder(logon,"/shared/alireza6","true","true");
			 String createFolderResult = c.getResult("http://5.201.178.229:9502/analytics-ws/saw.dll?SoapImpl=");
			 System.out.println("********************"+createFolderResult);
			 //GetGlobalPrivilegesACLResult getGlobalPrivilegesACLResult = c.getResult("http://192.168.1.250:9502/analytics-ws/saw.dll?SoapImpl=");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
