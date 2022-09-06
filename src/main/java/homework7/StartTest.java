package homework7;

import homework7.Annotation.AfterSuite;
import homework7.Annotation.BeforeSuite;
import homework7.Annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class StartTest {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Dog dog = new Dog();
        Class<?> dogClass = Dog.class;
        start(dogClass, dog);


    }
    public static void start(Class<?> dogClass, Dog dog) throws IllegalAccessException, InvocationTargetException {
        Map<Integer, ArrayList<Method>> map = new TreeMap<>(); // 1-10 - priority
        Method methodBeforeSuite = null;
        Method methodAfterSuite = null;
        int countBeforeSuite = 0;
        int countAfterSuite = 0;
        Method[] methods = dogClass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                methodBeforeSuite = method;
                countBeforeSuite++;
            }
            else if (method.isAnnotationPresent(AfterSuite.class)) {
                methodAfterSuite = method;
                countAfterSuite++;
            }
            else if (method.isAnnotationPresent(Test.class)) {
                Integer testPriority = method.getAnnotation(Test.class).priority();
                if(map.containsKey(testPriority)) {
                    map.get(testPriority).add(method);
                }
                else {
                    ArrayList<Method> methodArrayList = new ArrayList<>();
                    methodArrayList.add(method);
                    map.put(testPriority, methodArrayList);
                }
            }

        }
        if(countBeforeSuite > 1 || countAfterSuite > 1) {
            throw new RuntimeException();
        }

        if(methodBeforeSuite != null) {
            methodBeforeSuite.setAccessible(true);
            methodBeforeSuite.invoke(dog);
        }


        for (Map.Entry<Integer, ArrayList<Method>> m : map.entrySet()) {
            for (Method method : m.getValue()){
                method.setAccessible(true);
                method.invoke(dog);
            }
        }

        if(methodAfterSuite != null) {
            methodAfterSuite.setAccessible(true);
            methodAfterSuite.invoke(dog);
        }


    }

}
