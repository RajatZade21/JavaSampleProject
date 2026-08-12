package util;

import java.util.regex.*;
public class Base64Validator {
    public static boolean isBase64Compatible(String input) {
        String base64Regex = "^[A-Za-z0-9+/=]+$";
        return Pattern.matches(base64Regex, input);
    }

    public static void main(String[] args) {
        String test1 = "U29tZVRleHQh"; // Valid Base64
        String test2 = "Hello, World!"; // Invalid Base64
        String test3 = "TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx5"; // Valid Base64

        System.out.println(isBase64Compatible(test1)); // true
        System.out.println(isBase64Compatible(test2)); // false
        System.out.println(isBase64Compatible(test3)); // true
        }
}