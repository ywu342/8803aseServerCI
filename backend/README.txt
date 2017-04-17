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
URL: http://1-dot-thinking-return-161419.appspot.com/userregistration
JSON: 
{
"name": "Ricket",
    "email": "jiji@gmail.com",
    "password": "bbt312rr33"
}

Response:
{
  "code": 200,
  "descrip": "User Created Successfully",
  "smallUser": {
    "key": {
      "kind": "User",
      "id": 6473924464345088
    },
    "name": "Ricket",
    "email": "jiji@gmail.com",
    "password": "bbt312rr33"
  }
}

If the user being created has an email that belongs to an existing user:

Response:
{
  "code": 400,
  "descrip": "User could not be created Successfully.User with email id already exists",
  "smallUser": {
    "name": "Ricket",
    "email": "jiji@gmail.com",
    "password": "bbt312rr33"
  }
}



User Login: 
URL: http://1-dot-thinking-return-161419.appspot.com/userregistration?email=anuanu79ww39@gmail.com&password=bbt312

Response:

{
  "code": 200,
  "descrip": "User Found",
  "smallUser": {
    "key": {
      "kind": "User",
      "id": 5348024557502464
    },
    "name": "James",
    "email": "local@gmail.com",
    "password": "books"
  }
}


If the user is not found:
Response:
{
  "code": 404,
  "descrip": "User not found"
}


Test Cases:

User Registration: 
URL: http://1-dot-thinking-return-161419.appspot.com/userregistration
Test Case 1: Happy POST

Request JSON: 
{"name": "Kellie",
"email": "kell@gmail.com",
"password": "Kell"
}

Response JSON:
{
  "code": 200,
  "descrip": "User Created Successfully",
  "smallUser": {
    "key": {
      "kind": "User",
      "id": 5655869022797824
    },
    "name": "Kellie",
    "email": "kell@gmail.com",
    "password": "Kell"
  }
}

Test case 2: User with duplicate email

Request JSON:
{"name": "Kellie",
"email": "kell@gmail.com",
"password": "Kell"
}

Response JSON:
{
  "code": 400,
  "descrip": "User could not be created Successfully.User with email id already exists",
  "smallUser": {
    "name": "Kellie",
    "email": "kell@gmail.com",
    "password": "Kell"
  }
}

Test case 3: User with no name:

Request JSON:
{"name": "",
"email": "kell1@gmail.com",
"password": "Kol"
}

Response JSON:
{
  "code": 401,
  "descrip": "User could not be created Successfully. Name is null.",
  "smallUser": {
    "name": "",
    "email": "kell1@gmail.com",
    "password": "Kol"
  }
}

Test case 4: User with no password:

Request JSON:
{"name": "huhu",
"email": "kell123@gmail.com",
"password": ""
}

Response JSON:
{
  "code": 402,
  "descrip": "User could not be created Successfully. Password is null.",
  "smallUser": {
    "name": "huhu",
    "email": "kell123@gmail.com",
    "password": ""
  }
}

Test case 5: User with no email:

Request JSON:
{"name": "huhu",
"email": "",
"password": "eeeee"
}


Response JSON:
{
  "code": 403,
  "descrip": "User could not be created Successfully. Email is null.",
  "smallUser": {
    "name": "huhu",
    "email": "",
    "password": "eeeee"
  }
}
