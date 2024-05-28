package hashsetproblems;

import common.layer.models.Student;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HashSetExample1 {
    public static void main(String[] args){
        HashSet<Student> studentList = new HashSet<>();

        Student st1 = new Student("Nimit", 1);
        Student st2 = new Student("Rahul", 3);
        Student st3 = new Student("Nimit", 2);
        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);

        System.out.println(studentList.size());

        st1.studentId = 3;
        System.out.println(studentList.size());


        Set<String> set =new HashSet<>(Arrays.asList("Rajat","Rajat"));
        System.out.println(set.toString());
    }
}
