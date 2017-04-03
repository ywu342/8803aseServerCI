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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String name = "";
		String strResponse = "";
		UserError userError = new UserError();
		String email = "";
		String password = "";
		try {
			email = req.getParameter("email");
			password = req.getParameter("password");
			//name = req.getParameter("name");
			
			User usr = DBUtilsUser.getUser(email, password);
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
