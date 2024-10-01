# Welcome to Practical Test Automation 
## Task 4 Extending the RegistrationForm Class:

#### The `LoginForm` Class:

- Extends the `RegistrationForm` class, inheriting fields for username, email, and password.
- Includes an `authenticate` method to verify the match between the username and password. If three unsuccessful login attempts occur, the user account is locked, and an exception is thrown with a blocking message.
- Contains a `changePassword` method that allows the user to change their password after verifying the old password.

#### Error Handling:

- Login attempts with an incorrect password trigger an exception with a corresponding message.
- Exceeding the number of login attempts (three attempts) leads to the account being locked and an exception being thrown with a blocking message.

#### Limit on the Number of Login Attempts:

- The system monitors the number of unsuccessful login attempts for each user. The account is locked after three unsuccessful attempts.
