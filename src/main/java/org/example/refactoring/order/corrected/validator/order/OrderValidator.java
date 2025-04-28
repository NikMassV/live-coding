package org.example.refactoring.order.corrected.validator.order;

import org.example.refactoring.order.corrected.domain.Order;
import org.example.refactoring.order.corrected.exception.OrderValidationException;

public interface OrderValidator {

    void validate(Order order) throws OrderValidationException;
}
