package common.layer.models;


public class Student {
    public String name;
    public Integer studentId;

    public Student(String name,Integer id){
        this.name = name;
        this.studentId =id;
    }

    public int hashCode() {
        return this.studentId;
    }

    public String toString() {
        return "Student: " + this.name + "@" + Integer.toHexString(hashCode());
    }

    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student s = (Student) o;
            return s.studentId == this.studentId ? true : false;
        }
        return false;
    }

}
