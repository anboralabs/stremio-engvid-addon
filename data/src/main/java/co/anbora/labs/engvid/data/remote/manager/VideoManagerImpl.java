package co.anbora.labs.engvid.data.remote.manager;

import co.anbora.labs.engvid.data.local.dao.LessonDao;
import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPI;
import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;
import co.anbora.labs.engvid.domain.exceptions.LessonNotFoundException;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import com.jasongoodwin.monads.Try;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.INITIAL_PAGE;
import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.MAX_POST_BY_PAGE;

public class VideoManagerImpl implements IVideoManager {

    private Function<LessonInfoDTO, LessonInfoVO> lessonInfoDTOtoVOMapper;
    private Function<List<LessonVO>, List<LessonInfo>> listLessonInfoVOMapper;
    private Function<LessonVO, LessonMedia> lessonToMediaMapper;
    private Function<LessonMediaVO, LessonMedia> lessonMediaMapper;
    private BiFunction<String, Integer, LessonMediaVO> htmlMediaVOMapper;

    private EnglishVideoAPI englishVideoAPI;
    private LessonDao lessonDao;

    public VideoManagerImpl(EnglishVideoAPI englishVideoAPI,
                            LessonDao lessonDao,
                            Function<LessonInfoDTO, LessonInfoVO> lessonInfoDTOtoVOMapper,
                            Function<List<LessonVO>, List<LessonInfo>> listLessonInfoVOMapper,
                            Function<LessonVO, LessonMedia> lessonToMediaMapper,
                            Function<LessonMediaVO, LessonMedia> lessonMediaMapper,
                            BiFunction<String, Integer, LessonMediaVO> htmlMediaVOMapper) {
        this.englishVideoAPI = englishVideoAPI;
        this.lessonDao = lessonDao;
        this.lessonInfoDTOtoVOMapper = lessonInfoDTOtoVOMapper;
        this.listLessonInfoVOMapper = listLessonInfoVOMapper;
        this.lessonToMediaMapper = lessonToMediaMapper;
        this.lessonMediaMapper = lessonMediaMapper;
        this.htmlMediaVOMapper = htmlMediaVOMapper;
    }

    @Override
    public List<LessonInfo> lessons() {

        getLessons(INITIAL_PAGE, MAX_POST_BY_PAGE);

        return listLessonInfoVOMapper.apply(
                lessonDao.findAll()
        );
    }

    private void getLessons(final int page, int maxItems) {
        List<LessonInfoDTO> lessonInfoDTOS = Try.ofFailable(() -> englishVideoAPI.getLessonsByPage(page, maxItems).execute())
                .filter(Response::isSuccessful)
                .map(Response::body)
                .orElse(new ArrayList<>());

        List<LessonInfoVO> lessonInfoVOS = lessonInfoDTOS.stream()
                .map(lessonInfoDTOtoVOMapper)
                .collect(Collectors.toList());

        lessonDao.insert(lessonInfoVOS);

        if (!lessonInfoDTOS.isEmpty()) {
            getLessons(page + 1, maxItems);
        }
    }

    @Override
    public LessonMedia lessonMediaById(Integer lessonId) {

        LessonVO lesson = lessonDao.findById(lessonId);
        if (lesson.isSync()) {
            return lessonToMediaMapper.apply(lesson);
        }
        return getMediaFromApi(lesson.getSlug(), lessonId);
    }

    private LessonMedia getMediaFromApi(String slug, Integer lessonId) {
        LessonMediaVO lessonMediaVO = Try.ofFailable(() -> englishVideoAPI.getMediaInfoBySlug(slug).execute())
                .filter(Response::isSuccessful)
                .map(Response::body)
                .map(response -> htmlMediaVOMapper.apply(response, lessonId))
                .orElseThrow(LessonNotFoundException::new);
        this.lessonDao.insertMedia(lessonMediaVO);
        return lessonMediaMapper.apply(lessonMediaVO);
    }
}
