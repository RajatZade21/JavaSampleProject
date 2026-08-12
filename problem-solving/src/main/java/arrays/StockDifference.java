package arrays;

public class StockDifference {
    public static void main(String[] args){
        int[] prices = new int[]{7,1,5,3,6,4};

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int price : prices){
            minPrice = Math.min(minPrice,price);              //Hold min price for all occurrence

            int profit = price - minPrice;                    //compare current price with min price
            maxProfit = Math.max(maxProfit,profit);           //set max-profit if new profit larger
        }

        System.out.println(maxProfit);
    }
}
