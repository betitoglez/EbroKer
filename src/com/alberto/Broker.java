package com.alberto;

import java.util.Date;

import org.joda.time.DateTime;

import com.alberto.push.Handler;
import com.is_teledata.mdg.MDGConfig;
import com.is_teledata.mdg.MDGConfigException;
import com.is_teledata.mdg.MDGObject;
import com.is_teledata.mdg.MDGSession;
import com.is_teledata.mdg.push.PushConfig;
import com.is_teledata.mdg.push.PushSession;
import com.is_teledata.mdg.push.Subscription;

import eu.verdelhan.ta4j.Tick;

public class Broker {
	
	PushSession session;
	Subscription subscription;
	TradingDay tradingDay;
	
	String idApp = "12749";
	String idPass = "kPmK9sscbX4";
	String user = "TechUser2BBVA";
	String userPass = "TechBBVA2User";
	//String ID_NOTATION[] = {"324911"};
	String ID_NOTATION = "324911";
	
	String pullApp      =  "id10946";
	String pullPass     =  "jhdfcpoa";
	
	MDGSession mdgSession;
	
	public Broker() {
		// TODO Auto-generated constructor stub
		
		
		idApp    = "10946";
		user     = "technical";
		userPass = "technical";
		
		try {
			
			Date _date = new Date();
			mdgSession = MDGSession.getInstance(MDGConfig.getMDGConfig(), idApp, pullApp, pullPass); 
			
			tradingDay = new TradingDay(String.valueOf(_date.getTime()));
			historyList(tradingDay);
			
			session = PushSession.getUserPushInstanceByLogin(
					PushConfig.getPushConfig(), idApp, user,
					userPass);
			
			subscription = new Subscription("prices/quote?CODE_QUALITY_PRICE=RLT&VERSION=2&ID_NOTATION="+ID_NOTATION,new Handler(tradingDay));
			
			session.subscribe(subscription);
				
			
		} catch (MDGConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void historyList (TradingDay tradingDay){
	    
		MDGObject history = MDGObject.getInstance(mdgSession, "/prices/history_list?CODE_QUALITY_PRICE=RLT&ID_NOTATION="+ID_NOTATION+"&ID_RESOLUTION=57&VERSION=2&BLOCKSIZE=20000"); 
		int amount = history.getIntValue("AMOUNT").intValue();
        for (int i = 1; i < amount;i++){
        	
        	DateTime datetime = new DateTime( history.getDateTimeValue(i, "DATETIME_LAST").longValue());          	
        	Tick tick = new Tick(datetime.plusHours(2), history.getFloatValue(i,"LAST"), 
        			     history.getFloatValue(i,"LAST"), history.getFloatValue(i,"LAST"), history.getFloatValue(i,"LAST"), 1);
        	tradingDay.addTick(tick);
        	tradingDay.trade();
        }
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Broker eBroker = new Broker();
		
	}

}
