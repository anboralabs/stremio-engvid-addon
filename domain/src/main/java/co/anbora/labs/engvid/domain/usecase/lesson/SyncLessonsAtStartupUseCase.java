package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import lombok.Value;

import java.util.List;

public class SyncLessonsAtStartupUseCase extends UseCase<SyncLessonsAtStartupUseCase.Request, SyncLessonsAtStartupUseCase.Response> {

    private IAddOnRepository addOnRepository;
    private IEnglishVideoRepository englishVideoRepository;

    public SyncLessonsAtStartupUseCase(IAddOnRepository addOnRepository, IEnglishVideoRepository englishVideoRepository) {
        this.addOnRepository = addOnRepository;
        this.englishVideoRepository = englishVideoRepository;
    }

    @Override
    public Response execute(Request input) {
        List<Lesson> lessons = this.addOnRepository.getLessons();
        if (lessons.isEmpty()) {
            List<LessonInfo> remotes = this.englishVideoRepository.getLessons();
            this.addOnRepository.save(remotes);
        }
        return new Response(lessons);
    }

    @Value
    public static class Request implements UseCase.InputValues {
    }

    @Value
    public static class Response implements UseCase.OutputValues {
        private List<Lesson> lessons;
    }
}
