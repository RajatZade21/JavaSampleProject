package strings;

public class ConvertToUpperCase {
    public static void main(String[] args){
        String str = "Hello, WORLD!";
        String lowercaseStr = convertToLowerCase(str);
        System.out.println(convertToLowerCase(str));
        System.out.println(convertToLowerCase("RajatZade"));
    }

    public static String convertToLowerCase(String str){
        StringBuilder sb = new StringBuilder();
        for(Character ch:str.toCharArray()){
            if(ch >= 'A' && ch <= 'Z'){
                sb.append((char) (ch + 32));
            }
            else{
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
