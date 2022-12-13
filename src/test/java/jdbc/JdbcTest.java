package jdbc;

import com.demo.jdbc.DbUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import javax.sql.DataSource;
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
    public void databaseMetaData() {
        DataSource ds = DbUtil.getDataSource();
        try (Connection connection = ds.getConnection()) {

            DatabaseMetaData metaData = connection.getMetaData();
            /** 1.获取数据源的基本信息 */
            // getURL()：获取数据库URL。
            // getUserName()：获取数据库已知的用户。
            // getDatabaseProductName()：获取数据库产品名。
            // getDatabaseProductVersion()：获取数据库产品的版本。
            // getDriverMajorVersion()：获取驱动主版本。
            // getDriverMinorVersion()：获取驱动副版本。
            // getSchemaTerm()：获取数据库供应商用于Schema的首选术语。
            // getCatalogTerm()：获取数据库供应商用于Catalog的首选术语。
            // getProcedureTerm()：获取数据库供应商用于Procedure的首选术语。
            // nullsAreSortedHigh()：获取null值是否高排序。
            // nullsAreSortedLow()：获取null值是否低排序。
            // usesLocalFiles()：获取数据库是否将表存储在本地文件中。
            // usesLocalFilePerTable()：获取数据库是否为每个表使用一个文件。
            // getSQLKeywords()：获取数据库SQL关键字。
            System.out.println("1.获取数据源的基本信息");
            System.out.println("数据库URL:" + metaData.getURL());
            System.out.println("数据库用户名:" + metaData.getUserName());
            System.out.println("数据库产品名:" + metaData.getDatabaseProductName());
            System.out.println("数据库产品的版本:" + metaData.getDatabaseProductVersion());
            System.out.println("驱动主版本:" + metaData.getDriverMajorVersion());
            System.out.println("驱动副版本:" + metaData.getDatabaseMinorVersion());
            System.out.println("Schema的首选术语:" + metaData.getSchemaTerm());
            System.out.println("Catalog的首选术语:" + metaData.getCatalogTerm());
            System.out.println("Procedure的首选术语:" + metaData.getProcedureTerm());
            System.out.println("null值是否高排序:" + metaData.nullsAreSortedHigh());
            System.out.println("null值是否低排序:" + metaData.nullsAreSortedLow());
            System.out.println("是否将表存储在本地文件中:" + metaData.usesLocalFiles());
            System.out.println("是否为每个表使用一个文件:" + metaData.usesLocalFilePerTable());
            System.out.println("SQL关键字:" + metaData.getSQLKeywords());
            /** 2.获取数据源支持特性*/
            // supportsAlterTableWithDropColumn()：检索此数据源是否支持带有删除列的ALTER TABLE语句。
            // supportsBatchUpdates()：检索此数据源是否支持批量更新。
            // supportsTableCorrelationNames()：检索此数据源是否支持表相关名称。
            // supportsPositionedDelete()：检索此数据源是否支持定位的DELETE语句。
            // supportsFullOuterJoins()：检索此数据源是否支持完整地嵌套外部连接。
            // supportsStoredProcedures()：检索此数据源是否存储过程。
            // supportsMixedCaseQuotedIdentifiers()：检索此数据源是否将用双引号引起来
            System.out.println("\n2.获取数据源支持特性");
            System.out.println("是否支持带有删除列的ALTER TABLE语句:" + metaData.supportsAlterTableWithDropColumn());
            System.out.println("是否支持批量更新:" + metaData.supportsBatchUpdates());
            System.out.println("是否支持定位的DELETE语句:" + metaData.supportsPositionedDelete());
            System.out.println("是否支持完整地嵌套外部连接:" + metaData.supportsFullOuterJoins());
            System.out.println("是否存储过程:" + metaData.supportsStoredProcedures());
            System.out.println("是否将用双引号引起来:" + metaData.supportsMixedCaseQuotedIdentifiers());

            /** 3.获取数据源限制 */
            // getMaxRowSize()：获取最大行数。
            // getMaxStatementLength()：获取此数据库在SQL语句中允许的最大字符数。
            // getMaxTablesInSelect()：获取此数据库在SELECT语句中允许的最大表数。
            // getMaxConnections()：获取此数据库支持的最大连接数。
            // getMaxCharLiteralLength()：获取数据库支持的字符串字面量长度。
            // getMaxColumnsInTable()：获取数据库表中允许的最大列数。
            System.out.println("\n3.获取数据源限制");
            System.out.println("最大行数:" + metaData.getMaxRowSize());
            System.out.println("SQL语句中允许的最大字符数:" + metaData.getMaxStatementLength());
            System.out.println("SELECT语句中允许的最大表数:" + metaData.getMaxTablesInSelect());
            System.out.println("支持的最大连接数:" + metaData.getMaxConnections());
            System.out.println("支持的字符串字面量长度:" + metaData.getMaxCharLiteralLength());
            System.out.println("表中允许的最大列数:" + metaData.getMaxColumnsInTable());

            /** 4.获取SQL对象及属性 */
            // getSchemas()：获取Schema信息。
            // getCatalogs()：获取Catalog信息。
            // getTables()：获取表信息。
            // getPrimaryKeys()：获取主键信息。
            // getProcedures()：获取存储过程信息。
            // getProcedureColumns()：获取给定类别的存储过程参数和结果列的信息。
            // getUDTs()：获取用户自定义数据类型。
            // getFunctions()：获取函数信息。
            // getFunctionColumns()：获取给定类别的函数参数和结果列的信息。
            System.out.println("\n4.获取SQL对象及属性");
            System.out.println("Schema信息:" + metaData.getSchemas());
            System.out.println("Catalog信息:" + metaData.getCatalogs());
//            System.out.println("表信息:" + metaData.getTables());
//            System.out.println("主键信息:" + metaData.getPrimaryKeys()));
//            System.out.println("存储过程信息:" + metaData.getProcedures());

            /** 5.获取事务支持 */
            // supportsTransactionIsolationLevel(int level)：是否支持某一事务隔离级别。
            // supportsTransactions()：是否支持事务。
            // getDefaultTransactionIsolation()：获取默认的事务隔离级别。
            // supportsMultipleTransactions()：是否支持同时开启多个事务。
            System.out.println("\n5.获取事务支持");
            System.out.println("否支持事务隔离级别【REPEATABLE_READ】:" + metaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_REPEATABLE_READ));
            System.out.println("否支持事务隔离级别【SERIALIZABLE】:" + metaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE));
            System.out.println("是否支持事务:" + metaData.supportsTransactions());
            System.out.println("默认的事务隔离级别:" + metaData.getDefaultTransactionIsolation());
            System.out.println("是否支持同时开启多个事务:" + metaData.supportsMultipleTransactions());
            System.out.println("是否支持保存点:" + metaData.supportsSavepoints());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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
