package strings;

public class RemoveAdjacentDuplicates {
    public static void main(String[] args){
        System.out.println(removeAdjacent("acaaabbbacdddd"));
        System.out.println(removeAdjacent("azxxzy"));
    }

    public static String removeAdjacent(String str){
        StringBuilder result = new StringBuilder();
        int i=0;

        while (i < str.length()){
            // If the result is empty or the current character is not the same as the last character in the result, append it
            if(result.length() == 0 || str.charAt(i) != result.charAt(result.length() - 1)){
                result.append(str.charAt(i));
                i++;
            }
            else{
                // Skip all adjacent duplicates
                char duplicateChar = str.charAt(i);
                while (i < str.length() && str.charAt(i) == duplicateChar){
                    i++;
                }
                // Remove the last character in the result as it was part of the duplicate sequence
                result.deleteCharAt(result.length() - 1);
            }
        }
        return result.toString();
    }
}
