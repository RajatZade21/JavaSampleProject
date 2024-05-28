package factory;

//Factory Pattern - Creational Design Pattern
//Step 1 - Create a factory class to return instance of obj instead of using direct implementation
//Step 2 - All class should implements a Interface, object should be created via that interface
//It allows us to create objects without tightly coupling our code to specific classes
public class FactoryMain {

    public static void main(String[] str){
        OperatingSystemFactory factory = new OperatingSystemFactory();
        os obj = factory.getInstance("open");
        obj.specs();
    }
}
