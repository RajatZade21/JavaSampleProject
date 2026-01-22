package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomComparatorOnList {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("1234");
        list.add("0123");
        list.add("0001");
        list.add("0102");
        list.add("1102");
        list.add("1200");

        Comparator<String> zeroComparator = (x,y) -> {
            long zeroCount1 = x.chars().filter(ch -> ch == '0').count();
            long zeroCount2 = y.chars().filter(ch -> ch == '0').count();

            if(zeroCount1 != zeroCount2){
                return Long.compare(zeroCount1,zeroCount2);
            }
            else{
                return x.compareTo(y);
            }
        };

        Collections.sort(list,zeroComparator);

        System.out.println(list.toString());
    }
}
