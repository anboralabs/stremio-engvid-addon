package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.local.dao.LessonDao;
import co.anbora.labs.engvid.data.local.dao.LessonDaoImpl;
import io.agroal.api.AgroalDataSource;
import org.jdbi.v3.core.Jdbi;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@Dependent
public class JDBIFactory {

    @Inject
    protected AgroalDataSource defaultDataSource;

    @Produces
    public LessonDao provideLessonDao() {

        Jdbi jdbi = Jdbi.create(defaultDataSource);
        return new LessonDaoImpl(jdbi);
    }

}
