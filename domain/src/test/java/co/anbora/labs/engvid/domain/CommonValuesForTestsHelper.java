package co.anbora.labs.engvid.domain;

import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.Lesson;

import java.util.Arrays;
import java.util.List;

public class CommonValuesForTestsHelper {

    private CommonValuesForTestsHelper() {}

    public static Lesson provideBeginnerLesson() {
        return Lesson.builder()
                .id(6716L)
                .title("Learn English Tenses: FUTURE SIMPLE with “will”")
                .description("In this easy English class, you will learn to use will and won’t to talk about the future. This is called the FUTURE SIMPLE TENSE in English. I’ll show you exactly how to use it, when to use it, and what mistakes to avoid. This one English grammar class covers everything you need: structure, usage, [&hellip;]")
                .category(EnglishLevel.BEGINNER.getId().longValue())
                .date("2020-03-01T17:52:50")
                .renderLink("https://www.engvid.com/?p=6716")
                .slug("future-simple")
                .imageUrl("https://img.youtube.com/vi/tri7u632AaA/0.jpg")
                .youtubeId("tri7u632AaA")
                .sync(true)
                .build();
    }

    public static Lesson provideIntermediateLesson() {
        return Lesson.builder()
                .id(6700L)
                .title("Learn 30+ common “WILL” sentences in English")
                .description("Learning set phrases is one of the best ways to learn a language. This lesson covers more than 30 common English sentences that use the word will. Increase your speaking confidence and your listening comprehension by mastering the language covered in this practical English lesson. Some of the sentences include: We’ll see; you’ll never know unless you try; you’ll regret this; I’ll think about it; I’ll see you later; I’ll be right back; I’ll pay you back; I won’t forget this; it’ll be fine; and many more. Check it out and immediately increase your English language bank!")
                .category(EnglishLevel.INTERMEDIATE.getId().longValue())
                .date("2020-03-02T17:52:50")
                .renderLink("https://www.engvid.com/?p=6700")
                .slug("learn-30-common-will-sentences-in-english")
                .imageUrl("https://img.youtube.com/vi/hJaFrLeFItE/0.jpg")
                .youtubeId("hJaFrLeFItE")
                .sync(true)
                .build();
    }

    public static Lesson provideAdvancedLesson() {
        return Lesson.builder()
                .id(6640L)
                .title("Learn English the MMA way!")
                .description("This lesson is all about English expressions that come from mixed martial arts and combat sports! But don’t assume all of the expressions I will teach you have to do with actual fighting. Rather, we use these expressions in everyday conversation when we talk about competition, challenges, and arguments. For example, when we say throw your hat in the ring, we are not talking about a boxing ring, but someone entering a competition or challenge. When we say take it on the chin, we are not talking about punches. It means to accept defeat without complaining. So as you can see, although some expressions come from the world of combat sports, they are commonly used in non-violent situations. Some other expressions we will look at include: spoiling for a fight, the gloves are off, fight tooth and nail, uphill battle, throw in the towel, win hands down, and more. So before you tap out, click the lesson, and do the quiz to test yourself.")
                .category(EnglishLevel.ADVANCE.getId().longValue())
                .date("2020-03-03T17:52:50")
                .renderLink("https://www.engvid.com/?p=6640")
                .slug("learn-english-the-mma-way")
                .imageUrl("https://img.youtube.com/vi/iGFAbvwv8oE/0.jpg")
                .youtubeId("iGFAbvwv8oE")
                .sync(true)
                .build();
    }

    public static List<Lesson> beginnerLessons() {
        return Arrays.asList(provideBeginnerLesson());
    }

    public static List<Lesson> intermediateLessons() {
        return Arrays.asList(provideIntermediateLesson());
    }

    public static List<Lesson> advancedLessons() {
        return Arrays.asList(provideAdvancedLesson());
    }

}
