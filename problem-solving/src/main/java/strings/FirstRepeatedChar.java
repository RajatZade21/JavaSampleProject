package strings;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//Find the first repeated character in a string
//Time complexity : O(n)
//Auxiliary Space : O(n)
public class FirstRepeatedChar {

    static Character returnRepeatedChar(String str){
        HashSet<Character> set = new HashSet<Character>();

        for(int i=0;i < str.length();i++){
            Character ch = Character.toLowerCase(str.charAt(i));
            if(set.contains(ch)){
                return ch;
            }
            else{
                set.add(ch);
            }
        }
        return null;
    }

    static Character returnRepeatedCharStream(String str){

        Character result =  str.chars().mapToObj(x -> Character.toLowerCase((char)x)).collect(
                Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() > 1)
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .map(x -> x.getKey())
                .findFirst()
                .get();

        return result;
    }

    public static void main(String[] args){
        String str = "JavA is CoOl";

        System.out.println(returnRepeatedChar(str));

        System.out.println(returnRepeatedCharStream(str));
    }
}

//OPTIMIZED APPROACH:
//Intuition:
//We create a array of size 26.
//then we increase the value of that character by 1 and if same character comes again, then we return that character.
//Time Complexity:  O(N), because N is the length of the string
//Space Complexity: O(1)