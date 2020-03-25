package co.anbora.labs.engvid.data.local.dao;

import co.anbora.labs.engvid.data.local.model.LessonVO;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonRowMapper implements RowMapper<LessonVO> {
    @Override
    public LessonVO map(ResultSet rs, StatementContext ctx) throws SQLException {
        return null;
    }
}
