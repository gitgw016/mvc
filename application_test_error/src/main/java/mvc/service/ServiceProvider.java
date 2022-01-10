package mvc.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServiceProvider<T> {
	
	ArrayList<T> getList(HttpServletRequest request);
	
	void write(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException;
	
	T read(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException;
	
	void updateReadCount(HttpServletRequest request) throws IOException,ServletException;
	
	void update(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException;
	
	void delete(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException;

}
