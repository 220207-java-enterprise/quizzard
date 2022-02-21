package com.revature.quizzard.services;

import com.revature.quizzard.daos.UserDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// Test Suite
// A class that encapsulates one or more test methods (cases)
// Because the package structure of our test directory mirrors the src directory,
// Java's compiler and JVM look at this class as if it is in the same package as UserService.java

public class UserServiceTest {

    // we can access UserService methods
    // to keep track of
    private UserService sut; //sut = System Under Test

    // to be invoked before each
    @Before
    public void setup(){
        sut = new UserService(new UserDAO()); //TODO UserDAO needs to be mocked
    }

    @Test
    public void test_isUsernameValid_returnsFalse_givenEmptyString(){
        // AAA structure for testing:
        // Arrange
        String username ="";
        // Act
        boolean result = sut.isUsernameValid(username);
        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void test_isUsernameValid_returnsFalse_givenNullString(){
        // Arrange
        String username = null;
        // Act
        boolean result = sut.isUsernameValid(username);
        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void test_isUsernameValid_returnsFalse_givenShortUsername(){
        //Arrange
        String username="short";
        //Act
        boolean result = sut.isUsernameValid(username);
        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void test_isUsernameValid_returnsFalse_givenLongUsername(){
        //Arrange
        String username="waytoolongofausernameforourapplication";
        //Act
        boolean result = sut.isUsernameValid(username);
        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void test_isUsernameValid_returnsFalse_givenUsernameWithIllegalCharacters(){
        //Arrange
        String username="tester99!";
        //Act
        boolean result = sut.isUsernameValid(username);
        //Assert
        Assert.assertFalse(result);
    }

    @Test
    public void test_isUsernameValid_returnsTrue_givenValidUsername(){
        //Arrange
        String username="4bhilekh";
        //Act
        boolean result = sut.isUsernameValid(username);
        //Assert
        Assert.assertTrue(result);
    }


    // TODO mock UserDAO so this is a proper unit test
    @Test(expected=RuntimeException.class)
    public void test_login_throwsRuntimeException_givenUnknownUserCredentials(){
        //Arrange
        String unknownUsername="unknownuser";
        String somePassword = "p4$$WORD";
        //Act
        sut.login(unknownUsername,somePassword);
        //Assert
        //Assert.assertFalse(result);
    }
}
