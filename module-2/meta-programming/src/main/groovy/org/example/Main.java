package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Cat> catClass = Cat.class;
        Field[] declaredFields = catClass.getDeclaredFields();
        Method[] methods = catClass.getDeclaredMethods();
        Constructor<?>[] constructors = catClass.getConstructors();

        Cat cat = new Cat();
        Cat cat2 = (Cat) constructors[0].newInstance();
        System.out.println(cat.calculateSquare());
        declaredFields[0].setAccessible(true);
        declaredFields[0].setInt(cat, 2);
        declaredFields[0].setAccessible(false);
        declaredFields[1].setAccessible(true);
        declaredFields[1].setInt(cat, 3);
        declaredFields[1].setAccessible(false);
        System.out.println("Length=" + cat.getLength() + " Width=" + cat.getWidth());
        System.out.println(cat.calculateSquare());

        for(Method m : methods) {
            if (m.getName().equals("setLengthPrivate")) {
                m.setAccessible(true);
                m.invoke(cat, 8);
                m.setAccessible(false);
                System.out.println("Length=" + cat.getLength() + " Width=" + cat.getWidth());
                System.out.println(cat.calculateSquare());
            }
        }
    }
}
