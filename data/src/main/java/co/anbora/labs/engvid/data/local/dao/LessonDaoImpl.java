package co.anbora.labs.engvid.data.local.dao;

import co.anbora.labs.engvid.data.local.model.LessonTitleVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.util.List;

public class LessonDaoImpl implements LessonDao {

    private static final String INSERT_LESSON_WITH_CONFLICT = "INSERT INTO lessons(lesson_id, title, description, publish_date, render_link, category_, slug, image_url, youtube_id) "
            + "VALUES(:lessonId, :title, :description, "
            + ":date, :renderLink, :category, :slug, :image, :youtube) "
            + "ON CONFLICT (lesson_id) "
            + "DO NOTHING";
    public static final String INSERT_TITLE_WITH_CONFLICT = "INSERT INTO titles(video_slug, render_link, video_category) "
            + "VALUES(:slug, :renderLink, :category) "
            + "ON CONFLICT (video_slug) "
            + "DO NOTHING";

    private static final String SELECT_ALL_UN_SYNC = "SELECT t.* FROM titles t LEFT JOIN lessons l ON (l.slug = t.video_slug) WHERE l.slug IS NULL";
    private static final String SELECT_BY_LESSON_ID = "SELECT * FROM lessons WHERE lesson_id = :lessonId";
    private static final String SELECT_ALL = "SELECT * FROM lessons ORDER BY publish_date DESC";
    private static final String SELECT_ALL_BY_CATEGORY = "SELECT * FROM lessons where category_ = :categoryId ORDER BY publish_date DESC";
    private static final String SELECT_BY_DESCRIPTION = "SELECT * FROM lessons WHERE category_ = :categoryId and description ILIKE ('%' || :search || '%') ORDER BY publish_date DESC";

    private static final String CATEGORY_ID = "categoryId";
    private static final String SEARCH = "search";
    private static final String LESSON_ID = "lessonId";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String DATE = "date";
    private static final String RENDER_LINK = "renderLink";
    private static final String CATEGORY = "category";
    private static final String SLUG = "slug";
    private static final String IMAGE = "image";
    private static final String YOUTUBE = "youtube";
    private static final String SYNC = "sync";

    private Jdbi jdbi;

    public LessonDaoImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public void insert(List<LessonVO> lessons) {
        try (Handle handle = jdbi.open()) {
            PreparedBatch insertBatch = handle.prepareBatch(INSERT_LESSON_WITH_CONFLICT);
            lessons.forEach(lessonInfoVO -> addBatch(lessonInfoVO, insertBatch));
            insertBatch.execute();
        }
    }

    private void addBatch(LessonVO video, PreparedBatch insertBatch) {
        insertBatch.bind(LESSON_ID, video.getId())
                .bind(TITLE, video.getTitle())
                .bind(DESCRIPTION, video.getDescription())
                .bind(DATE, video.getDate())
                .bind(RENDER_LINK, video.getRenderLink())
                .bind(CATEGORY, video.getCategory())
                .bind(SLUG, video.getSlug())
                .bind(IMAGE, video.getImageUrl())
                .bind(YOUTUBE, video.getYoutubeId())
                .add();
    }

    @Override
    public LessonVO findById(Integer id) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery(SELECT_BY_LESSON_ID)
                    .bind(LESSON_ID, id)
                    .map(new LessonRowMapper())
                    .findOnly();
        }
    }

    @Override
    public List<LessonVO> findAll() {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery(SELECT_ALL)
                    .map(new LessonRowMapper())
                    .list();
        }
    }

    @Override
    public List<LessonVO> findAllByCategory(Integer id) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery(SELECT_ALL_BY_CATEGORY)
                    .bind(CATEGORY_ID, id)
                    .map(new LessonRowMapper())
                    .list();
        }
    }

    @Override
    public List<LessonVO> findAllByDescription(Integer id, String searchValue) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery(SELECT_BY_DESCRIPTION)
                    .bind(CATEGORY_ID, id)
                    .bind(SEARCH, searchValue)
                    .map(new LessonRowMapper())
                    .list();
        }
    }

    @Override
    public void insertTitles(List<LessonTitleVO> titles) {
        try (Handle handle = jdbi.open()) {
            PreparedBatch insertBatch = handle.prepareBatch(INSERT_TITLE_WITH_CONFLICT);
            titles.forEach(title -> addBatch(title, insertBatch));
            insertBatch.execute();
        }
    }

    private void addBatch(LessonTitleVO title, PreparedBatch insertBatch) {
        insertBatch.bind(SLUG, title.getSlug())
                .bind(RENDER_LINK, title.getRenderLink())
                .bind(CATEGORY, title.getCategory())
                .add();
    }

    @Override
    public List<LessonTitleVO> findAllUnSync() {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery(SELECT_ALL_UN_SYNC)
                    .map(new LessonTitleRowMapper())
                    .list();
        }
    }
}
