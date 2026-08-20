package arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Palindrome {

    public static void main(String[] args){
        List<String> words = Arrays.asList("radar", "hello", "level", "java","ararara");

        System.out.println(withStream(words));
    }


    public static List<String> withStream(List<String> words){
        return words.stream().filter(word ->
                IntStream.range(0, word.length() / 2)
                        .allMatch(i ->
                                word.charAt(i) ==
                                        word.charAt(word.length() -1 -i))
        ).collect(Collectors.toList());
    }
}
