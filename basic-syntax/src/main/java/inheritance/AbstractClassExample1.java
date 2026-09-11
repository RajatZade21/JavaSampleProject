package inheritance;

abstract class AA
{
    protected abstract void firstMethod();

    public void secondMethod()
    {
        System.out.println("SECOND");

        firstMethod();
    }
}
abstract class BB extends AA
{
    //'firstMethod()' in 'BB' clashes with 'firstMethod()' in 'AA';
    // attempting to assign weaker access privileges ('package-private'); was 'protected'
    //BB.firstMethod has default/package-private more restrictive access than AA.firstMethod
    //When overriding a method, the child method cannot have more restrictive access than the parent method.
    //It can have the same or broader access.
    @Override
    void firstMethod()
    {
        System.out.println("FIRST");

        thirdMethod();
    }

    //Illegal combination of modifiers: 'abstract' and 'private'
    //abstract method cannot be private , final or static.
    private abstract void thirdMethod();
}
class CC extends BB
{
    @Override
    void thirdMethod()
    {
        System.out.println("THIRD");
    }
}


public class AbstractClassExample1 {

    public static void main(String[] args)
    {
        CC c = new CC();

        c.firstMethod();

        c.secondMethod();

        c.thirdMethod();
    }
}
