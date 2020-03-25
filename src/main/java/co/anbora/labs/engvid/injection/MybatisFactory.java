package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.local.dao.LessonDao;
import co.anbora.labs.engvid.data.local.dao.LessonDaoImpl;
import io.agroal.api.AgroalDataSource;
import org.jdbi.v3.core.Jdbi;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

@Dependent
public class MybatisFactory {

    //@Inject
    //AgroalDataSource defaultDataSource;

    @Produces
    LessonDao provideLessonDao() {

        Jdbi jdbi = Jdbi.create("");
        return new LessonDaoImpl(jdbi);
    }

}
