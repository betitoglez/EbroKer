package com.alberto.push;

import org.joda.time.DateTime;

import com.alberto.TradingDay;
import com.alberto.indicators.Broker;
import com.is_teledata.mdg.push.PushCallback;
import com.is_teledata.mdg.push.PushObject;
import com.is_teledata.mdg.push.Subscription;

import eu.verdelhan.ta4j.Tick;

public class Handler implements PushCallback {

	TradingDay tradingDay;
	Broker broker;
	int currentMinute = -1;
	
	private double _open,_high,_low,_close;
	
	public Handler(TradingDay tradingDay) {
		// TODO Auto-generated constructor stub
		this.tradingDay = tradingDay;
	}
	
	public Handler(Broker broker) {
		// TODO Auto-generated constructor stub
		this.broker = broker;
		_open = 0;
		_high=0;
		_low=0;
		_close=0;
	}

	@Override
	public void callback(PushObject obj, Subscription subs) {
		// TODO Auto-generated method stub
		if (obj.isValid()) {
			//System.out.println(obj.getFormattedValue("ID_NOTATION") + ";OK;" + obj.getFormattedValue("CODE_EXCHANGE") + ";"
			//			+ obj.getRawValue("PRICE") + ";" + obj.getRawValue("DATETIME_PRICE")+ ";" + obj.getRawValue("CODE_QUALITY_PRICE") + "\r\n");
			if (null != tradingDay){
				Tick tick = new Tick(DateTime.now().plusHours(2), obj.getFloatValue("PRICE"), obj.getFloatValue("PRICE"), obj.getFloatValue("PRICE"), obj.getFloatValue("PRICE"), 1);
				tradingDay.addTick(tick);
				tradingDay.trade();
			}
			
			if (null != broker){
			
				
				DateTime datetime = new DateTime(obj.getDateValue("DATETIME_PRICE"));
				int _minute = datetime.getMinuteOfDay();
				
				if (_minute > currentMinute){
					if (currentMinute>0 && currentMinute%5==0){	
						
						broker.ohlcChart.addCandle(datetime, _open, _high, _low, _close);
						_close = obj.getFloatValue("PRICE");
						_high = obj.getFloatValue("PRICE");
						_low = obj.getFloatValue("PRICE");
					}
					_open = obj.getFloatValue("PRICE");
					currentMinute = _minute;
				}else{
					_close = obj.getFloatValue("PRICE");
					_high = obj.getFloatValue("PRICE") > _high ? obj.getFloatValue("PRICE") : _high;
					_low = obj.getFloatValue("PRICE") < _low || _low == 0 ? obj.getFloatValue("PRICE") : _low;
					return;
				}
				broker.adx.update(obj.getFloatValue("PRICE"),obj.getFloatValue("PRICE"), obj.getFloatValue("PRICE"));
	        	broker.prices.add(obj.getFloatValue("PRICE"));
	        	broker.status (datetime);
		    	broker.check(datetime);
		    	broker.chart.addPoint(datetime, obj.getFloatValue("PRICE"));
		    	broker.rsi.update(obj.getFloatValue("PRICE"));
			}
		}

	}

}
