import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
* Reflection allows Java to inspect and manipulate classes, methods,
* and objects at runtime—even if they are not known at compile time.
* */
public class ReflectionExample {
    public static void main(String[] args)  {
        try {
            /*
            * This code is a basic Java Reflection example showing how to load a class,
            * create an object, and invoke methods at runtime.
            * */
            Class c = Class.forName("builder.PhoneBuilder");          //get class details from builder.PhoneBuilder
            Method method = c.getMethod("setProcessor",String.class);     //get method setProcessor
            Object instance = c.newInstance();                                  //create instance of PhoneBuilder
            Object obj = method.invoke(instance,"ios");                   //call setProcessor for instance
            Method method2 = c.getMethod("toString",null);  //get method toString
            System.out.println(method2.invoke(instance));                        //call method toString for instance
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
