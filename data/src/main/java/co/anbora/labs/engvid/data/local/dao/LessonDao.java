package co.anbora.labs.engvid.data.local.dao;

import co.anbora.labs.engvid.data.local.model.LessonTitleVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;

import java.util.List;

public interface LessonDao {

    void insert(List<LessonVO> lessons);

    LessonVO findById(Integer id);

    List<LessonVO> findAll();

    List<LessonVO> findAllByCategory(Integer id);

    List<LessonVO> findAllByDescription(Integer id, String searchValue);

    void insertTitles(List<LessonTitleVO> titles);

    List<LessonTitleVO> findAllUnSync();
}
