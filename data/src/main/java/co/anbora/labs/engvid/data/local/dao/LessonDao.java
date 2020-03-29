package co.anbora.labs.engvid.data.local.dao;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import java.util.List;

public interface LessonDao {

  void insert(List<LessonInfoVO> lessons);

  void updateMedia(LessonMediaVO video);

  LessonVO findById(Integer id);

  List<LessonVO> findAll();

  List<LessonVO> findAllByCategory(Integer id);

  List<LessonVO> findAllByDescription(Integer id, String searchValue);
}
