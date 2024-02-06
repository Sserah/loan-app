This project is for loan application

Stacks used
- Java17
- Postgress as database

## Server runnning on port 7878

1) To run it create a new database in postgres called `loan`
2) in the application.properties file modify the database configs. (db pass,username)
2) you cal call the exposed API is postman running on port `7878` eg.  http://localhost:7878/loan/application


_API ENDPOINTS_
1) Register user
POST -  http://localhost:7878/loan/application
BODY - `{
   "firstName" : "Abraham",
   "age" : 21,
   "lastName" : "Lugonzo",
   "idNumber" : 37238931
   }`

RESPONSE EXAMPLE - `{
"status": "OK",
"message": "User successfully registered"
}`

2) CheckBalance API
GET - http://localhost:7878/loan/balance/{idNumber}


Name : Abraham Lugonzo
id : 37287743
Phone: 0713568753
Email : abramlugonzo@gmail.com# Loan-Application-System
