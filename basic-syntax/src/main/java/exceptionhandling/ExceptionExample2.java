package exceptionhandling;

public class ExceptionExample2 {
    public static void main(String[] args){
        System.out.println(test()); // will return C everytime
    }

    public static String test(){
        try{
            return "A";
        }
        catch(Exception ex){
            return "B";
        }
	finally{
            return "C";
        }
    }
}
