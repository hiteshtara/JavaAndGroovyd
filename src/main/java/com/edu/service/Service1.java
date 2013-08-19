package com.edu.service;
import com.edu.service.Service;


public class Service1  implements Service{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "service1";
	}

	@Override
	public void execute() {
		System.out.println("executing service1");

}
}