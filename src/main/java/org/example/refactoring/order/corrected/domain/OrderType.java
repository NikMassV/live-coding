package org.example.refactoring.order.corrected.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class OrderType<T> {

    private T type;

    public OrderType() {
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrderType<?> orderType = (OrderType<?>) o;

        return new EqualsBuilder().append(type, orderType.type).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(type).toHashCode();
    }

    @Override
    public String toString() {
        return "OrderType{" +
                "type=" + type +
                '}';
    }
}
