package com.drpicox.dddapp;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NamedObjectsRegistry {

    private Map<String, Object> storedNameValues = new HashMap<>();

    public void register(String name, Object value) {
        storedNameValues.put(name, value);
    }

    public <T> T recall(String name) {
        return (T) storedNameValues.get(name);
    }

    public void clear() {
        storedNameValues.clear();
    }

}
