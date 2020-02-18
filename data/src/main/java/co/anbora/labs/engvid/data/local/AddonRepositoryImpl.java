package co.anbora.labs.engvid.data.local;

import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;

import java.util.List;

public class AddonRepositoryImpl implements IAddOnRepository {
    @Override
    public List<LessonInfo> getLessons() {
        return null;
    }

    @Override
    public LessonMedia getLessonMediaById(Integer lessonId) {
        return null;
    }
}
