package co.anbora.labs.engvid.data.local;

import co.anbora.labs.engvid.data.local.dao.LessonDao;
import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;

import java.util.List;
import java.util.function.Function;

public class AddonRepositoryImpl implements IAddOnRepository {

    private Function<LessonMedia, LessonMediaVO> lessonMediaToVOMapper;
    private Function<List<LessonInfo>, List<LessonInfoVO>> listLessonInfoToVOMapper;
    private Function<LessonVO, Lesson> lessonVOMapper;
    private Function<List<LessonVO>, List<Lesson>> listLessonVOMapper;

    private LessonDao lessonDao;

    public AddonRepositoryImpl(Function<LessonMedia, LessonMediaVO> lessonMediaToVOMapper,
                               Function<List<LessonInfo>, List<LessonInfoVO>> listLessonInfoToVOMapper,
                               Function<LessonVO, Lesson> lessonVOMapper,
                               Function<List<LessonVO>, List<Lesson>> listLessonVOMapper,
                               LessonDao lessonDao) {
        this.lessonMediaToVOMapper = lessonMediaToVOMapper;
        this.listLessonInfoToVOMapper = listLessonInfoToVOMapper;
        this.lessonVOMapper = lessonVOMapper;
        this.listLessonVOMapper = listLessonVOMapper;
        this.lessonDao = lessonDao;
    }

    @Override
    public void save(LessonMedia lessonMedia) {
        this.lessonDao.updateMedia(
                lessonMediaToVOMapper.apply(lessonMedia)
        );
    }

    @Override
    public void save(List<LessonInfo> lessons) {
        this.lessonDao.insert(
                listLessonInfoToVOMapper.apply(lessons)
        );
    }

    @Override
    public List<Lesson> getLessons() {
        return listLessonVOMapper.apply(
                this.lessonDao.findAll()
        );
    }

    @Override
    public List<Lesson> getLessonsByCategory(Integer categoryId) {
        return listLessonVOMapper.apply(
                this.lessonDao.findAllByCategory(categoryId)
        );
    }

    @Override
    public Lesson getLessonById(Integer lessonId) {
        return lessonVOMapper.apply(
                this.lessonDao.findById(lessonId)
        );
    }

    @Override
    public List<Lesson> getLessonsByDescription(Integer categoryId, String searchValue) {
        return listLessonVOMapper.apply(
                this.lessonDao.findAllByDescription(categoryId, searchValue)
        );
    }
}
