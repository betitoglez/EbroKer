package com.alberto.http;

import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.FileUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.protocol.RequestAddCookies;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParamConfig;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Selector;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	
	@SuppressWarnings("deprecation")
	public String test () throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException{
		
		//System.setProperty("https.protocols", "TLSv1");
		//CloseableHttpClient httpclient = HttpClients.createDefault();
		@SuppressWarnings("deprecation")
		java.net.CookieManager manager = new java.net.CookieManager();
		java.net.CookieHandler.setDefault(manager);
		manager.getCookieStore().removeAll();
		
		BasicCookieStore cookieStore = new BasicCookieStore();
	    HttpClientContext context = HttpClientContext.create();
	    context.setCookieStore(cookieStore);
		
		CloseableHttpClient httpclient = HttpClientBuilder.create()
				.setDefaultCookieStore(cookieStore).setRedirectStrategy(new LaxRedirectStrategy()).setSslcontext(SSLContexts.custom().useProtocol("TLSv1").build()).build();
		
		
		HttpGet httpget = new HttpGet("https://cfdsydivisas.selfbank.es/sim/Login/es");
		CloseableHttpResponse response = httpclient.execute(httpget,context);
		
		
		String _first = EntityUtils.toString(response.getEntity());
		response.close();
		
		Document first = Jsoup.parse(_first);
		
		String pAuthRequest = Selector.select("[name=AuthnRequest]", first).first().val();
		String pRelayState  = Selector.select("[name=RelayState]", first).first().val(); 
		
	
		HttpPost rLogin = new HttpPost("https://cfdsydivisas.selfbank.es/sim/Login/es");
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("AuthnRequest", pAuthRequest));
		formparams.add(new BasicNameValuePair("RelayState", pRelayState));
		formparams.add(new BasicNameValuePair("Locality", "es-ES"));
		formparams.add(new BasicNameValuePair("PageLoadInfo", "0|0"));
		formparams.add(new BasicNameValuePair("Platform", "Win32"));
		formparams.add(new BasicNameValuePair("ScreenResolution", "1525x858"));
		formparams.add(new BasicNameValuePair("TimeZone", "es-ES"));
		formparams.add(new BasicNameValuePair("field_password", "Admin1234"));
		formparams.add(new BasicNameValuePair("field_userid", "SB58472"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		rLogin.setEntity(entity);
		
		CloseableHttpResponse doLogin = httpclient.execute(rLogin,context);
	
	
		String _login = EntityUtils.toString(doLogin.getEntity());
		
		doLogin.close();
		
		Document login = Jsoup.parse(_login);
		
		String pSAML = Selector.select("[name=SAMLResponse]", login).first().val();
		
	
		HttpPost rLoginSe = new HttpPost("https://cfdsydivisas.selfbank.es/sim/login.sso.ashx");
		List<NameValuePair> formparamsSe = new ArrayList<NameValuePair>();
		formparamsSe.add(new BasicNameValuePair("SAMLResponse", pSAML));
		Date oDate = new Date();
		formparamsSe.add(new BasicNameValuePair("PageLoadInfo", String.valueOf(oDate.getTime())));
		formparamsSe.add(new BasicNameValuePair("RelayState", ""));
		UrlEncodedFormEntity entitySe = new UrlEncodedFormEntity(formparamsSe, Consts.UTF_8);
		rLoginSe.setEntity(entitySe);
		
		CloseableHttpResponse doLoginSe = httpclient.execute(rLoginSe,context);
		
		//FileUtils.writeStringToFile(new File("resources/htmloutput/output.html"), EntityUtils.toString(doLoginSe.getEntity()));

		
		for (Header header : doLoginSe.getAllHeaders()){
			
			if (header.getName().equalsIgnoreCase("Set-Cookie")){
				
				String cookieString = header.getValue();
				String name = cookieString.substring(0, cookieString.indexOf("="));
				String cookieBody = cookieString.substring(cookieString.indexOf("=")+1);
				
				String[] cookieChunks = cookieBody.split(";");
			
				
				
				BasicClientCookie cookie = new BasicClientCookie(name, cookieChunks[0]);
				cookie.setDomain("cfdsydivisas.selfbank.es");
				for (String _chunk : cookieChunks){
					
					if (_chunk.indexOf("path")>=0){
						cookie.setPath(_chunk.substring(6));
					} else if (_chunk.indexOf("secure")>=0){
						System.out.println("Secure");
						cookie.setSecure(true);
					} else if (_chunk.equalsIgnoreCase("HttpOnly")){
						
					}
				}
				
				cookieStore.addCookie(cookie);
				
			}
		}
		
		
		
		String welcomePage = EntityUtils.toString(doLoginSe.getEntity());
		
		
		
		
		doLoginSe.close();
	
		
		/*
		//Default https://cfdsydivisas.selfbank.es/sim/default.aspx?ast=1433170540412
		HttpGet defaultRe = new HttpGet("https://cfdsydivisas.selfbank.es/sim/default.aspx?ast="+String.valueOf(oDate.getTime()));
		CloseableHttpResponse odefaultRe = httpclient.execute(defaultRe,context);
		
		
		for (Header header : odefaultRe.getAllHeaders()){
			System.out.println(header.toString());
		}
		System.out.println(odefaultRe.getStatusLine());
		
		System.out.println(EntityUtils.toString(odefaultRe.getEntity()));
		
		
		odefaultRe.close();
        
		*/
		//https://sim.onlinewebconnect.com/CSWeb/login.aspx
		
		int indexSession = welcomePage.indexOf("session_id");
		String session_id = welcomePage.substring(indexSession+19,welcomePage.indexOf("/",indexSession+20)-1);
		
		//System.out.println(session_id);
		
		HttpPost rLoginId = new HttpPost("https://sim.onlinewebconnect.com/CSWeb/login.aspx");
			
		List<NameValuePair> idparams = new ArrayList<NameValuePair>();
		idparams.add(new BasicNameValuePair("session_id", session_id));
		idparams.add(new BasicNameValuePair("client_id", "6872216"));
		idparams.add(new BasicNameValuePair("user_id", "6872216"));
		idparams.add(new BasicNameValuePair("shell_type", "WT2"));
		UrlEncodedFormEntity entityId = new UrlEncodedFormEntity(idparams, Consts.UTF_8);
		rLoginId.setEntity(entityId);
		CloseableHttpResponse doLoginId = httpclient.execute(rLoginId,context);
		/*
		for (Header header : doLoginId.getAllHeaders()){
			System.out.println(header.toString());
		}
		System.out.println(doLoginId.getStatusLine());
		
		System.out.println(EntityUtils.toString(doLoginId.getEntity()));
		*/
		
		doLoginId.close();
		
		
		
		int index = welcomePage.indexOf("BrokerInstanceId");	
		String brokerInstanceId = welcomePage.substring(index+19,welcomePage.indexOf(",",index+20)-1);
		
		
		
		
		
		
		
		
		
		String _poll = URLEncoder.encode("[{\"i\":\""+brokerInstanceId+"\",\"x\":6872216,\"s\":7,\"c\":0,\"r\":true,\"lid\":\"es\",\"cid\":\"en-GB\",\"tid\":0}]");
		//String _poll = URLEncoder.encode("[{\"i\":\"dfdsf\",\"x\":6872216,\"s\":7,\"c\":0,\"r\":true,\"lid\":\"es\",\"cid\":\"en-GB\",\"tid\":0}]");
		//System.out.println(_poll);
		//System.out.println(URLDecoder.decode(_poll));
		HttpGet pollGet = new HttpGet ("https://cfdsydivisas.selfbank.es/sim/MessageBrokerService.asmx/PollGet?request="+_poll);
		pollGet.addHeader("Content-Type", "application/json; charset=utf-8");
		pollGet.addHeader("X-Requested-With","XMLHttpRequest");
		CloseableHttpResponse pollRequest = httpclient.execute(pollGet,context);
		/*
		for (Header header : pollRequest.getAllHeaders()){
			System.out.println(header.toString());
		}
		System.out.println(pollRequest.getStatusLine());
		
		System.out.println(EntityUtils.toString(pollRequest.getEntity()));
		*/
		pollRequest.close();
		
		
		
		HttpPost widget2 = new HttpPost("https://cfdsydivisas.selfbank.es/sim/SingleCallsService.asmx/RegisterPageLoadEvent");
		widget2.addHeader("Content-Type", "application/json; charset=utf-8");
		widget2.addHeader("X-Requested-With","XMLHttpRequest");
		StringEntity stringEntity2 = new StringEntity("{\"pageLoadTime\":11949,\"appLoadTime\":14074}",ContentType.create("application/json"));
		widget2.setEntity(stringEntity2);
		CloseableHttpResponse ibexvalue2 = httpclient.execute(widget2,context);
		/*
		for (Header header : ibexvalue2.getAllHeaders()){
			System.out.println(header.toString());
		}
		System.out.println(ibexvalue2.getStatusLine());
		
		System.out.println(EntityUtils.toString(ibexvalue2.getEntity()));
		*/
			
		ibexvalue2.close();
		
		
		
		HttpGet logonValidation2 = new HttpGet("https://cfdsydivisas.selfbank.es/eqr/PartnerConfigService.ashx?json");
		logonValidation2.addHeader("X-Requested-With","XMLHttpRequest");
		/*
		for (Cookie cookie : cookieStore.getCookies()){
			System.out.println(cookie.toString());
		}
		*/
		CloseableHttpResponse oLogonValidation2 = httpclient.execute(logonValidation2,context);
		/*
		for (Header header : oLogonValidation2.getAllHeaders()){
			System.out.println(header.toString());
		}
		System.out.println(oLogonValidation2.getStatusLine());
		
		System.out.println(EntityUtils.toString(oLogonValidation2.getEntity()));
		
		
		{"request":[{"i":"123a83593f9944f69b027bc83f93f9b7b41","x":6872216,"s":7,"c":2,"t":"35.00","f":"350"
},[2,6,{"priceMask":247879614,"instrumentType":8,"uic":1669147,"symbol":"","account":"6872216","amount"
:0,"positionId":0,"priceExtras":[24,{"exchange":44}]},7],[100,95,"widget2444560axacedx4ec9x8f5exe768fabda823"
,[[0,[{"Uic":1669147,"InstrumentType":8}]]],7]]}


		*/
		
		oLogonValidation2.close();
		
		HttpGet logonValidation = new HttpGet("https://sim.logonvalidation.net/spheartbeat/"+String.valueOf(oDate.getTime()));
		CloseableHttpResponse oLogonValidation = httpclient.execute(logonValidation,context);
		oLogonValidation.close();

		
		HttpPost widget = new HttpPost("https://cfdsydivisas.selfbank.es/sim/MessageBrokerService.asmx/Poll");
		widget.addHeader("Content-Type", "application/json; charset=utf-8");
		widget.addHeader("X-Requested-With","XMLHttpRequest");
		widget.addHeader("Origin","https://cfdsydivisas.selfbank.es");	
		widget.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.81 Safari/537.36");
		StringEntity stringEntity = new StringEntity("{\"request\":[{\"i\":\""+brokerInstanceId+"\",\"x\":6872216,\"s\":7,\"c\":1,\"t\":\"26.00\",\"f\":\"300\"},[20,1,[6872216],7],[15,2,[1669147,8],7],[6,3,[],7],[11,4,[],7],[21,5,[],7],[100,7,\"\",[true],7]]}",ContentType.create("application/json"));
		widget.setEntity(stringEntity);
		CloseableHttpResponse ibexvalue = httpclient.execute(widget,context);
		
		
		for (Header header : ibexvalue.getAllHeaders()){
			System.out.println(header.toString());
		}
		System.out.println(ibexvalue.getStatusLine());
		
		System.out.println(EntityUtils.toString(ibexvalue.getEntity()));
		
		
		//FileUtils.writeStringToFile(new File("resources/htmloutput/output.html"), EntityUtils.toString(ibexvalue.getEntity()));
		
		ibexvalue.close();
		return "";
	}
	
	public static void main (String... args){
		Test test = new Test();
		try {
			test.test();
		} catch (IOException | KeyManagementException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
