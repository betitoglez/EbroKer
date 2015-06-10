package com.alberto.push;

import com.is_teledata.mdg.MDGConfigException;
import com.is_teledata.mdg.push.PushConfig;
import com.is_teledata.mdg.push.PushSession;
import com.is_teledata.mdg.push.Subscription;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
		String idApp = "12749";
		String idPass = "kPmK9sscbX4";;
		String user = "TechUser2BBVA";
		String userPass = "TechBBVA2User";
		//String ID_NOTATION[] = {"324911"};
		String ID_NOTATION = "324911";
		
		try {
			/*
			PushSession session = PushSession.getUserPushInstanceByLogin(
					PushConfig.getPushConfig(), idApp, user,
					userPass);
			
			Subscription subscription = new Subscription("prices/quote?VERSION=2&ID_NOTATION="+ID_NOTATION,new Handler());
			
			session.subscribe(subscription);
			*/
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
