package co.anbora.labs.engvid.domain.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum EnglishLevel {

    BEGINNER(8, "1-Beginner"),
    INTERMEDIATE(9, "2-Intermediate"),
    ADVANCE(10, "3-Advanced");

    private static final Map<String, EnglishLevel> ENUM_MAP;

    static {
        Map<String, EnglishLevel> map = new ConcurrentHashMap<>();
        for (EnglishLevel instance : EnglishLevel.values()) {
            map.put(instance.getName(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    private Integer id;
    private String name;

    EnglishLevel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static EnglishLevel getLevel(String name) {
        return ENUM_MAP.getOrDefault(name, BEGINNER);
    }
}
