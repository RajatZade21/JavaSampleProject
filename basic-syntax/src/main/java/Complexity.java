public class Complexity {
    public static void main(String[] args){
        int n = 100;

        //Single Loop → Usually O(n)
        for (int i = 0; i < n; i++){

        }

        //Nested Loops → Usually O(n²)
        //n × n → O(n²)
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){

            }

        //Loop Halving the Input → O(log n)
        //Dividing by 2 each time → O(log n)
        //Binary search
        //Tree height traversal
        while (n > 1) {
            n = n / 2;
        }

        //Loop Inside Logarithmic Operation → O(n log n)
        
    }
}
