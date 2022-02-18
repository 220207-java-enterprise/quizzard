package com.revature.quizzard.services;

// Test Suite
// A class that encapsulates one or more test methods (cases)
import com.revature.quizzard.daos.UserDAO;
import org.junit.*;

// Because the package structure of our test directory mirrors the src directory,
// Java's compiler and JVM look at this class as if it is in the same package as
// UserService.java
public class UserServiceTest {

    /*
        Common JUnit annotations:
            - @Test (marks a method as a test case)
            - @Ignore (tells JUnit to skip this test case)
            - @Before (logic that runs once before every test case)
            - @After (logic that runs once after every test case)
            - @BeforeClass (logic that runs only once before all test cases)
            - @AfterClass (logic that runs only once after all test cases)
     */

    private UserService sut; // sut = System Under Test

    @Before
    public void setup() {
        sut = new UserService(new UserDAO()); // TODO UserDAO needs to be mocked
    }

    @Test
    public void test_isUsernameValid_returnsFalse_givenEmptyString() {

        // AAA = Arrange, Act, and Assert

        // Arrange
        String username = "";

        // Act
        boolean result = sut.isUsernameValid(username);

        // Assert
        Assert.assertFalse(result);

    }

    @Test
    public void test_isUsernameValid_returnsFalse_givenNullString() {

        // Arrange
        String username = null;
        // Act
        boolean result = sut.isUsernameValid(null);

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void test_isUsernameValid_returnsFalse_givenShortUsername() {
        Assert.assertFalse(sut.isUsernameValid("short"));
    }

    @Test
    public void test_isUsernameValid_returnsFalse_givenLongUsername() {
        Assert.assertFalse(sut.isUsernameValid("waytolongofausernameforourapplication"));
    }

    @Test
    public void test_isUsernameValid_returnsFalse_givenUsernameWithIllegalCharacters() {
        Assert.assertFalse(sut.isUsernameValid("tester99!"));
    }

    @Test
    public void test_isUsernameValid_returnsTrue_givenValidUsername() {
        Assert.assertTrue(sut.isUsernameValid("tester99"));
    }

    // TODO mock UserDAO so that this is a proper unit test
    @Test(expected = RuntimeException.class)
    public void test_login_throwsRuntimeException_givenUnknownUserCredentials() {

        // Arrange
        String unknownUsername = "unknownuser";
        String somePassword = "p4$$W0RD";

        // Act
        sut.login(unknownUsername, somePassword);

    }

}
