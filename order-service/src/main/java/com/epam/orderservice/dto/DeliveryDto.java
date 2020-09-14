package com.epam.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {
    @NotNull
    private Long orderId;

    @NotNull
    private Date scheduledDeliveryTime;
}
