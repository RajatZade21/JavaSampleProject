package thread;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> future =
                CompletableFuture
                        .supplyAsync(() -> 10)
                        .thenApply(number -> number * 2);

        CompletableFuture<List<String>> result =
                future.thenCompose(value -> getResults(value)); //thenCompose - combine two CompletableFuture

        //result.join(); // will wait for result to get completed, ultimately blocking the execution

        result.thenAccept(list -> {
            System.out.println("Final result: " + list);
        });


        Thread.sleep(5000); //main class would complete before result gets result
    }

    private static CompletableFuture<List<String>> getResults(Integer value) {

        return CompletableFuture.supplyAsync(() ->
                List.of("Result 1 : " + value, "Result 2 : " + value * 2)
        );
    }
}
