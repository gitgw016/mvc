

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("*.test")
public class AsyncTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String cmd = request.getRequestURI().substring(request.getContextPath().length()+1);
		String nextPage = null;
		if(cmd.equals("javascript.test")) {
			nextPage = "/test/javascript.jsp";
		}else if(cmd.equals("jQuery.test")) {
			nextPage = "/test/jquery.jsp";
		}else if(cmd.equals("user.test")) {
			System.out.println("sample test");
			System.out.println("name : "+name);
			System.out.println("age : "+age);
			// JSON : javascript Object Notation
			// 자바스크립트 오브젝트 타입의 경량 data
			// 사람이 읽고 쓰기도 편하고 기계가 분석하고 생성하기도 용이
			response.setContentType("application/json;charset=utf-8");
			// var obj = {key:value , key : value}
			String json = "{\"name\" : \""+name+"\" , \"age\" : "+age+"}";
			System.out.println(json);
			response.getWriter().print(json);
		}else if(cmd.equals("userList.test")) {
			response.setContentType("application/json;charset=utf-8");
			String json = "[";
			for(int i=0; i<100; i++) {
				json += "{\"name\" : \""+(name+i)+"\" , \"age\" : "+(age+i)+"}";
				if(i != 99) {
					json += ",";
				}
			}
			json+="]";
			System.out.println(json);
			response.getWriter().print(json);
		}if(cmd.equals("gsontest.test")) {
			System.out.println("gsontest.test");
			ArrayList<UserVO> userList = new ArrayList<>();
			for(int i=0; i<100; i++) {
				UserVO vo = new UserVO();
				vo.setName(name+i);
				vo.setAge(Integer.parseInt(age)+i);
				userList.add(vo);
			}
			
			// com.google.gson.Gson
			Gson gson = new Gson();
			String json = gson.toJson(userList);
			System.out.println(json);
			/*
			UserVO result = gson.fromJson(json, UserVO.class);
			System.out.println(result);
			*/
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json);
		}else if(cmd.equals("userXML.test")) {
			System.out.println("xml ");
			response.setContentType("text/xml;charset=utf-8");
			String xml = "<user>";
			xml += "<name>";
			xml += name;
			xml += "</name>";
			xml += "<age>";
			xml += age;
			xml += "</age>";
			xml += "</user>";
			System.out.println(xml);
			response.getWriter().print(xml);
		}
		
		
		if(nextPage != null) {
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
