package jdbc;

import com.demo.jdbc.DbUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

/**
 * Jdbc测试类
 *
 * @author gaoyanzhen
 * @since 2022-05-25
 */
@Slf4j
public class JdbcTest {
    @Test
    public void statementTest() {
        try {
            try (Connection connection = DbUtil.getMysqlConnection();
                 Statement statement = connection.createStatement()) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(String.format("SQLKeywords: %s", metaData.getSQLKeywords()));
                System.out.println(String.format("MaxRowSize: %s", metaData.getMaxRowSize()));
                String sql = "select * from sys_dict where id='1174509082208395266'";
                boolean isResult = statement.execute(sql);
                if (isResult) {
                    try (ResultSet resultSet = statement.getResultSet()) {
                        DbUtil.printResultSet(resultSet);
                    }
                } else {
                    int updateCount = statement.getUpdateCount();
                    System.out.println(updateCount + "rows updated");
                }

            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    @Test
    public void prepareStatementTest() {
        try {
            String sql = "select * from sys_dict where id=?";
            try (Connection connection = DbUtil.getMysqlConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "1174509082208395266");
                boolean isResult = statement.execute(sql);
                if (isResult) {
                    try (ResultSet resultSet = statement.getResultSet()) {
                        DbUtil.printResultSet(resultSet);
                    }
                } else {
                    int updateCount = statement.getUpdateCount();
                    System.out.println(updateCount + "rows updated");
                }

            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }

    }

    @Test
    public void returnGeneratedKeys() {
        try {
            String sql = "insert into test(code) values(?)";
            try (Connection connection = DbUtil.getMysqlConnection();
                 PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, "A001");
                int updateCount = statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int key = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    @Test
    public void resultSetType() {
        try {
            String sql = "select * from test";
            try (Connection connection = DbUtil.getMysqlConnection();
                 PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                DatabaseMetaData metaData = connection.getMetaData();
                log.info("TYPE_FORWARD_ONLY: {}", metaData.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
                log.info("TYPE_SCROLL_SENSITIVE: {}", metaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
                log.info("TYPE_SCROLL_INSENSITIVE: {}", metaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
                log.info("CONCUR_READ_ONLY: {}", metaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY));
                log.info("CONCUR_UPDATABLE: {}", metaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE));
                ResultSet resultSet = statement.executeQuery();
                log.info("ResultSet.type:{}", resultSet.getType());
                log.info("ResultSet.concurrency:{}", resultSet.getConcurrency());
//                Thread.sleep(5000);
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "," + resultSet.getString(2));
                }
                SQLWarning sqlWarning = connection.getWarnings();
                log.info("SQLWarning", sqlWarning);
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    @Test
    public void updatableResultSet() {
        try {
            String sql = "select * from test";
            try (Connection connection = DbUtil.getMysqlConnection();
                 PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                ResultSet resultSet = statement.executeQuery();
                log.info("ResultSet.type:{}", resultSet.getType());
                log.info("ResultSet.concurrency:{}", resultSet.getConcurrency());
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String code = resultSet.getString(2);
                    log.info("第{}行: {},{}", resultSet.getRow(), id, code);
                    if (id == 2) {
                        resultSet.updateString("code", "A002");
                        resultSet.updateRow();
                    }
                }
                // 插入行
                resultSet.moveToInsertRow();
                resultSet.updateInt(1, 8);
                resultSet.updateString(2, "A007");
                log.info("插入第{}行: {},{}", resultSet.getRow(), resultSet.getInt(1), resultSet.getString(2));
                resultSet.insertRow();

                // 删除行
                log.info("移动到第{}行", 2);
                resultSet.absolute(2);
                log.info("删除第{}行: {},{}", resultSet.getRow(), resultSet.getInt(1), resultSet.getString(2));
                resultSet.deleteRow();
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    @Test
    public void rowResultSet() {
        try {
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            CachedRowSet rowSet = rowSetFactory.createCachedRowSet();
            String sql = "select * from test";
            try (Connection connection = DbUtil.getMysqlConnection();
                 PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                ResultSet resultSet = statement.executeQuery();
                log.info("ResultSet.type:{}", resultSet.getType());
                log.info("ResultSet.concurrency:{}", resultSet.getConcurrency());
                rowSet.populate(resultSet);
            }
            DbUtil.printResultSet(rowSet);
//            log.info("rowSet:{}",rowSet);
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }
}
