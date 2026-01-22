package strings;

//Check if String is Palindrome
//Using 2 pointer approach
public class PalindromeChecker {
    public static void main(String[] args){
        System.out.println(isPalindrome("Rajat"));
        System.out.println(isPalindrome("Nayan"));
    }

    public static boolean isPalindrome(String str){
        str = str.toLowerCase();

        // Initialize pointers
        int i=0;
        int j=str.length() - 1;

        // Compare characters at each position
        while (i < j){
            if(str.charAt(i) != str.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
