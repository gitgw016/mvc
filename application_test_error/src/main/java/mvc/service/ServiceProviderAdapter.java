package mvc.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ServiceProviderAdapter<T> implements ServiceProvider<T>{

	@Override
	public ArrayList<T> getList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public T read(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void updateReadCount(HttpServletRequest request) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
