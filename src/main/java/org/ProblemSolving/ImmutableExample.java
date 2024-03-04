package org.ProblemSolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//•Don't provide "setter" methods — methods that modify fields or objects referred to by fields.
//•Make all fields final and private.
//•Don't allow subclasses to override methods. The simplest way to do this is to declare the class as final. A more sophisticated approach is to make the constructor private and construct instances in factory methods.
//•If the instance fields include references to mutable objects, don't allow those objects to be changed:
//->Don't provide methods that modify the mutable objects.
//->Don't share references to the mutable objects. Never store references to external, mutable objects passed to the constructor; if necessary, create copies, and store references to the copies. Similarly, create copies of your internal mutable objects when necessary to avoid returning the originals in your methods.
final class Student{
    final private String name;
    final private int rollNumber;
    final private long percent;
    final List<String> marks;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", percent=" + percent +
                ", marks=" + marks +
                '}';
    }

    public Student(String name, int rollNumber, long percent, List<String> marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.percent = percent;
        this.marks = new ArrayList<>();
        //this.marks = marks;  -> avoid this as updating original list would update local list
        //deep copy of this list of the mutable fields
        for(String mark : marks){
            this.marks.add(mark);
        }
    }
}
public class ImmutableExample {
    public static void main(String[] args){
        List<String> marks = Arrays.asList("50","60","70");
        Student stud1 = new Student("Rajat",21,80,marks);
        System.out.println(stud1.toString());

    }
}
