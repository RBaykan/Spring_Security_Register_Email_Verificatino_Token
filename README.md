# Spring Security Register with Email Verification



## Description
- This is a continuation of the previous project; https://github.com/RBaykan/Spring_Security_Register_Validate_User
- In the previous project, when a user is successfully registered in the database, they are not immediately activated, and an event called `OnRegisterationCompleteComplete` is triggered. The relevant user and registration link are registered in the `OnRegisterationCompleteComplete`. This event generates a verification token with a validity period and saves it in the database for verification. The token is associated with the corresponding user.

- The token information is appended as a parameter to the registration link and sent to the user's registered email address.
When the user clicks on the link, the token information from the link is queried in the database, and the token verification process is carried out. If the token is valid, the user is activated.

## Configure Your SMTP
- You can specify your SMTP information either in the `application.yml` file or by defining `JavaMailSender` as a Bean in the `MailConfiguration.java` file. I have clearly indicated the fields you need to fill out in these two files. Both configurations will be converted into a `JavaMailSender` object by Spring.

- Remember that the converted objects need to be connected to the Event Listener classes by Spring. I have established this connection using the `constructor` method.

## Steps to Run the Project
1. **Clone the repository**:
```bash
git clone https://github.com/RBaykan/Spring_Security_Register_Email_Verification_Token.git
```
2. **Navigate to the project directory**:
```bash
cd Spring_Security_Register_Email_Verification_Token
```
3. **Dependencies and build the project**:
```bash
mvn clean install
```
- Run the application:
```bash
mvn spring-boot:run
```
The application should now be running on `http://localhost:8080.`

4. Follow the `CreateDTO` object and prepare a JSON file accordingly, then send a POST request to the following address: `http://localhost:8080/api/user`.
   This way, when a new user is created, the token will be sent to your email address.



