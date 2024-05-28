package stack;

import java.util.Stack;

public class ValidParentheses {

    public static void main(String[] args){
        String str1 = "()[]{}";
        System.out.println(str1 + " : " + checkParentheses(str1));

        String str2 = "[()]{}{[()()]()}";
        System.out.println(str2 + " : " + checkParentheses(str2));

        String str3 = "(]";
        System.out.println(str3 + " : " + checkParentheses(str3));
    }

    static boolean checkParentheses(String str){
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length();i ++){
            char ch = str.charAt(i);

            if(ch == '{'){
                stack.push('}');
            }
            else if(ch == '('){
                stack.push(')');
            }
            else if(ch == '['){
                stack.push(']');
            }
            else if(stack.isEmpty() || stack.pop() != ch){
                return false;
            }
        }
        return true;
    }
}
