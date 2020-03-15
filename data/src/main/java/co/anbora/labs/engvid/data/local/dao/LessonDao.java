package co.anbora.labs.engvid.data.local.dao;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LessonDao {

    @Insert("INSERT INTO lessons(lesson_id, title, description, publish_date, render_link, category_, slug) "
            + "VALUES(#{lesson.id}, #{lesson.title}, #{lesson.description}, "
            + "#{lesson.date}, #{lesson.renderLink}, #{lesson.category}, #{lesson.slug}) "
            + "ON CONFLICT (lesson_id) "
            + "DO NOTHING"
    )
    void insert(@Param("lesson") LessonInfoVO video);

    void insert(List<LessonInfoVO> lessons);

    @Insert("INSERT INTO lessons(lesson_id, image_url, youtube_id, sync) "
            + "VALUES(#{lesson.id}, #{lesson.imageUrl}, #{lesson.youtubeId}, "
            + "#{lesson.sync}) "
            + "ON CONFLICT (lesson_id) "
            + "DO UPDATE "
            + "SET image_url=#{lesson.imageUrl}, youtube_id=#{lesson.youtubeId}, sync=#{lesson.sync}"
    )
    void insertMedia(@Param("lesson") LessonMediaVO video);

    void insertMedia(List<LessonMediaVO> lessons);

    @Select("select * from lessons where lesson_id = #{lessonId}")
    LessonVO findById(@Param("lessonId") Integer id);

    @Select("select * from lessons order by publish_date desc")
    List<LessonVO> findAll();

    @Select("select * from lessons where category_ = #{categoryId} order by publish_date desc")
    List<LessonVO> findAllByCategory(@Param("categoryId") Integer id);

}
