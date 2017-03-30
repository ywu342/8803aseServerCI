Backend - README

Implementation has been performed using JDO. And deploying to Google App
Engine. This is a Java Project. To run, please import to eclipse. Maven
is not required. The libraries outside of typical jars that have been
used are as follows. Please download then and add them to the WEB-INF
folder, in case of error.
https://mvnrepository.com/artifact/com.google.code.gson/gson/2.3.1

Once imported the project is imported in Eclipse, use, run as Web
Application to test the project. Please use PostMan or
AdvancedRestClient for testing the APIs.

User Registration: 
URL: http://localhost:8888/userregistration
JSON: 
{
"name":"Hannah",
"email": "anuanu79ww39@gmail.com",
"password": "bbt312"
}
Response:
Your account has been created.


User Login: 
URL: http://localhost:8888/userregistration?name=Hannah&email=anuanu79ww39@gmail.com&password=bbt312
Response:
User Found: anuanu79ww39@gmail.com Hannah bbt312


