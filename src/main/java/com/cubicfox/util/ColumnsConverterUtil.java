package com.cubicfox.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ColumnsConverterUtil {
    public String convertColumnsToString(String joiner, String prefix, String ... columns) {
        if (prefix == null) {
            return String.join(joiner, columns);
        } else {
            List<String> stringColumns = Arrays.stream(columns)
                    .map(column -> prefix + column).collect(Collectors.toList());
            return String.join(joiner, stringColumns);
        }
    }
}
