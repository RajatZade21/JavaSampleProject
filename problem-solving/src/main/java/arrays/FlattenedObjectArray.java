package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Convert given array Object[] array = { 1, 2, new Object[]{ 3, 4, new Object[]{ 5 }, 6, 7 }, 8, 9, 10 };
//to array of Integers
public class FlattenedObjectArray {
    public static void main(String[] args){
        Object[] array = { 1, 2, new Object[]{ 3, 4, new Object[]{ 5 }, 6, 7 }, 8, 9, 10 };

        Integer[] flattenedArray = flatten(array);

        System.out.println(Arrays.toString(flattenedArray));
    }

    public static Integer[] flatten(Object[] array){
        List<Integer> list = new ArrayList<>();
        flattenArray(array,list);
        Integer[] arr = new Integer[list.size()];
        return list.toArray(arr);
    }

    public static void flattenArray(Object[] array,List<Integer> list){
        for(int i=0;i < array.length;i++){
            if(array[i] instanceof Object[]){
                flattenArray((Object[]) array[i],list);
            }
            else{
                list.add((Integer) array[i]);
            }
        }
    }
}
