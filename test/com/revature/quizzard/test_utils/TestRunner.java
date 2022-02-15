package com.revature.quizzard.test_utils;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class TestRunner {
        public static void main(String[] args) {
            // POOR MAN's VERSION OF JUNIT
            System.out.println("Running Tests...");
            int passed=0,failed=0,error=0;


            try {
                // get the test suite class -> using package referencing
                Class<?> testSuiteClass = Class.forName("com.revature.quizzard.services.UserServiceTest");

                if (!testSuiteClass.isAnnotationPresent(Describe.class)){
                    throw new RuntimeException("Test suite classes are expected to be annotated with @Describe");
                }
                // List to store methods with @Test
                List<Method> testMethods = new LinkedList<>();
                //BeforeEach method instantiate as null
                Method beforeEachMethod = null;

                // iterate over methods in testSuiteClass
                for(Method classMethod : testSuiteClass.getDeclaredMethods()){
                    System.out.println("Inspecting method: "+ classMethod.toString());

                    // Within detected class, scan for methods annotated with @Test
                    if (classMethod.isAnnotationPresent(Test.class)){
                        // do something
                        System.out.println("@Test annotation present for the above method");
                        testMethods.add(classMethod);
                    }

                    // within detected class, scan for method annotated with @BeforeEach
                    if (classMethod.isAnnotationPresent(BeforeEach.class)){
                        if (beforeEachMethod!=null){
                            throw new RuntimeException("Only expected one @BeforeEach method");
                        }
                        beforeEachMethod = classMethod;
                    }
                }

                // create an instance of the UserServiceTest class (testSuiteClass)
                Object testSuiteInstance = testSuiteClass.newInstance();
                for (Method testMethod : testMethods){
                    try {
                        if (beforeEachMethod != null) beforeEachMethod.invoke(testSuiteInstance);
                        // invoke method stored in testMethods of testSuiteInstance class
                        testMethod.invoke(testSuiteInstance);
                        passed++;
                    }catch (InvocationTargetException e){
                        e.printStackTrace();
                        failed++;
                    } catch (Throwable t){
                        error++;
                    }
                }

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
                e.printStackTrace();
            }



            // Tally pass/fail and print to console
            System.out.printf("Passed: %d, Failed: %d, Error: %d", passed, failed, error);


        }
}
