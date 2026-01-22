package inheritance;

class A {
    public void m1() {
        System.out.println("A : m1");
    }

    public int m3(){ return 1;}

    private int m4(){ return 2;}
}

class B extends A {
    public void m1() { //Override m1 from A
        System.out.println("B : m1");
    }

    public void m2() {
        System.out.println("B : m2");
    }

    public int m3() { return 2;}
    //public void m3(){} //gives compilation error, you can only override method with same input and output signature

    //@Override                //can not override private method from parent class,will consider m4 as new method
    public int m4(){ return 5;}
}

class C extends B {
    public void m2() { //Override m2 from B
        System.out.println("C : m2");
    }

    public void m6(int val){
        System.out.println(val);
    }

    public void m6(Integer value){
        System.out.println(value);
    }

    public void m6(Object obj){
        System.out.println(obj);
    }
}

public class InheritanceClass1 {
    public static void main(String[] args) {
        //C c = new A();  //this will not work, we cant create child object with parent reference
        A a = new C(); // can create object for
        //a.m2(); // this will not work as m2 not present in A

        B b1 = new B();
        b1.m1(); // class m1 from B
        b1.m3(); // class m3 from B

        A b2 = new B();
        b2.m1(); // class m1 from B

        A a1 = new A();
        a1.m1(); // class m1 from A

        C c = new C();
        c.m6(null); //m6(Object obj) will get called as since String is a subclass of Object,
        c.m6(1);  //m6(int val) will get called cause 1 in int literal
        c.m6(Integer.valueOf(10));  //m6(Integer value) will get called
    }
}

