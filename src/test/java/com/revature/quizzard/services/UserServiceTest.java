package com.revature.quizzard.services;

// Test Suite
// A class that encapsulates one or more test methods (cases)
import com.revature.quizzard.daos.UserDAO;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.util.exceptions.AuthenticationException;
import com.revature.quizzard.util.exceptions.InvalidRequestException;
import org.junit.*;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

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
    private UserDAO mockUserDao = mock(UserDAO.class);

    @Before
    public void setup() {
        sut = new UserService(mockUserDao); // TODO UserDAO needs to be mocked
    }

    @After
    public void cleanUp() {
        reset(mockUserDao);
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

    @Test(expected = InvalidRequestException.class)
    public void test_login_throwsInvalidRequestExceptionAndDoesNotInvokeUserDao_givenInvalidUsername() {

        // Arrange
        String invalidUsername = "no";
        String validPassword = "p4$$W0RD";

        // Act
        try {
            sut.login(invalidUsername, validPassword);
        } finally {
            verify(mockUserDao, times(0)).findUserByUsernameAndPassword(invalidUsername, validPassword);
        }

    }

    @Test(expected = InvalidRequestException.class)
    public void test_login_throwsInvalidRequestExceptionAndDoesNotInvokeUserDao_givenInvalidPassword() {

        // Arrange
        String invalidUsername = "tester99";
        String validPassword = "invalid";

        // Act
        try {
            sut.login(invalidUsername, validPassword);
        } finally {
            verify(mockUserDao, times(0)).findUserByUsernameAndPassword(invalidUsername, validPassword);
        }

    }

    @Test(expected = InvalidRequestException.class)
    public void test_login_throwsInvalidRequestExceptionAndDoesNotInvokeUserDao_givenInvalidUsernameAndPassword() {

        // Arrange
        String invalidUsername = "invalid";
        String validPassword = "invalid";

        // Act
        try {
            sut.login(invalidUsername, validPassword);
        } finally {
            verify(mockUserDao, times(0)).findUserByUsernameAndPassword(invalidUsername, validPassword);
        }

    }

    @Test(expected = AuthenticationException.class)
    public void test_login_throwsAuthenticationException_givenUnknownUserCredentials() {

        // Arrange
        String unknownUsername = "unknownuser";
        String somePassword = "p4$$W0RD";
        when(mockUserDao.findUserByUsernameAndPassword(unknownUsername, somePassword)).thenReturn(null);

        // Act
        sut.login(unknownUsername, somePassword);

    }

    @Test
    public void test_login_returnsNonNullAppUser_givenValidAndKnownCredentials() {

        // Arrange
        UserService spiedSut = Mockito.spy(sut);

        String validUsername = "tester99";
        String validPassword = "p4$$W0RD";
        when(spiedSut.isUsernameValid(validUsername)).thenReturn(true);
        when(spiedSut.isPasswordValid(validPassword)).thenReturn(true);
        when(mockUserDao.findUserByUsernameAndPassword(validUsername, validPassword)).thenReturn(new AppUser());

        // Act
        AppUser loginResult = spiedSut.login(validUsername, validPassword);

        // Assert
        assertNotNull(loginResult);
        verify(mockUserDao, times(1)).findUserByUsernameAndPassword(validUsername, validPassword);
        verify(spiedSut, times(1)).isUsernameValid(validUsername);
        verify(spiedSut, times(1)).isPasswordValid(validPassword);

    }

}
