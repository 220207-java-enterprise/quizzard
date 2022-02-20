package com.revature.quizzard.services;

import com.revature.quizzard.test_utils.BeforeEach;
import com.revature.quizzard.test_utils.Describe;
import com.revature.quizzard.test_utils.Test;

// Test Suite
// A class that encapsulates one or more test methods (cases)
// Because the package structure of our test directory mirrors the src directory,
// Java's compiler and JVM look at this class as if it is in the same package as UserService.java
@Describe
public class UserServiceTest {

    // we can access UserService methods
    // to keep track of
    private UserService sut; //sut = System Under Test

    // to be invoked before each
    @BeforeEach
    public void setup(){
        System.out.println("Inside of @BeforeEach");
        sut = new UserService();
    }

    @Test
    public void test_isEmptyStringValidUsername(){
        System.out.println("Inside of test_isEmptyStringValidUsername");
        // AAA structure for testing:
        // Arrange
        String username ="";
        boolean expected = false;

        // Act
        boolean result = sut.isUsernameValid(username);

        // Assert
        if (result != expected){
            throw new RuntimeException("Test Failed! Expected result to be false.");
        }
    }

    @Test
    public void test_isNullStringValidUsername(){
        System.out.println("Inside of test_isNullStringValidUsername()");
        // Arrange
        String username = null;
        boolean expected = false;

        // Act
        boolean result = sut.isUsernameValid(username);

        // Assert
        if (result != expected){
            throw new RuntimeException("Test Failed! Expected result to be false.");
        }
    }
}
