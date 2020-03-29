package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.local.dao.LessonDao;
import co.anbora.labs.engvid.data.local.dao.LessonDaoImpl;
import io.agroal.api.AgroalDataSource;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jdbi.v3.core.Jdbi;

@Dependent
public class JDBIFactory {

  @Inject AgroalDataSource defaultDataSource;

  @Produces
  LessonDao provideLessonDao() {

    Jdbi jdbi = Jdbi.create(defaultDataSource);
    return new LessonDaoImpl(jdbi);
  }
}
