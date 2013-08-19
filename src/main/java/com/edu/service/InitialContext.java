package com.edu.service;
public class InitialContext {

	public Object lookup(String jndiName){
		
		if(jndiName.equalsIgnoreCase("SERVICE1")){
		System.out.println("executing service1 ");
		
		return new Service1();
		}else if(jndiName.equalsIgnoreCase("service2")){
			
			System.out.println("executing service2");
			return  new Service2();
		}
		return null;
	}
}
