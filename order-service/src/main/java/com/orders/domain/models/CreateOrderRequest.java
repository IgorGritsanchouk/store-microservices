package com.orders.domain.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public record CreateOrderRequest(
        @NotEmpty(message = "Items cannot be empty") Set<@Valid OrderItem> items,
        @Valid Customer customer,
        @Valid Address deliveryAddress) {}
