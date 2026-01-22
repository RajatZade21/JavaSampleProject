public class CallByValueExample {
    public static void main(String[] args){
        int i =10;
        int j =20;
        System.out.println(i + " " + j); //will print 10 20
        swap(i,j);
        System.out.println(i + " " + j); //will print 10 20

    }

    //Java is not call by reference
    //Java is call by value; primitive values are copied,
    // so changes inside a method don’t affect the original variables.
    static void swap(int i, int j){
        int temp = i;
        i =j;
        j =temp;
    }

}
