
# PayMyBuddy
A web application allowing you to simply transfer money with other users.

# Informations
## Author: Ghazi Bouzazi/ Java application developper student / OpenClassrooms

# Running
Application running on port 8000 / with a MySQL database on port 3306
Create database named "paymybuddy" then you must run sql scripts, in order: sql-script.sql located in src/main/resources/scripts
# Setuping test environment
Same as previous step except you must create a database named "paymybuddytest", you can also modify database settings in application.properties located in src/test/resources/
# Business logic

#### One user can have bank accounts
#### At each transaction, a fee is collected
#### Fee rate is 0.5%
# Class diagram
![A Star-Based LAN](https://user-images.githubusercontent.com/42503736/122658863-5eaff980-d172-11eb-889d-6444c62b61d5.png)

# Database schema
![paymybuddy](https://user-images.githubusercontent.com/42503736/122658944-ebf34e00-d172-11eb-801b-b192adc44c25.png)
