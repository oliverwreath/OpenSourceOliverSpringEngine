package com.oli.Entities;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Oliver
 */
@Slf4j
public class DropDownListConverter implements AttributeConverter<List<String>, String> {

    private static final String SEPARATOR = ", ";

    /**
     * Convert Color object to a String
     * with format red|green|blue|alpha
     */
    @Override
    public String convertToDatabaseColumn(List<String> names) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String name : names) {
            if (isFirst) {
                sb.append(name);
                isFirst = false;
            } else {
                sb.append(SEPARATOR).append(name);
            }
        }
        return sb.toString();
    }

    /**
     * Convert a String with format red|green|blue|alpha
     * to a Color object
     */
    @Override
    public List<String> convertToEntityAttribute(String namesString) {
        String[] split = namesString.split(SEPARATOR);
        return Arrays.asList(split);
    }
}

