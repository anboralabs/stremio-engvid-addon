package co.anbora.labs.engvid.data.remote;

import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPI;
import co.anbora.labs.engvid.data.remote.manager.IVideoManager;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;

import java.util.List;

public class EnglishVideoRepositoryImpl implements IEnglishVideoRepository {

    private IVideoManager videoManager;

    public EnglishVideoRepositoryImpl(IVideoManager videoManager) {
        this.videoManager = videoManager;
    }

    @Override
    public List<LessonInfo> getLessons() {
        return this.videoManager.lessons();
    }

    @Override
    public LessonMedia getLessonMediaById(Integer lessonId) {
        return null;
    }
}
