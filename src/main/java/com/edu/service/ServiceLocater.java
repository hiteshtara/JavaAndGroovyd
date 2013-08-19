package com.edu.service;
import com.edu.service.Service;


public class ServiceLocater {
public static Cache cache;
static {
	cache = new Cache();
}
public  static Service getService(String jndiName){
	Service service=cache.getService(jndiName);
	if(service!=null){
		return service;
	}
	InitialContext context= new InitialContext();
	Service serviceName=(Service)context.lookup(jndiName);
	cache.addService(serviceName);
	return  serviceName;
	
}
}
