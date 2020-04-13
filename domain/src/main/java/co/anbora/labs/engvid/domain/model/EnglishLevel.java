package co.anbora.labs.engvid.domain.model;

public enum EnglishLevel {

    BEGINNER(8, "1-Beginner"),
    INTERMEDIATE(9, "2-Intermediate"),
    ADVANCE(10, "3-Advanced");

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
}
