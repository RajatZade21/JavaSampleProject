package org.ProblemSolving;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatedChar {

    public static void main(String[] args){
        Character ch = findFirstNonRepeatedChar("namaste nayan");
        System.out.println("First Non Repeated Character - " + ch);

        System.out.println("First Non Repeated Character using stream - " + findFirstNonRepeatedCharUsingStream("namaste nayan"));
    }

    static Character findFirstNonRepeatedChar(String str){
        Character nonRepeatedChar = ' ';

        Map<Character ,Integer> map = new HashMap<>();

        for(int i = 0;i < str.length();i++){
            Character ch = str.charAt(i);
            map.put(ch,map.getOrDefault(ch,0) + 1);
        }
        System.out.println(map);

        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if(entry.getValue() == 1){
                nonRepeatedChar = entry.getKey();
                break;
            }
        }

        return nonRepeatedChar;
    }

    static Character findFirstNonRepeatedCharUsingStream(String str){
        Character result = str.chars()
                .mapToObj(x -> Character.toLowerCase(Character.valueOf((char) x)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue().equals(1L))
                .map(x -> x.getKey())
                .findFirst().get();

        return result;
    }
}
