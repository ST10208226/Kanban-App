import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginApplicationTest {

    private LoginApplication login;

    // JUnit 4's @Before is used to set up the test environment before each test
    @Before
    public void setUp() {
        login = new LoginApplication();
        login.firstName = "John";
        login.surname = "Doe";
        login.userName = "valid_user";
        login.password = "Valid123!";
        login.enteredUserName = "valid_user";
        login.enteredPassword = "Valid123!";
    }

    // Testing checkUsername method
    @Test
    public void testCheckUsernameValid() {
        login.userName = "valid_user"; // valid username format
        assertTrue("Username should be valid.", login.checkUsername());
    }

    @Test
    public void testCheckUsernameInvalid() {
        login.userName = "invalid"; // invalid username (no underscore)
        assertFalse("Username should be invalid.", login.checkUsername());
    }

    // Testing checkPasswordComplexity method
    @Test
    public void testCheckPasswordComplexityValid() {
        login.password = "Valid123!"; // valid password format
        assertTrue("Password should meet complexity requirements.", login.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexityInvalid() {
        login.password = "short"; // invalid password (too short)
        assertFalse("Password should not meet complexity requirements.", login.checkPasswordComplexity());
    }

    // Testing registerUser method
    @Test
    public void testRegisterUserValid() {
        login.userName = "valid_user";
        login.password = "Valid123!";
        // No exception should be thrown for valid registration
        login.registerUser(); // No assertion needed, just ensure it doesn't throw an exception
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        login.userName = "invalid"; // invalid username (no underscore)
        // No exception should be thrown for invalid username
        login.registerUser();
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        login.password = "short"; // invalid password (too short)
        // No exception should be thrown for invalid password
        login.registerUser();
    }

    // Testing loginUser method
    @Test
    public void testLoginUserSuccess() {
        login.enteredUserName = "valid_user";
        login.enteredPassword = "Valid123!";
        assertTrue("Login should succeed with valid credentials.", login.loginUser());
    }

    @Test
    public void testLoginUserFailure() {
        login.enteredUserName = "wrong_user";
        login.enteredPassword = "wrong_password";
        assertFalse("Login should fail with incorrect credentials.", login.loginUser());
    }

    // Testing returnLoginStatus method
    @Test
    public void testReturnLoginStatusSuccess() {
        login.enteredUserName = "valid_user";
        login.enteredPassword = "Valid123!";
        login.returnLoginStatus(); // Assumes the method just returns status without throwing exception
    }

    @Test
    public void testReturnLoginStatusFailure() {
        login.enteredUserName = "wrong_user";
        login.enteredPassword = "wrong_password";
        login.returnLoginStatus(); // Assumes the method just returns status without throwing exception
    }
}
