package com.revature.quizzard.daos;

// interfaces are implicitly abstract
// Do not declare constructors

// CrudDAO accepts generic object type -> <T>
public interface CrudDAO<T> {

    // all declared interface attributes are implicitly: public, static, final

    // all declared methods are implicitly: public and abstract
    void save(T newObject);
    T getById(String id);
    T[] getAll();
    void update(T updatedObject);
    void deleteById(String id);

    // interfaces can have static methods with a body
    // such methods cannot be overridden
    // bc overriding occurs on instance level objects
    // but, they can be re-declared (don't use @Override)
    // essentially shadowing
    // static void staticInterfaceMethod(){}

    // Since Java 8, interfaces can contain "default" methods
    // can be overridden at class-level
    // default void defaultInterfaceMethod(){}
}
