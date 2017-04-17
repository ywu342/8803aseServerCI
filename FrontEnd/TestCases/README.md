# Automated Testing
The folder Automated Test is a JUnit project containing test cases for the login and register features. 

# Technical details 
We used JUnit test cases along with Selenium library to create test case of the webapp. We separate the test cases into two classes, each combining test cases for a feature. Here we test two features: login and registration.<br />

Selenium library uses firefox browser to simulate navigation (filling inputbox, simulating clicks, finding elements in HTML DOM...) in the website.<br />

For each features we established a number of test cases:

1. Missing/Incomplete input
2. Incorrect input
3. Successful input

We create a JUnit test function for each test case and simulate navigation in the website using Selnuim Driver using the following steps:

1. Creating a new browser (driver = new FirefoxDriver())
2. Navigate to the website using (driver.get(URL))
3. Fill the login/register form
	1. Find the inputs in the forms (username = driver.findElement(By.name("login-username")))
	2. Simulate typing in the inputs (username.sendKeys("example"))
4. Submit the form (simulate a click on submit button using submit.click())
5. Wait for response 
	1. Set a timeout of 10 seconds (WebDriverWait wait = new WebDriverWait(driver, 10))
	2. Wait until desired output shows up (wait.until(ExpectedConditions.alertIsPresent()))
6. Check if response corresponds to expected output (assertEquals("Expected output", "Actual output"))

For more details please refer to the commented source code of the test cases.

# How to run it
1. Make sure JUnit is installed.
2. Download and Import the project to eclipse
3. Install Selenium Java from the following [website](http://www.seleniumhq.org/download/)
	1. Download and unzip the folder
	2. Add all jar files as external jars to the project (including those in subfolder /lib)
4. Download the latest version of geckodriver from [here](https://github.com/mozilla/geckodriver/releases)
	1. Download the latest executable release and extract it 
	2. Navigate to LoginTestSuite.java and RegisterTestSuite.java and set the path to geckodriver System.setProperty("webdriver.gecko.driver", "<PATH_TO_GECKO_EXEC>");
5. Navigate to (Login|Register)TestSuite in src folder and run it.
6. See output as JUnit test cases

# Test Cases Details
## Login Test cases
Following are the cases tested in the project:

1. badFormatTest: The format of the email is corrupted.
2. emptyCredentialsTest: The credentials are empty.
3. badCredentialsTest: The credentials does not exist or the password does not match the email.
4. goodCredentialsTest: The credentials are valid.

Expected output:

* For 1-2, the output should be an alert saying that the login information are incorrect meaning the format is not valid or the form is empty.
* For 3, the output should be an alert saying unsuccessful login.
* For 4, the output should be an alert saying successful login.

## Register Test cases

5. emptyRegisterTest: The registration credentials are empty.
6. badEmailRegisterTest: The format of the email is incorrect.
7. badPasswordTest: The confirm password and password fields did not match.
8. existingEmailTest: The email used for registration exists already.
9. goodRegisterTest: The credentials are valid.

Expected output:

* For 5-7, the output should be an alert saying that the registration information are incorrect meaning the format is not valid or the form is empty.
* For 8, the output should be an alert indicating that the email has already been registered
* For 9, the output should be an alert saying successful registration.

	
