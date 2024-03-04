package design.patterns.factory;

public class OperatingSystemFactory {

    public os getInstance(String str){
        if(str.equals("open"))
            return new Android();
        else
            return new IOS();
    }
}
