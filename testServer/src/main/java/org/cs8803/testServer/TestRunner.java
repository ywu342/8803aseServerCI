package org.cs8803.testServer;
import java.io.File;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello world");
		Result result = JUnitCore.runClasses(TestServer.class);
		
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      
	      try{
		      File yourFile = new File("score.txt");
		      yourFile.createNewFile(); // if file already exists will do nothing       
	      }catch(Exception e)
	      {
	    	 
	      }

	    //result.wasSuccessful();

	}

}
