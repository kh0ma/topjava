package ru.javawebinar.topjava.util.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by kh0ma on 12.10.16.
 */
@Component
public class LocalDateTimeToTimestampConverter implements Converter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convert(LocalDateTime source) {
        return source == null ? null : Timestamp.valueOf(source);
    }
}
