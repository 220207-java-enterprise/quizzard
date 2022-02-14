package com.revature.quizzard.daos;

// Interfaces are implicitly abstract
// Do NOT declare constructors
public interface CrudDAO<T> {

    // all declared interface variables are implicitly: public, static, and final

    // all declared method stubs are implicitly: public and abstract
    void save(T newObject);
    T getById(String id);
    T[] getAll();
    void update(T updatedObject);
    void deleteById(String id);

    // Interfaces can have static methods with a body
    // These methods cannot be overridden (but they can be redeclared [don't use @Override]; effectively shadowing)
    static void staticInterfaceMethod() {

    }

    // Since Java 8, interfaces can contain "default" methods
    // Provides a base functionality that all implementations get, but it can be overridden
    default void defaultInterfaceMethod() {

    }

}
