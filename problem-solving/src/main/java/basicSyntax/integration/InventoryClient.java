package basicSyntax.integration;

import basicSyntax.dto.records.StockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/stock/{sku}")
    StockResponse getStock(
            @PathVariable("sku") String sku
    );
}
