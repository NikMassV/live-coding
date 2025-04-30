package org.example.refactoring.order.corrected.validator.order;

import org.example.refactoring.order.corrected.exception.OrderValidationException;

import java.util.HashMap;
import java.util.Map;

public class OrderValidatorRegistry {

    private final Map<Class<?>, OrderValidator> validators = new HashMap<>();

    public void registerValidator(Class<?> orderType, OrderValidator validator) {
        validators.put(orderType, validator);
    }

    public OrderValidator getValidator(Class<?> orderType) {
        OrderValidator validator = validators.get(orderType);
        if (validator == null) {
            throw new OrderValidationException("Unsupported order type: " + orderType);
        }
        return validator;
    }
}
