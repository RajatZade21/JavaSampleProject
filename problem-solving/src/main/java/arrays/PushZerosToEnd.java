package arrays;

import java.util.Arrays;
import java.util.List;

public class PushZerosToEnd {

    public static void main(String[] args){

        int[] arr = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9};
        int length = arr.length;
        arr = pushZeros(arr,length);
        System.out.println(Arrays.toString(arr));

    }

    static int[] pushZeros(int[] arr,int length){
        int count = 0;

        for(int i = 0; i < length; i++){
            if(arr[i] != 0){
                arr[count++] = arr[i];
                //System.out.println(count++);
            }
        }

        while (count < length)
            arr[count++] = 0;

        return arr;
    }

    //Given an array  int[] a={1,2,3,1,1,2,2,1,3,2,1}   Move all the 1's to the left, without changing the order of other elements . Output :: {1,1,1,1,1,2,3,2,2,3,2}
    //
}
