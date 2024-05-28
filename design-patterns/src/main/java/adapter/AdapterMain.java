package adapter;

//Adapter Pattern - Structural Design Pattern
/**The Adapter design pattern is a structural pattern that allows the interface of an existing class to be used as another
 * interface. It acts as a bridge between two incompatible interfaces, making them work together.
 * This pattern involves a single class, known as the adapter, which is responsible for joining functionalities of
 * independent or incompatible interfaces.**/
//uses Object Composition to join incompatible interfaces
public class AdapterMain {

    public static void main(String[] args){
        Pen p = new PenAdopter();
        AssignmentWork ow = new AssignmentWork();
        ow.setP(p);
        ow.writeAssignment("Example for Adapter Design Pattern");
    }
}
