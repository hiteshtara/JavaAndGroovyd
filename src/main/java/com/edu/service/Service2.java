package com.edu.service;
import com.edu.service.Service;


public class Service2   implements Service{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "service2";
	}

	@Override
	public void execute() {
		System.out.println("executing servicee2");
	}

}
