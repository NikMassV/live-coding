package org.example.refactoring.order.corrected.validator.order;

import org.example.refactoring.order.corrected.domain.Order;
import org.example.refactoring.order.corrected.exception.OrderValidationException;

public class ToyOrderValidator implements OrderValidator {

    @Override
    public void validate(Order order) {
        if (order.getToyName() == null || order.getToyName().isEmpty()) {
            throw new OrderValidationException("Set items are required for toy orders");
        }
    }
}
