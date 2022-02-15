package com.revature.quizzard.test_utils;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD) // we can use @Test annotation with methods
@Retention(RetentionPolicy.RUNTIME) // annotation will be visible at runtime (could be source=at source time, before
// compiling, class=during class
// loading)
public @interface Test {
    // declares an argument that can be passed to this annotation
    // by including "default clause" this value is optional
    String description() default "";
}
