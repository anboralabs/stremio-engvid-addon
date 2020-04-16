package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.constants.ConstantsHelper;
import co.anbora.labs.engvid.domain.exceptions.LessonNotFoundException;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.repository.IRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import com.jasongoodwin.monads.Try;
import lombok.Getter;
import lombok.Value;

import static co.anbora.labs.engvid.domain.constants.StremioConstantsHelper.StremioCatalog.MOVIE;
import static co.anbora.labs.engvid.domain.constants.StremioConstantsHelper.StremioCatalog.VIDEO_PREFIX_ID;


public class GetLessonByIdUseCase extends UseCase<GetLessonByIdUseCase.Request, GetLessonByIdUseCase.Response> {

    private IRepository repository;

    public GetLessonByIdUseCase(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response execute(Request input) {
        if (MOVIE.equals(input.type) && input.isValidVideoId()) {
            return Try.ofFailable(() -> Integer.parseInt(input.getVideoId()))
                        .map(this.repository::getLessonById)
                        .map(Response::new)
                        .orElseThrow(() -> new LessonNotFoundException(input.type, input.id));
        }
        throw new LessonNotFoundException(input.type, input.id);
    }

    @Getter
    public static class Request implements UseCase.InputValues {
        private String type;
        private String id;

        public Request(String type, String id) {
            this.type = type;
            this.id = id;
            if (!isValidVideoId()) {
                throw new LessonNotFoundException(type, id);
            }
        }

        public boolean isValidVideoId() {
            return id != null && !id.isEmpty() && id.startsWith(VIDEO_PREFIX_ID);
        }

        public String getVideoId() {
            return id.replace(VIDEO_PREFIX_ID, ConstantsHelper.EMPTY_VALUE);
        }
    }

    @Value
    public static class Response implements UseCase.OutputValues {
        private Lesson lesson;
    }
}
