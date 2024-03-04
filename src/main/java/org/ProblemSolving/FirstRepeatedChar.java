package org.ProblemSolving;

import java.util.HashSet;

//Find the first repeated character in a string
//Time complexity : O(n)
//Auxiliary Space : O(n)
public class FirstRepeatedChar {

    static Character returnRepeatedChar(String str){
        HashSet<Character> set = new HashSet<Character>();

        for(int i=0;i < str.length();i++){
            Character ch = str.charAt(i);
            if(set.contains(ch)){
                return ch;
            }
            else{
                set.add(ch);
            }
        }
        return null;
    }

    public static void main(String[] args){
        System.out.println(returnRepeatedChar("Rajat"));
    }
}

//OPTIMIZED APPROACH:
//Intuition:
//We create a array of size 26.
//then we increase the value of that character by 1 and if same character comes again, then we return that character.
//Time Complexity:  O(N), because N is the length of the string
//Space Complexity: O(1)