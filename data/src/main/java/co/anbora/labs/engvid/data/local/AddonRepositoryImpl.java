package co.anbora.labs.engvid.data.local;

import co.anbora.labs.engvid.data.local.dao.LessonDao;
import co.anbora.labs.engvid.data.local.model.LessonTitleVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;

import java.util.List;
import java.util.function.Function;

public class AddonRepositoryImpl implements IAddOnRepository {

    private Function<LessonVO, Lesson> lessonVOMapper;
    private Function<List<LessonVO>, List<Lesson>> listLessonVOMapper;
    private Function<List<LessonTitle>, List<LessonTitleVO>> listTitleMapper;
    private Function<List<LessonTitleVO>, List<LessonTitle>> listTitleVOMapper;
    private Function<List<Lesson>, List<LessonVO>> listLessonMapper;

    private LessonDao lessonDao;

    public AddonRepositoryImpl(Function<LessonVO, Lesson> lessonVOMapper,
                               Function<List<LessonVO>, List<Lesson>> listLessonVOMapper,
                               Function<List<LessonTitle>, List<LessonTitleVO>> listTitleMapper,
                               Function<List<LessonTitleVO>, List<LessonTitle>> listTitleVOMapper,
                               Function<List<Lesson>, List<LessonVO>> listLessonMapper,
                               LessonDao lessonDao) {
        this.lessonVOMapper = lessonVOMapper;
        this.listLessonVOMapper = listLessonVOMapper;
        this.listTitleMapper = listTitleMapper;
        this.listTitleVOMapper = listTitleVOMapper;
        this.listLessonMapper = listLessonMapper;
        this.lessonDao = lessonDao;
    }

    @Override
    public void save(List<Lesson> lessons) {
        this.lessonDao.insert(
                listLessonMapper.apply(lessons)
        );
    }

    @Override
    public void saveTitles(List<LessonTitle> titles) {
        this.lessonDao.insertTitles(
                listTitleMapper.apply(titles)
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

    @Override
    public List<LessonTitle> getUnSyncTitles() {
        return listTitleVOMapper.apply(
                this.lessonDao.findAllUnSync()
        );
    }
}
