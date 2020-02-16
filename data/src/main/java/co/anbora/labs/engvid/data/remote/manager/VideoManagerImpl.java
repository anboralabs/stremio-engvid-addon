package co.anbora.labs.engvid.data.remote.manager;

import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPI;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;

import java.util.List;

public class VideoManagerImpl implements IVideoManager {

    private EnglishVideoAPI englishVideoAPI;

    public VideoManagerImpl(EnglishVideoAPI englishVideoAPI) {
        this.englishVideoAPI = englishVideoAPI;
    }

    @Override
    public List<LessonInfo> lessons() {
        return null;
    }
}
