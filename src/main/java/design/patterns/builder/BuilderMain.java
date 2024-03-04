package design.patterns.builder;

//Builder Pattern - Creational Design Pattern
//Step 1 - User a builder class to assign values instead of direct constructor
//Step 2 - Let the builder class have all the setter and each setter will return Builder object
//Step 3 - Lastly a method to return instance of actual object
public class BuilderMain {

    public static void main(String[] args){
        Phone ph = new PhoneBuilder().setOs("Android").setRam(2).getPhone();
        System.out.println(ph.toString());
    }
}
