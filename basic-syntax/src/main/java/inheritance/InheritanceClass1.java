package inheritance;

class A {
    public void m1() {
        System.out.println("A : m1");
    }

    public int m3(){ return 1;}
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
}

class C extends B {
    public void m2() { //Override m2 from B
        System.out.println("C : m2");
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
    }
}

