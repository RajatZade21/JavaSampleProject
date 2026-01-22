package strings;

//Reverse String
//time complexity - O(n)
public class ReverseString {
    public static void main(String[] args){
        System.out.println(reverse("I"));
        System.out.println(reverse("I am"));
        System.out.println(reverse("Hero"));
        System.out.println(reverse("Rajat"));
    }

    public static String reverse(String str){
        if(str == null && str.isEmpty()){
            return str;
        }

        char[] charArray = str.toCharArray();
        int left = 0;
        int right = charArray.length - 1;
        while (right > left){
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }
        return new String(charArray);
    }
}
