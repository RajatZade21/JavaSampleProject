package strings;

import java.util.HashMap;
import java.util.Map;

public class CheckAnagram {

    public static void main(String[] args){
        String str1 = "rajat";
        String str2 = "aajtr";

        System.out.println("Anagram is :" + checkAngram(str1,str2));
    }

    static boolean checkAngram(String str1,String str2){

        if(str1.length() != str2.length()){
            return false;
        }

        Map<Character,Integer> map = new HashMap<>();

        for(int i=0;i<str1.length();i++){
            map.put(str1.charAt(i),map.getOrDefault(str1.charAt(i),0) + 1);
        }

        System.out.println(map);

        for (int i=0;i<str2.length();i++){
            map.put(str2.charAt(i),map.getOrDefault(str2.charAt(i),0) - 1);
        }

        System.out.println(map);

        for (Map.Entry<Character,Integer> entry:map.entrySet()) {
            if(entry.getValue() != 0){
                return false;
            }
        }

        return true;
    }
}
