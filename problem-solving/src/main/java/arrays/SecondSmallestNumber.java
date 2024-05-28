package arrays;

public class SecondSmallestNumber {
    public static void main(String[] args){
        Integer arr[] = {12, 13, 1, 10, 34, 1};
        Integer smallest=Integer.MAX_VALUE;
        Integer secondSmallest=Integer.MAX_VALUE;

        for(Integer i: arr){
            if(i < smallest){
                smallest = i;
            }
            if(i < secondSmallest && i != smallest){
                secondSmallest = i;
            }
        }

        System.out.println(smallest);
        System.out.println(secondSmallest);


    }
}
