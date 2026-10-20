package arrays.searching;

public class EquilibriumIndex {
    public static void main(String[] args){
      int A[] = {-7, 1, 5, 2, -4, 3, 0};

      System.out.println("Equilibrium Index - " + getEquilibriumIndex(A));
      System.out.println("Equilibrium Index - " + equilibrium(A,3));

    }

    static int getEquilibriumIndex(int[] arr){
        int totalResult = 0;

        for(int i:arr){
            totalResult=totalResult+i;
        }

        for(int i=0;i<arr.length-1;i++){
            if(arr[i] == totalResult){
                return i;
            }
            totalResult = totalResult - arr[i];
        }

        return -1;
    }

    static int equilibrium(int[] arr, int n) {
        int totalSum = 0;
        int leftSum = 0;

        for (int num : arr) {
            totalSum += num;
        }

        for (int i = 0; i < n; i++) {
            int rightSum = totalSum - leftSum - arr[i];

            if (leftSum == rightSum) {
                return i;
            }

            leftSum += arr[i];
        }

        return -1;
    }
}
