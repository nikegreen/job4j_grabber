package ru.job4j.template;

import java.util.IllegalFormatFlagsException;
import java.util.Map;

public class SimpleGenerator implements Generator {

    @Override
    public String produce(String template, Map<String, String> args) {
        for (String key: args.keySet()) {
            String s = template.replaceAll("\\$\\{" + key + "}", args.get(key));
            if (template.equals(s)) {
                throw new IllegalArgumentException("key '" + key + "'");
            }
            template = s;
        }
        int index = 0;
        while ((index = template.indexOf("${", index)) >= 0) {
            int first = index;
            index = template.indexOf("}");
            if (index >= 0) {
                throw new IllegalFormatFlagsException(
                        "key '" + template.substring(first, index) + "' not found");
            }
        }
        return template;
    }
}
