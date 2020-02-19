package co.anbora.labs.engvid.data.local.dao;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LessonDao {

    @Insert("merge into lessons(lesson_id, title, description, publish_date, render_link, category_, slug, image_url) "
            + "key (lesson_id) "
            + "values(#{lesson.id}, #{lesson.title}, #{lesson.description}, "
            + "#{lesson.date}, #{lesson.renderLink}, #{lesson.category}, #{lesson.slug}, #{lesson.imageUrl})")
    void insert(@Param("lesson") LessonInfoVO video);

    void insert(List<LessonInfoVO> lessons);

    @Insert("merge into lessons(lesson_id, image_url, youtube_id, sync) key (lesson_id) "
            + "values(#{lesson.id}, #{lesson.imageUrl}, #{lesson.youtubeId}, "
            + "#{lesson.sync})")
    void insertMedia(@Param("lesson") LessonMediaVO video);

    void insertMedia(List<LessonMediaVO> lessons);

    @Select("select * from lessons where lesson_id = #{lessonId}")
    LessonVO findById(@Param("lessonId") Integer id);

    @Select("select * from lessons")
    List<LessonVO> findAll();

    @Select("select * from lessons where category_ = #{categoryId} and sync = true")
    List<LessonVO> findAllByCategory(@Param("categoryId") Integer id);

}
