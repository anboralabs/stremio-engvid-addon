package co.anbora.labs.engvid.data.local.dao;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class LessonDaoImpl implements LessonDao {

    private Jdbi jdbi;

    public LessonDaoImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public void insert(LessonInfoVO video) {
        try (Handle handle = jdbi.open()) {
            handle.createUpdate("INSERT INTO lessons(lesson_id, title, description, publish_date, render_link, category_, slug) "
                        + "VALUES(:id, :title, :description, "
                        + ":date, :renderLink, :category, :slug) "
                        + "ON CONFLICT (lesson_id) "
                        + "DO NOTHING")
                    .bind("id", video.getId())
                    .bind("title", video.getTitle())
                    .bind("description", video.getDescription())
                    .bind("date", video.getDate())
                    .bind("renderLink", video.getRenderLink())
                    .bind("category", video.getCategory())
                    .bind("slug", video.getSlug())
                    .execute();
        }
    }

    @Override
    public void insert(List<LessonInfoVO> lessons) {
        /*try (SqlSession session = sqlSessionFactory.openSession()) {
            LessonDao lessonDao = getLessonDao(session);
            lessons.forEach(lessonDao::insert);
            session.commit();
            session.clearCache();
        }*/
    }

    @Override
    public void updateMedia(LessonMediaVO video) {
        /*try (SqlSession session = sqlSessionFactory.openSession()) {
            getLessonDao(session).updateMedia(video);
            session.commit();
        }*/
    }

    @Override
    public LessonVO findById(Integer id) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT * FROM lessons WHERE lesson_id = :lessonId")
                    .bind("lessonId", id)
                    .map(new LessonRowMapper())
                    .findOnly();
        }
    }

    @Override
    public List<LessonVO> findAll() {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT * FROM lessons ORDER BY publish_date DESC")
                    .map(new LessonRowMapper())
                    .list();
        }
    }

    @Override
    public List<LessonVO> findAllByCategory(Integer id) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT * FROM lessons where category_ = :categoryId ORDER BY publish_date DESC")
                    .bind("categoryId", id)
                    .map(new LessonRowMapper())
                    .list();
        }
    }

    @Override
    public List<LessonVO> findAllByDescription(Integer id, String searchValue) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT * FROM lessons WHERE category_ = :categoryId and description ILIKE '%:search%' ORDER BY publish_date DESC")
                    .bind("categoryId", id)
                    .bind("search", searchValue)
                    .map(new LessonRowMapper())
                    .list();
        }
    }
}
