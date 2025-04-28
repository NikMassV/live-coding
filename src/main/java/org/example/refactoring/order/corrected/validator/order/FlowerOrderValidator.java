package org.example.refactoring.order.corrected.validator.order;

import org.example.refactoring.order.corrected.domain.Order;
import org.example.refactoring.order.corrected.exception.OrderValidationException;

public class FlowerOrderValidator implements OrderValidator {

    @Override
    public void validate(Order order) {
        if (order.getBouquetName() == null || order.getBouquetName().isEmpty()) {
            throw new OrderValidationException("Bouquet name is required for flower orders");
        }
    }
}
