package test.java.task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.*;

class LoginFormTest {

//    private LoginForm loginForm;
//
//    @BeforeEach
//    void setUp() {
//        loginForm = new LoginForm("testUser", "test@example.com", "password123");
//    }
//
//    @Test
//    @DisplayName("LoginForm class should be public")
//    void classIsPublic() {
//        int classModifiers = LoginForm.class.getModifiers();
//        assertTrue(Modifier.isPublic(classModifiers), "LoginForm class should be public");
//    }
//
//    @Test
//    @DisplayName("LoginForm should inherit from RegistrationForm")
//    void inheritsFromRegistrationForm() {
//        assertTrue(RegistrationForm.class.isAssignableFrom(LoginForm.class), "LoginForm should inherit from RegistrationForm");
//    }
//
//    @Test
//    @DisplayName("LoginForm should have a no-arg constructor inherited from RegistrationForm")
//    void defaultConstructorIsInherited() throws NoSuchMethodException {
//        Constructor<LoginForm> constructor = LoginForm.class.getDeclaredConstructor();
//        assertNotNull(constructor, "LoginForm should have a no-arg constructor inherited from RegistrationForm");
//        assertTrue(Modifier.isPublic(constructor.getModifiers()), "Constructor should be public");
//    }
//
//    @Test
//    @DisplayName("LoginForm should have a parameterized constructor inherited from RegistrationForm")
//    void parameterizedConstructorIsInherited() throws NoSuchMethodException {
//        Constructor<LoginForm> constructor = LoginForm.class.getDeclaredConstructor(String.class, String.class, String.class);
//        assertNotNull(constructor, "LoginForm should have a parameterized constructor inherited from RegistrationForm");
//        assertTrue(Modifier.isPublic(constructor.getModifiers()), "Constructor should be public");
//    }
//    @Test
//    @DisplayName("Successful authentication")
//    void testSuccessfulAuthentication() {
//        assertDoesNotThrow(() -> assertTrue(loginForm.authenticate("testUser", "password123"),
//                "Authentication should succeed with correct credentials."));
//    }
//
//    @Test
//    @DisplayName("Authentication fails with incorrect password")
//    void testAuthenticationFailsWithIncorrectPassword() {
//        Exception exception = assertThrows(AuthenticationException.class, () -> loginForm.authenticate("testUser", "wrongPassword"));
//        assertEquals("Incorrect password.", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Account is locked after 3 unsuccessful login attempts")
//    void testAccountLockoutAfterThreeFailedAttempts() {
//        LoginForm loginForm = new LoginForm("testUser", "test@example.com", "correctPassword");
//        // Імітація трьох невдалих спроб входу
//        for (int i = 0; i < 3; i++) {
//            Exception exception = assertThrows(AuthenticationException.class, () -> loginForm.authenticate("testUser", "wrongPassword"));
//            String expectedMessage = i < 2 ? "Incorrect password." : "Account is locked due to too many login attempts.";
//            assertEquals(expectedMessage, exception.getMessage());
//        }
//
//        Exception lockedException = assertThrows(AuthenticationException.class, () -> loginForm.authenticate("testUser", "correctPassword"));
//        assertEquals("Account is locked due to too many login attempts.", lockedException.getMessage());
//    }
//
//    @Test
//    @DisplayName("Changing password successfully")
//    void testChangePasswordSuccessfully() {
//        assertDoesNotThrow(() -> {
//            loginForm.changePassword("password123", "newPassword123");
//            assertTrue(loginForm.authenticate("testUser", "newPassword123"),
//                    "Authentication should succeed with new password.");
//        });
//    }
//
//    @Test
//    @DisplayName("Changing password fails with incorrect current password")
//    void testChangePasswordFailsWithIncorrectCurrentPassword() {
//        Exception exception = assertThrows(AuthenticationException.class, () -> loginForm.changePassword("wrongCurrentPassword", "newPassword123"));
//        assertEquals("Incorrect current password.", exception.getMessage());
//    }
//    @Test
//    @DisplayName("Changing password with new password that fails validation")
//    void testChangePasswordWithInvalidNewPassword() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> loginForm.changePassword("password123", "short"));
//        assertEquals("The new password must be at least 8 characters long.", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Authentication fails with correct password after account is locked")
//    void testAuthenticationFailsAfterAccountIsLocked() {
//
//        for (int i = 0; i < 3; i++) {
//            assertThrows(AuthenticationException.class, () -> loginForm.authenticate("testUser", "wrongPassword"));
//        }
//
//        Exception exception = assertThrows(AuthenticationException.class, () -> loginForm.authenticate("testUser", "password123"));
//        assertEquals("Account is locked due to too many login attempts.", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Attempting to change password for locked account")
//    void testChangePasswordForLockedAccount() {
//        for (int i = 0; i < 3; i++) {
//            assertThrows(AuthenticationException.class, () -> loginForm.authenticate("testUser", "wrongPassword"));
//        }
//
//        Exception exception = assertThrows(AuthenticationException.class, () -> loginForm.changePassword("password123!", "newPassword123!"));
//        assertEquals("Account is locked due to too many login attempts.", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Authentication with empty username or password")
//    void testAuthenticationWithEmptyCredentials() {
//        assertThrows(AuthenticationException.class, () -> loginForm.authenticate("", "password123"), "Username cannot be empty.");
//        assertThrows(AuthenticationException.class, () -> loginForm.authenticate("testUser", ""), "Password cannot be empty.");
//    }
//    @Test
//    void testAuthenticationWithEmptyUsernameThrowsException() {
//        LoginForm loginForm = new LoginForm();
//        assertThrows(AuthenticationException.class, () -> loginForm.authenticate("", "somePassword"));
//    }
}