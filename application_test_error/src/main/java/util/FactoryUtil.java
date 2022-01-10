package util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class FactoryUtil {
	
	private FactoryUtil() {}
	
	public static String getCommand(HttpServletRequest request) {
		return request.getRequestURI().substring(request.getContextPath().length()+1);
	}
	
	public static void nextPage(HttpServletRequest request,HttpServletResponse response,String nextPage) throws ServletException, IOException {
		if(nextPage != null) {
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
	}
}
