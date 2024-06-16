import java.util.concurrent.CompletableFuture;

public class CompletableFeatues1 {
    public static void main(String[] args){
        CompletableFuture<String> step2 = CompletableFuture.supplyAsync(() -> {
            return "Step 2 execution";
        });

        CompletableFuture<String> step1 = CompletableFuture.supplyAsync(() -> {
            return "Step 1 execution";
        });

        CompletableFuture<Void> step3 = CompletableFuture.runAsync(() -> {
           System.out.println("Step 3 executing");
        });

        CompletableFuture<String> step4 = CompletableFuture.supplyAsync(() -> {
            return "Step 4 execution";
        });

        CompletableFuture<String> step5 = step4.thenApply(x -> x + " combining with Step 5");

        try{
            System.out.println(step1.get());
            System.out.println(step2.get());
            step3.get();
            System.out.println(step5.get());
        }
        catch (Exception ex){

        }
    }
}
