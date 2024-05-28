package strings;

//input : The Quick Brown Fox Jumped Over lazy Dog
//Output: ehT kciuQ nworB xoF depmuJ revO yzal goD
//Reverse a sentence
public class ReverseSentence {
    public static void main(String[] args){
        String str = "The Quick Brown Fox Jumped Over lazy Dog";

        String[] list = str.split(" ");
        StringBuilder result = new StringBuilder();

        for(String s:list){
            int length = s.length() - 1;
            while (length >= 0){
                result.append(s.charAt(length));
                length--;
            }
            result.append(" ");
        }
        System.out.println(result.toString());
    }
}
