package com.demo.api.event;
import lombok.Getter;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Getter
@NoArgsConstructor
public class Event<K, T> {

    public enum Type {CREATE, DELETE, UPDATE}

    private Event.Type eventType;
    private K key;
    private T data;
    private LocalDateTime eventCreatedAt;


    public Event(Type eventType, K key, T data) {
        this.eventType = eventType;
        this.key = key;
        this.data = data;
        this.eventCreatedAt = now();
    }
}
