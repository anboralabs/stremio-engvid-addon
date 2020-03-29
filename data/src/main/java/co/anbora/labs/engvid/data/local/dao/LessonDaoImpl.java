package co.anbora.labs.engvid.data.local.dao;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;

public class LessonDaoImpl implements LessonDao {

  private static final String INSERT_LESSON_WITH_CONFLICT =
      "INSERT INTO lessons(lesson_id, title, description, publish_date, render_link, category_, slug) "
      + "VALUES(:lessonId, :title, :description, "
      + ":date, :renderLink, :category, :slug) "
      + "ON CONFLICT (lesson_id) "
      + "DO NOTHING";
  private static final String UPDATE_MEDIA_INFO =
      "UPDATE lessons "
      + "SET image_url= :image, youtube_id= :youtube, sync= :sync "
      + "WHERE lesson_id= :lessonId";

  private static final String SELECT_BY_LESSON_ID =
      "SELECT * FROM lessons WHERE lesson_id = :lessonId";
  private static final String SELECT_ALL =
      "SELECT * FROM lessons ORDER BY publish_date DESC";
  private static final String SELECT_ALL_BY_CATEGORY =
      "SELECT * FROM lessons where category_ = :categoryId ORDER BY publish_date DESC";
  private static final String SELECT_BY_DESCRIPTION =
      "SELECT * FROM lessons WHERE category_ = :categoryId and description ILIKE ('%' || :search || '%') ORDER BY publish_date DESC";

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

  public LessonDaoImpl(Jdbi jdbi) { this.jdbi = jdbi; }

  @Override
  public void insert(List<LessonInfoVO> lessons) {
    try (Handle handle = jdbi.open()) {
      PreparedBatch insertBatch =
          handle.prepareBatch(INSERT_LESSON_WITH_CONFLICT);
      lessons.forEach(lessonInfoVO -> addBatch(lessonInfoVO, insertBatch));
      insertBatch.execute();
    }
  }

  private void addBatch(LessonInfoVO video, PreparedBatch insertBatch) {
    insertBatch.bind(LESSON_ID, video.getId())
        .bind(TITLE, video.getTitle())
        .bind(DESCRIPTION, video.getDescription())
        .bind(DATE, video.getDate())
        .bind(RENDER_LINK, video.getRenderLink())
        .bind(CATEGORY, video.getCategory())
        .bind(SLUG, video.getSlug())
        .add();
  }

  @Override
  public void updateMedia(LessonMediaVO video) {
    try (Handle handle = jdbi.open()) {
      handle.createUpdate(UPDATE_MEDIA_INFO)
          .bind(LESSON_ID, video.getId())
          .bind(IMAGE, video.getImageUrl())
          .bind(YOUTUBE, video.getYoutubeId())
          .bind(SYNC, video.getSync())
          .execute();
    }
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
      return handle.createQuery(SELECT_ALL).map(new LessonRowMapper()).list();
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
}
