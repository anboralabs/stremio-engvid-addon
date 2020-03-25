package co.anbora.labs.engvid.data.local.dao;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;

import java.util.List;

public interface LessonDao {

    void insert(LessonInfoVO video);

    void insert(List<LessonInfoVO> lessons);

    /*@Insert("UPDATE lessons "
            + "SET image_url=#{lesson.imageUrl}, youtube_id=#{lesson.youtubeId}, sync=#{lesson.sync} "
            + "WHERE lesson_id=#{lesson.id}"
    )*/
    void updateMedia(LessonMediaVO video);

    //@Select("select * from lessons where lesson_id = #{lessonId}")
    LessonVO findById(Integer id);

    List<LessonVO> findAll();

    List<LessonVO> findAllByCategory(Integer id);

    List<LessonVO> findAllByDescription(Integer id, String searchValue);
}
