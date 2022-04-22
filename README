# The test task in CUBICFOX #

Your task is to implement a REST endpoint testing software in java 8+. Any other framework or technology can be used. The suggested database is PostgreSQL.

Your code has to call the following endpoint, and handle all the data you will get back in the response.
https://jsonplaceholder.typicode.com/users

## How to configure your project before run? ##
1. Download the PostgreSQL server.
2. Create a server with the name 'postgres' and the password 'password'.
3. Create a user with the name 'postgres' and the password 'password'.
4. Create a database with the name like 'cubicfox'.
5. Assign your user to the database.

## How to run the server? ##
1. Clone the project by the link.
2. Run the starter class named like TestApplication.
3. Go to google chrome or safari and type in the search section 'http://localhost:8080'.
It will do a get request on API and save from there a JSON data to our database.
If you need to configure a specific path for that please follow to controller and will type under the method named saveUsers in the class UsersResource your appropriate path, nearly get mapping annotation.
4. Go to a database, refresh your schema and check the data on populating.

WARN!!! If you twice do a get request on the started endpoint you will get an exception with duplicated data. Firstly you need to call the method clearAll() in the UserService class or just manually remove our tables.

## How to run the unit test. ##

WARN!!! If you run your test without any existed tables then you can do below actions else, please, manually drop all tables.

1. Go to test folder.
2. Click the right button on the folder 'com.cubicfox' and execute all scenarios.
3. Make sure that you have all green steps.
