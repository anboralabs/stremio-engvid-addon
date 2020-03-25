package co.anbora.labs.engvid.data.local.dao;

import co.anbora.labs.engvid.data.local.model.LessonVO;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonRowMapper implements RowMapper<LessonVO> {
    @Override
    public LessonVO map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new LessonVO(
                rs.getLong("lesson_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("publish_date"),
                rs.getString("render_link"),
                rs.getLong("category_"),
                rs.getString("slug"),
                rs.getString("image_url"),
                rs.getString("youtube_id"),
                rs.getBoolean("sync")
        );
    }
}
