package ru.brandanalyst.core.db.provider.mysql;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.brandanalyst.core.db.provider.interfaces.MentionProvider;
import ru.brandanalyst.core.model.Mention;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: alexsen
 * Date: 23.02.12
 * Time: 20:44
 */
public class MySQLMentionProvider implements MentionProvider {
    private SimpleJdbcTemplate jdbcTemplate;

    @Required
    public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Mention> getLatestMentions() {
        return jdbcTemplate.query(
                "SELECT * FROM Graphs"
                        + " INNER JOIN Ticker ON TickerId = Ticker.Id"
                        + " INNER JOIN Brand ON BrandId = Brand.Id"
                        + " AND Tstamp = (SELECT MAX(Tstamp) FROM Graphs) ORDER BY TickerId DESC",
                MappersHolder.MENTION_MAPPER);
    }
}
