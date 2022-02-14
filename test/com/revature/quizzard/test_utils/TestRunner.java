package com.revature.quizzard.test_utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) {

        System.out.println("Running tests...");
        int passed = 0, failed = 0, error = 0;

        try {
            Class<?> testSuiteClass = Class.forName("com.revature.quizzard.services.UserServiceTest");
            List<Method> testMethods = new LinkedList<>();
            Method beforeEachMethod = null;

            // class.getMethods vs class.getDeclaredMethods
            // getMethods will return an array containing all methods (even inherited ones)
            // getDeclaredMethods will return an array containing only the explicitly declared ones
            for (Method classMethod : testSuiteClass.getDeclaredMethods()) {

                System.out.println("Inspecting method: " + classMethod.toString());

                // Is the method annotated with @Test?
                if (classMethod.isAnnotationPresent(Test.class)) {
                    System.out.println("@Test annotation present for the above method");
                    testMethods.add(classMethod);
                }

                if (classMethod.isAnnotationPresent(BeforeEach.class)) {
                    if (beforeEachMethod != null) {
                        throw new RuntimeException("Only expected one @BeforeEach method!");
                    }
                    beforeEachMethod = classMethod;
                }

            }

            Object testSuiteInstance = testSuiteClass.newInstance();
            for (Method testMethod : testMethods) {
                try {
                    if (beforeEachMethod != null) beforeEachMethod.invoke(testSuiteInstance);
                    testMethod.invoke(testSuiteInstance);
                    passed++;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    failed++;
                } catch (Throwable t) {
                    error++;
                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.printf("Passed: %d, Failed: %d, Error: %d\n", passed, failed, error);

    }

}
