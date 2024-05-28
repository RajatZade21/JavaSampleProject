package arrays;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args){
        int[] arr = {1,9,6,5,4,5,7,8,2,6,3,4};
        int sum = 11;
        List < List < Integer >> ls = combinationSum(arr,sum);
        for (int i = 0; i < ls.size(); i++) {
            for (int j = 0; j < ls.get(i).size(); j++) {
                //System.out.print(ls.get(i).get(j) + " ");
            }
            //System.out.println();
        }
    }

    static List<List<Integer>> combinationSum(int[] arr,int sum){
        List<List<Integer>> result = new ArrayList<>();
        findCombination(0,arr,sum,result,new ArrayList<>());
        return result;
    }

    private static void findCombination(int i, int[] arr, int sum, List<List<Integer>> result, ArrayList<Integer> list) {
        //System.out.println(i + " " + arr + " " + sum + " " + result.toString() + " " + list.toString());
        if(i == arr.length){
            if(sum == 0){
                result.add(new ArrayList<>(list));
            }
            return;
        }

        if(arr[i] <= sum){
            list.add(arr[i]);
            findCombination(i,arr,sum - arr[i],result,list);
            System.out.println(list);
            list.remove(list.size() - 1);
        }
        findCombination(i + 1,arr,sum,result,list);

    }


}
