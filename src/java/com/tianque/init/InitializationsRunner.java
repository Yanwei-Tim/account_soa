package com.tianque.init;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class InitializationsRunner {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private List<Initialization> inits=new ArrayList<Initialization>();

	protected void executeInitialization() throws Exception{
		for (Initialization init:inits){
			long startTime=System.currentTimeMillis();
			init.init();
			logger.error(init.getClass().getName()+":"+(System.currentTimeMillis()-startTime));
		}
	}
	
	protected void addInitialization(Initialization init){
		if (!inits.contains(init)){
			inits.add(init);
		}
	}

}
