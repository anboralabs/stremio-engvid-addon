package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import lombok.Value;

import java.util.List;

public class SyncRemoteLessonsUseCase extends UseCase<SyncRemoteLessonsUseCase.Request, SyncRemoteLessonsUseCase.Response> {

    private IAddOnRepository addOnRepository;
    private IEnglishVideoRepository englishVideoRepository;

    public SyncRemoteLessonsUseCase(IAddOnRepository addOnRepository,
                                    IEnglishVideoRepository englishVideoRepository) {
        this.addOnRepository = addOnRepository;
        this.englishVideoRepository = englishVideoRepository;
    }

    @Override
    public Response execute(Request input) {

        List<LessonInfo> lessons = this.englishVideoRepository.getLessons();
        this.addOnRepository.save(lessons);
        return new Response(lessons);
    }

    @Value
    public static class Request implements UseCase.InputValues {
    }

    @Value
    public static class Response implements UseCase.OutputValues {
        private List<LessonInfo> lessons;
    }

}
