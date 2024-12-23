package main.java.task4;

public class LoginForm extends RegistrationForm {
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private int loginAttempts = 0;
    private boolean isLocked = false;

    public LoginForm(String username, String email, String password) {
        super(username, email, password);
    }

    public LoginForm() {
        super();
    }

    public boolean authenticate(String username, String password) {
        if (isLocked) {
            throw new AuthenticationException("Account is locked due to too many login attempts.");
        }

        if (username.isEmpty()) {
            throw new AuthenticationException("Username cannot be empty.");
        }

        if (password.isEmpty()) {
            throw new AuthenticationException("Password cannot be empty.");
        }

        if (!this.getUsername().equals(username) || !this.getPassword().equals(password)) {
            loginAttempts++;
            if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                isLocked = true;
                throw new AuthenticationException("Account is locked due to too many login attempts.");
            }
            throw new AuthenticationException("Incorrect password.");
        }

        loginAttempts = 0;
        return true;
    }

    public void changePassword(String currentPassword, String newPassword) {
        if (isLocked) {
            throw new AuthenticationException("Account is locked due to too many login attempts.");
        }

        if (currentPassword.isEmpty() || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Passwords cannot be empty.");
        }

        if (!this.getPassword().equals(currentPassword)) {
            throw new AuthenticationException("Incorrect current password.");
        }

        if (newPassword.length() < 8) {
            throw new IllegalArgumentException("The new password must be at least 8 characters long.");
        }

        this.setPassword(newPassword);
    }
}
