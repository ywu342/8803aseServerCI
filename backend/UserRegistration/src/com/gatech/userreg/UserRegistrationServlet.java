package com.gatech.userreg;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class UserRegistrationServlet extends HttpServlet {
	public static final Logger _logger = Logger.getLogger(UserRegistrationServlet.class.getName());

	//Add a GET userID that returns
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		StringBuffer requestURL = req.getRequestURL();
		String name = "";
		String strResponse = "";
		UserError userError = new UserError();
		String email = "";
		String password = "";
		try {
			email = req.getParameter("email");
			password = req.getParameter("password");
			name = req.getParameter("name");
			
			User usr = DBUtilsUser.getUser(name, email, password);
			if(usr==null)	
				throw new Exception();
			userError.setSmallUser(usr);
			userError.setCode(200);
			userError.setDescrip("User Found");
			
			String json = new Gson().toJson(userError);
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json);
		    
			
		} catch (Exception ex) {
			userError.setCode(404);
			userError.setDescrip("User not found");
			String json = new Gson().toJson(userError);
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json);
		}
		
		//resp.getWriter().println(strResponse);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		BufferedReader reader = req.getReader();
		Gson gson = new Gson();

		User usr = gson.fromJson(reader, User.class);
		UserError userError = new UserError();

		reader.close();
		if(usr.getName().isEmpty() || usr.getName()==null){
			userError.setSmallUser(usr);
			userError.setCode(401);
			userError.setDescrip("User could not be created Successfully Name is null.");
			String json = new Gson().toJson(userError);
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json);
		}
		
		else if(usr.getPassword().isEmpty() || usr.getPassword()==null){
			userError.setSmallUser(usr);
			userError.setCode(402);
			userError.setDescrip("User could not be created Successfully Password is null.");
			String json = new Gson().toJson(userError);
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json);
		}
		
		else if(usr.getEmail().isEmpty() || usr.getEmail() ==null){
			userError.setSmallUser(usr);
			userError.setCode(403);
			userError.setDescrip("User could not be created Successfully Email is null.");
			String json = new Gson().toJson(userError);
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json);
		}
		
		else{

		try {
			User user = DBUtilsUser.getUser(usr.getName(),usr.getEmail(), usr.getPassword());
			if(user!=null){
				throw new Exception("User with email id already exists");
			}
			DBUtilsUser.saveUser(usr);
			userError.setSmallUser(usr);
			userError.setCode(200);
			userError.setDescrip("User Created Successfully");
			String json = new Gson().toJson(userError);
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json);

		} catch (Exception ex) {
			_logger.severe("Error in creating account : " + usr.getName() + "," + usr.getEmail() + " : "
					+ usr.getPassword() + ex.getMessage());
			userError.setSmallUser(usr);
			userError.setCode(400);
			userError.setDescrip("User could not be created Successfully."+ex.getMessage());
			String json = new Gson().toJson(userError);
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().write(json);
		}
		}

	}
}
