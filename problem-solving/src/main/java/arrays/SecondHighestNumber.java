package arrays;

public class SecondHighestNumber {
    public static void main(String[] args){
        int[] arr = {1,9,6,5,4,5,7,8,2,6,3,4};
        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;

        for(int num:arr){
            if(num > highest){
                highest = num;
            }
            if(num > secondHighest && num < highest){
                secondHighest = num;
            }
        }
        System.out.println(highest);
        System.out.println(secondHighest);
    }

}
