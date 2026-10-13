package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CombinationSum {
    static HashSet<List<Integer>> set = new HashSet<>();

    public static void main(String[] args){
        int[] arr = {1,9,6,5,4,5,7,8,2,6,3,4};
        int targetSum = 11;
        Arrays.sort(arr);
        System.out.println("N values that sum to " + targetSum + ":");
        combineSumN(arr, 0, 0, targetSum, new ArrayList<>());
        System.out.println("Triplets values that sum to " + targetSum + ":");
        combineSumThree(arr,targetSum);
        System.out.println("Two values that sum to " + targetSum + ":");
        combineSumTwo(arr,targetSum);
    }


    static void combineSumN(int[] arr, int index,
                                  int sum, int targetSum,
                                  List<Integer> current) {

        // Base case: processed every element
        if (index == arr.length) {
            if (sum == targetSum) {
                if(!set.contains(current)){
                    System.out.println(current);
                    set.add(current);
                }
            }
            return;
        }

        // Include current element
        current.add(arr[index]);
        combineSumN(arr, index + 1,
                sum + arr[index], targetSum, current);

        // Backtrack: remove current element
        current.remove(current.size() - 1);

        // Exclude current element
        combineSumN(arr, index + 1,
                sum, targetSum, current);
    }

    static void combineSumThree(int[] arr,int targetSum){
        Arrays.sort(arr);

        // Iterate through the array
        for (int i = 0; i < arr.length - 2; i++) {
        //The outer loop picks the first element (arr[i]) of our triplet.
        // We loop up to arr.length - 2 because we always need to leave room for at least two more elements (left and right).

            // Skip duplicate elements for the first number to avoid duplicate triplets
            // This prevents the program from printing the exact same triplet combination multiple times.
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                // We calculate the sum of the three numbers:
                // the fixed number + the left pointer number + the right pointer number.
                // We then check three conditions:
                int currentSum = arr[i] + arr[left] + arr[right];

                if (currentSum == targetSum) {
                    System.out.println("(" + arr[i] + ", " + arr[left] + ", " + arr[right] + ")");

                    // Skip duplicates for the second and third numbers
                    while (left < right && arr[left] == arr[left + 1]) left++;
                    while (left < right && arr[right] == arr[right - 1]) right--;

                    left++;
                    right--;
                } else if (currentSum < targetSum) {
                    //Because the array is sorted, if our total sum is less than the target, we need a larger number.
                    //We move the left pointer to the right (left++) to increase the sum.
                    left++;
                } else {
                    right--;
                }
            }


        }

    }

    static void combineSumTwo(int[] arr,int targetSum){

        HashSet<Integer> seen = new HashSet<>();
        HashSet<String> printedPairs = new HashSet<>();
        for(int element:arr){
            int complement = targetSum - element;

            // If the complement exists in our set, we found a pair!
            if(seen.contains(complement)){

                // Order them to prevent printing duplicates like (5,6) and (6,5)
                int min = Integer.min(element,complement);
                int max = Integer.max(element,complement);

                String pairkey = min + "," + max;

                if(!printedPairs.contains(pairkey)){
                    printedPairs.add(pairkey);
                    System.out.println(pairkey);
                }
            }

            // Add the current number to the set for future lookups
            seen.add(complement);
        }


    }

}
