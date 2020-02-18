package co.anbora.labs.engvid.data;

import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import co.anbora.labs.engvid.domain.repository.IRepository;

import java.util.List;

public class RepositoryImpl implements IRepository {

    private IEnglishVideoRepository remoteRepository;
    private IAddOnRepository localRepository;

    @Override
    public List<LessonInfo> getLessons() {
        return null;
    }

    @Override
    public LessonMedia getLessonMediaById(Integer lessonId) {
        return null;
    }

    @Override
    public List<LessonInfo> getLessonsByCategory(Integer categoryId) {
        return null;
    }
}
