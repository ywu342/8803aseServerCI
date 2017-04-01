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
		String email = "";
		String password = "";
		UserError response = new UserError();
		try {
			email = req.getParameter("email");
			password = req.getParameter("password");
			name = req.getParameter("name");

			User usr = DBUtilsUser.getUser(name, email, password);
			response.setCode(200);
			response.setData(usr);
			response.setDescrip("User Found");
			
		} catch (Exception ex) {
			response.setCode(400);
			response.setDescrip("Error in retrieving user. " + ex.getMessage());
			
		}
		resp.getWriter().println(response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		BufferedReader reader = req.getReader();
		Gson gson = new Gson();

		User usr = gson.fromJson(reader, User.class);

		reader.close();

		UserError response = new UserError();
		response.setData(usr);
		
		try {
			DBUtilsUser.saveUser(usr);
			response.setCode(100);
			response.setDescrip("Your account has been created");
		} catch (Exception ex) {
			_logger.severe("Error in creating account : " + usr.getName() + "," + usr.getEmail() + " : "
					+ usr.getPassword() + ex.getMessage());
			response.setCode(200);
			response.setDescrip("Error in saving User via web. Reason :" + ex.getMessage());
		}
		resp.getWriter().println(response);

	}
}
