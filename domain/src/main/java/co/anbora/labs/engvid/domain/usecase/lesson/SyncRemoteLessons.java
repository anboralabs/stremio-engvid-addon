package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import lombok.Value;

import java.util.List;

public class SyncRemoteLessons extends UseCase<SyncRemoteLessons.Request, SyncRemoteLessons.Response> {

    private IEnglishVideoRepository englishVideoRepository;

    public SyncRemoteLessons(IEnglishVideoRepository englishVideoRepository) {
        this.englishVideoRepository = englishVideoRepository;
    }

    @Override
    public Response execute(Request input) {
        return new Response(this.englishVideoRepository.getLessons());
    }

    @Value
    public static class Request implements UseCase.InputValues {
    }

    @Value
    public static class Response implements UseCase.OutputValues {
        private List<LessonInfo> lessons;
    }

}
