package org.example.refactoring.order.corrected.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "discount")
public class DiscountProperties {

    private String apiUrl;

    public String getApiUrl() {
        return apiUrl;
    }
}
