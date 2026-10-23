package basicSyntax.dto.records;

public record OrderCreated(
        Long orderId,
        Long customerId,
        double amount
) {}
