package basicSyntax.dto;

import java.math.BigDecimal;

public class PaymentResponse {
    private Long paymentId;
    private Long orderId;
    private BigDecimal amount;

    public PaymentResponse(Long paymentId, Long orderId, BigDecimal amount) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
    }
}
