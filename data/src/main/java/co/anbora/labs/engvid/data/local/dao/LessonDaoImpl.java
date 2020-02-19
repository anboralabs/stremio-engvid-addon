package co.anbora.labs.engvid.data.local.dao;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class LessonDaoImpl implements LessonDao {

    private SqlSessionFactory sqlSessionFactory;

    public LessonDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(LessonInfoVO video) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            getLessonDao(session).insert(video);
            session.commit();
        }
    }

    @Override
    public void insert(List<LessonInfoVO> lessons) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            LessonDao lessonDao = getLessonDao(session);
            lessons.forEach(lessonDao::insert);
            session.commit();
            session.clearCache();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void insertMedia(LessonMediaVO video) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            getLessonDao(session).insertMedia(video);
            session.commit();
        }
    }

    @Override
    public void insertMedia(List<LessonMediaVO> lessons) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            LessonDao lessonDao = getLessonDao(session);
            lessons.forEach(lessonDao::insertMedia);
            session.commit();
            session.clearCache();
        }
    }

    @Override
    public LessonVO findById(Integer id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return getLessonDao(session).findById(id);
        }
    }

    @Override
    public List<LessonVO> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return getLessonDao(session).findAll();
        }
    }

    @Override
    public List<LessonVO> findAllByCategory(Integer id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return getLessonDao(session).findAllByCategory(id);
        }
    }

    private LessonDao getLessonDao(SqlSession session) {
        return session.getMapper(LessonDao.class);
    }
}
