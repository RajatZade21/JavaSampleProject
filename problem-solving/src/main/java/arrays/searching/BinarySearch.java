package arrays.searching;

public class BinarySearch {
    public static void main(String[] args){
        Integer[] array = new Integer[]{1,2,3,7,8,9,12,15,17,18,16,20};
        System.out.println("BinarySearchIterative " + BinarySearchIterative(array,9));

        int left=0,right= array.length - 1;
        System.out.println("BinarySearchRecursive " + BinarySearchRecursive(array,left,right,12));
    }

    public static int BinarySearchIterative(Integer[] array,Integer element){
        Integer left = 0,right = array.length - 1;
        while (left < right){
            Integer mid = left + (right - 1) / 2;
            if(array[mid] == element){
                return mid;
            }
            else if(array[mid] > element){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int BinarySearchRecursive(Integer[] array,int left,int right,Integer element){
        int mid = left + (right - 1)/2;
        if(array[mid] == element){
            return mid;
        }
        else if(element < array[mid]){
            return BinarySearchRecursive(array,left,mid - 1,element);
        }
        else{
            return BinarySearchRecursive(array,left + 1,right,element);
        }
    }
}
