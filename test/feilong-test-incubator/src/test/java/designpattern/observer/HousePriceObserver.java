package designpattern.observer;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HousePriceObserver implements Observer{

	private static final Logger	log	= LoggerFactory.getLogger(HousePriceObserver.class);

	private String				name;

	public HousePriceObserver(String name){
		this.name = name;
	}

	public void update(Observable obj,Object arg){

		if (log.isDebugEnabled()){
			log.debug("update:" + obj.toString());
		}
		if (arg instanceof Float){ // 判断参数类型  
			log.debug(this.name + "观察到价格更改为：" + arg);
		}
	}
}