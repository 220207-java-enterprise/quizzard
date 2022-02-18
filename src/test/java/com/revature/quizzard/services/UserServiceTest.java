package com.revature.quizzard.services;


// Test Suite
// A class that encapsulates one or more test methods (cases)

import com.revature.quizzard.test_utils.BeforeEach;
import com.revature.quizzard.test_utils.Describe;
import com.revature.quizzard.test_utils.Test;

// Because the package structure of our test directory mirrors the src directory,
// Java's compiler and JVM look at this class as if it is in the same package as
// UserService.java
@Describe
public class UserServiceTest {

    private UserService sut; // sut = System Under Test

    @BeforeEach
    public void setup() {
        System.out.println("In the before each!");
        sut = new UserService();
    }

    @Test
    public void test_isEmptyStringIsValidUsername() {

        System.out.println("Inside of test_isEmptyStringIsValidUsername");
        // AAA = Arrange, Act, and Assert

        // Arrange
        String username = "";
        boolean expected = false;

        // Act
        boolean result = sut.isUsernameValid(username);

        // Assert
        if (result != expected) {
            throw new RuntimeException("Test failed! Expected result to be false.");
        }

    }

    @Test
    public void test_isNullStringValidUsername() {

        System.out.println("Inside of test_isNullStringValidUsername");

        // Arrange
        String username = null;
        boolean expected = false;

        // Act
        boolean result = sut.isUsernameValid(username);

        // Assert
        if (result != expected) {
            throw new RuntimeException("Test failed! Expected result to be false.");
        }
    }

}
