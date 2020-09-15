package com.epam.deliveryservice.event;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class Event {
    private EventType eventType;
    private EventResult eventResult;
    private Long orderId;
}
