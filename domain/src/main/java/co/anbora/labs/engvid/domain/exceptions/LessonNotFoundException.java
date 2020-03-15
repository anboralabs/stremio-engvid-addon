package co.anbora.labs.engvid.domain.exceptions;

public class LessonNotFoundException extends RuntimeException {

    public LessonNotFoundException() {
        super("Lesson is missing unable to find it.");
    }

    public LessonNotFoundException(String type, String id) {
        super("Lesson is missing. Type: " + type + " and Id: " + id);
    }

}
