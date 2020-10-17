package co.anbora.labs.engvid.data.local.dao;

import co.anbora.labs.engvid.data.local.model.LessonTitleVO;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonTitleRowMapper implements RowMapper<LessonTitleVO> {
    @Override
    public LessonTitleVO map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new LessonTitleVO(
                rs.getString("video_slug"),
                rs.getString("render_link"),
                rs.getLong("video_category")
        );
    }
}
