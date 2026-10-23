package hashmaps;

import common.layer.models.Employee;

import java.util.HashMap;
import java.util.Objects;

public class HashMapExample1 {
    public static void main(String[] args) {
        HashMap<Employee, Integer> employeeMap = new HashMap<Employee, Integer>();

        System.out.println(Objects.hash(new Employee("Rajat", 1)));
        System.out.println(Objects.hash(new Employee("Rajat", 1)));

        Employee emp = new Employee("zade", 1);
        System.out.println(Objects.hash(emp));
        System.out.println(Objects.hash(emp));

        employeeMap.put(new Employee("Rajat", 1), 100);
        employeeMap.put(new Employee("Rajat", 1), 200);

        Integer result = employeeMap.get(new Employee("Rajat", 1));
        System.out.println(result); // result = null;

    }
}
//A simple thumb rule is to use designpatterns.immutable objects as keys in a HashMap.
//because:
//If it were mutable, then the hashcode() value or equals() condition might change, and you would never be able to retrieve the key from your HashMap.
//More precisely, class fields that are used to compute equals() and hashcode() should be designpatterns.immutable!
//Now, suppose you create your own class:
//To compare two objects of your class you will have to override equals()
//To use it as a key in any Hash based Data structure you will have to override hashcode() (again, keeping immutability in mind)
//Remember that if two objects are equal(), then their hashcode() should be equal as well!