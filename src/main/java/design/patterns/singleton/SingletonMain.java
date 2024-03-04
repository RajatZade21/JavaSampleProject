package design.patterns.singleton;
//Declaring all constructors of the class to be private.
//Providing a static method that returns a reference to the instance. The lazy initialization concept is used to write the static methods.
//The instance is stored as a private static variable.
//can use enum instead of class
class Singleton {
    //static Singleton singleton = new Singleton();  //--> Eager Loading -> will work
    static Singleton singleton;
    private Singleton(){
        System.out.println("Instance Created");
    }
    public static Singleton getInstance(){  //can use synchronized for multi threading (method level locking)
        if(singleton == null){
            //synchronized(Singleton.class)  -> double check locking (Block level)
            singleton = new Singleton(); // Lazy Laoding
        }
        return singleton;
    }
}

public class SingletonMain{
    public static void main(String[] args){
        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = Singleton.getInstance();
    }
}
