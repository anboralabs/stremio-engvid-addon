package co.anbora.labs.engvid.data.local;

import co.anbora.labs.engvid.data.local.dao.LessonDao;
import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;

import java.util.List;
import java.util.function.Function;

public class AddonRepositoryImpl implements IAddOnRepository {

    private Function<LessonMedia, LessonMediaVO> lessonMediaToVOMapper;
    private Function<List<LessonInfo>, List<LessonInfoVO>> listLessonInfoToVOMapper;
    private Function<LessonVO, LessonMedia> lessonVOtoMediaMapper;
    private Function<List<LessonVO>, List<LessonInfo>> listLessonVOtoInfoMapper;

    private LessonDao lessonDao;

    public AddonRepositoryImpl(Function<LessonMedia, LessonMediaVO> lessonMediaToVOMapper,
                               Function<List<LessonInfo>, List<LessonInfoVO>> listLessonInfoToVOMapper,
                               Function<LessonVO, LessonMedia> lessonVOtoMediaMapper,
                               Function<List<LessonVO>, List<LessonInfo>> listLessonVOtoInfoMapper,
                               LessonDao lessonDao) {
        this.lessonMediaToVOMapper = lessonMediaToVOMapper;
        this.listLessonInfoToVOMapper = listLessonInfoToVOMapper;
        this.lessonVOtoMediaMapper = lessonVOtoMediaMapper;
        this.listLessonVOtoInfoMapper = listLessonVOtoInfoMapper;
        this.lessonDao = lessonDao;
    }

    @Override
    public void save(LessonMedia lessonMedia) {
        this.lessonDao.insertMedia(
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
    public List<LessonInfo> getLessons() {
        return listLessonVOtoInfoMapper.apply(
                this.lessonDao.findAll()
        );
    }

    @Override
    public List<LessonInfo> getLessonsByCategory(Integer categoryId) {
        return listLessonVOtoInfoMapper.apply(
                this.lessonDao.findAllByCategory(categoryId)
        );
    }

    @Override
    public LessonMedia getLessonMediaById(Integer lessonId) {
        return lessonVOtoMediaMapper.apply(
                this.lessonDao.findById(lessonId)
        );
    }
}
