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

    @Insert("UPDATE lessons "
            + "SET image_url=#{lesson.imageUrl}, youtube_id=#{lesson.youtubeId}, sync=#{lesson.sync} "
            + "WHERE lesson_id=#{lesson.id}"
    )
    void updateMedia(@Param("lesson") LessonMediaVO video);

    @Select("select * from lessons where lesson_id = #{lessonId}")
    LessonVO findById(@Param("lessonId") Integer id);

    @Select("select * from lessons order by publish_date desc")
    List<LessonVO> findAll();

    @Select("select * from lessons where category_ = #{categoryId} order by publish_date desc")
    List<LessonVO> findAllByCategory(@Param("categoryId") Integer id);

    @Select("select * from lessons where category_ = #{categoryId} and description ilike ('%'::varchar || #{descriptionValue} || '%'::varchar) order by publish_date desc")
    List<LessonVO> findAllByDescription(@Param("categoryId") Integer id, @Param("descriptionValue") String searchValue);
}
