package com.gatech.userreg;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class DBUtilsUser {
	public static final Logger _logger = Logger.getLogger(DBUtilsUser.class.getName());

	/**
	 * This method persists a record to the database.
	 */
	public static void saveUser(User user) throws Exception {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(user);
			_logger.log(Level.INFO, "Account has been saved.");
		} catch (Exception ex) {
			_logger.log(Level.SEVERE, "Account has not been saved.. Reason : " + ex.getMessage());
			throw ex;
		} finally {
			pm.close();
		}
	}

	public static User getUser(String name, String email, String password) {

		PersistenceManager pm = null;
		User usr = null;
		// Get the current month and year
		try {

			pm = PMF.get().getPersistenceManager();
			Query query = null;
			query = pm.newQuery(User.class, " name == givenName && email == givenEmail && password == givenPassword ");

			// declare parameters used above
			query.declareParameters("String givenName, String givenEmail, String givenPassword");

			List<User> z = (List<User>) query.executeWithArray(name, email, password);

			return z.get(0);
		} catch (Exception ex) {
			return null;
		} finally {
			pm.close();
		}
	}
}