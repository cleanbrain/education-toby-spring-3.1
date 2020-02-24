package springbook.user.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-01-06
 */
public class JdbcContext {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void executeUpdate(final String query, final String... args) throws SQLException {
        /*executeUpdateWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement(query);

                for (int var = 0; var < args.length; var++) {
                    ps.setString(var + 1, args[var]);
                }

                return ps;
            }
        });*/
    }

    public void executeUpdateWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();

            ps = stmt.makePreparedStatement(c);

            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw e;
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (SQLException e) {

                }
            }

            if (c != null) {
                try {
                    c.close();
                }
                catch (SQLException e) {

                }
            }
        }
    }
}
