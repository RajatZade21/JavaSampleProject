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
","    finally{
            return "C";
        }
    }

    //This will work - try can be used without catch
    public static int test1() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }

    public static int test2() {
        try {
            throw new RuntimeException();
            //return 1;   <--- this will return error - Unreachable statement
        } finally {
            return 2;
        }
        //It still returns 2, and the exception is effectively suppressed/swallowed because finally has a return
        //the exception occurs, but it doesn't reach the caller because the finally block returns a value.
    }

    public static void test3(){
        //try           <- this will return error - 'catch' or 'finally' expected
        {
            throw new RuntimeException();
        }
    }

}
