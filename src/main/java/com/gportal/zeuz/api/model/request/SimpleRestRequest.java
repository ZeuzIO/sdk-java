package com.gportal.zeuz.api.model.request;

import com.gportal.zeuz.api.model.annotiations.JsonField;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleRestRequest {
    protected String fetchFields() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            List<Field> fields = Arrays.stream(this.getClass().getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(JsonField.class))
                    .collect(Collectors.toList());

            if(!fields.isEmpty()) {
                for(Field field : fields) {
                    if(field.isAnnotationPresent(JsonField.class)) {
                        if(!field.isAccessible())
                            field.setAccessible(true);

                        JsonField jsonField = field.getAnnotation(JsonField.class);

                        Object value = field.get(this);

                        if(value == null) {
                            stringBuilder.append(String.format("\t\"%s\": %s", jsonField.fieldName(), jsonField.displayType().name()));


                            if(fields.indexOf(field) != fields.size() - 1) {
                                stringBuilder.append(",\n");
                            }
                            continue;
                        }

                        switch(jsonField.displayType()) {
                            case TEXT:
                                stringBuilder.append(
                                        String.format("\t\"%s\": \"%s\"", jsonField.fieldName(), value.toString())
                                );
                                break;

                            case NUMBER:
                                stringBuilder.append(
                                        String.format("\t\"%s\": %d", jsonField.fieldName(), value)
                                );
                                break;

                            case BOOLEAN:
                                stringBuilder.append(
                                        String.format("\t\"%s\": %s", jsonField.fieldName(), String.valueOf((boolean)value))
                                );
                                break;

                            case LIST:
                                stringBuilder.append(
                                        String.format("\t\"%s\": []", jsonField.fieldName())
                                );
                                break;
                        }

                        if(fields.indexOf(field) != fields.size() - 1) {
                            stringBuilder.append(",\n");
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        stringBuilder.insert(0, "{\n");
        stringBuilder.insert(stringBuilder.length(), "\n}");

        return stringBuilder.toString();
    }
}
