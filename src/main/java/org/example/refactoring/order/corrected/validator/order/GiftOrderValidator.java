package org.example.refactoring.order.corrected.validator.order;

import org.example.refactoring.order.corrected.domain.Order;
import org.example.refactoring.order.corrected.exception.OrderValidationException;

public class GiftOrderValidator implements OrderValidator {

    @Override
    public void validate(Order order) {
        if (order.getGiftWrap() == null) {
            throw new OrderValidationException("Gift wrap option must be specified for gift orders");
        }
    }
}
