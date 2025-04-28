package org.example.refactoring.order.corrected.validator.order;

import org.example.refactoring.order.corrected.exception.OrderValidationException;

import java.util.HashMap;
import java.util.Map;

public class OrderValidatorRegistry {
    private final Map<String, OrderValidator> validators = new HashMap<>();

    public OrderValidatorRegistry() {
        validators.put("flower", new FlowerOrderValidator());
        validators.put("gift", new GiftOrderValidator());
        validators.put("toy", new ToyOrderValidator());
    }

    public OrderValidator getValidator(String type) {
        OrderValidator validator = validators.get(type);
        if (validator == null) {
            throw new OrderValidationException("Unsupported order type: " + type);
        }
        return validator;
    }
}
