package com.epam.kitchenservice.event;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CompensationEvent {
    EventState eventState;
    EventType eventType;
    Long orderId;
}
