package exceptionhandling;

class InvalidAgeException extends Exception{
    public InvalidAgeException(String msg){
        super(msg);
    }
}

public class ExceptionExample1 {
    public static void main(String[] args){
        try{
            checkValidAge(17);
        }
        catch (InvalidAgeException ex){
            System.out.println("Exception occured: " + ex);
        }
    }

    public static void checkValidAge(int age) throws InvalidAgeException{
        if(age < 18){
            throw new InvalidAgeException("Invalid Age");
        }
    }
}
