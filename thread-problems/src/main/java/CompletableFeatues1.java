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
        try{
            System.out.println(step1.get());
            System.out.println(step2.get());
            step3.get();
        }
        catch (Exception ex){

        }
    }
}
