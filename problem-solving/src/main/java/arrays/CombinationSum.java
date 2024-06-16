package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args){
        int[] arr = {1,9,6,5,4,5,7,8,2,6,3,4};
        int targetSum = 11;
        Arrays.sort(arr);
        List<List<Integer >> combinations = combinationSum(arr,targetSum);
        System.out.println("All combinations that add up to " + targetSum + ": " + combinations);
    }

    public static List<List<Integer>> combinationSum(int[] arr,int targetSum){
        List<List<Integer>> combinations = new ArrayList<>();
        findCombination(arr,0,targetSum,new ArrayList<>(),combinations);
        return combinations;
    }

    public static void findCombination(int[] arr, int currIndex, int targetSum, List<Integer> currCombination, List<List<Integer>> combinations) {

        if(targetSum == 0){
            // Found a valid combination
            combinations.add(new ArrayList<>(currCombination));
            return;
        }

        if(currIndex >= arr.length || targetSum < 0){
            // Reached end of array or exceeded target sum
            return;
        }

        // Explore two options: include or exclude current element
        List<Integer> withCurrent = new ArrayList<>(currCombination);
        withCurrent.add(arr[currIndex]);
        findCombination(arr,currIndex + 1,targetSum - arr[currIndex],withCurrent,combinations);

        List<Integer> withoutCurrent = new ArrayList<>(currCombination);
        findCombination(arr,currIndex + 1,targetSum,withoutCurrent,combinations);

    }



}
