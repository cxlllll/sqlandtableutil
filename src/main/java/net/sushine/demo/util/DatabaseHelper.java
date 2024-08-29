package net.sushine.demo.util;import lombok.extern.slf4j.Slf4j;import org.apache.commons.collections.MapUtils;import org.apache.commons.dbcp2.BasicDataSource;import org.apache.commons.dbutils.QueryRunner;import org.apache.commons.dbutils.handlers.BeanHandler;import org.apache.commons.dbutils.handlers.BeanListHandler;import org.apache.commons.dbutils.handlers.MapListHandler;import java.io.BufferedReader;import java.io.IOException;import java.io.InputStream;import java.io.InputStreamReader;import java.sql.Connection;import java.sql.SQLException;import java.util.ArrayList;import java.util.List;import java.util.Map;/** * 数据库工具类 */@Slf4jpublic class DatabaseHelper {    private static final ThreadLocal<Connection> CONNECTION_HOLDER;    private static final QueryRunner QUERY_RUNNER;    private static final BasicDataSource DATA_SOURCE;    static {        CONNECTION_HOLDER = new ThreadLocal<>();        QUERY_RUNNER = new QueryRunner();        DATA_SOURCE = new BasicDataSource();//        String dbDriver = DataCache.DB_CONFIG_CACHE.get(DbConfigItem.DB_DRIVER);//        String dbUrl = DataCache.DB_CONFIG_CACHE.get(DbConfigItem.DB_URL);//        String dbUsername = DataCache.DB_CONFIG_CACHE.get(DbConfigItem.DB_USERNAME);//        String dbPassword = DataCache.DB_CONFIG_CACHE.get(DbConfigItem.DB_PASSWORD);//        DATA_SOURCE.setDriverClassName(dbDriver);//        DATA_SOURCE.setUrl(dbUrl);//        DATA_SOURCE.setUsername(dbUsername);//        DATA_SOURCE.setPassword(dbPassword);//        DATA_SOURCE.setMaxTotal(3);//        DATA_SOURCE.setMaxIdle(3);//        DATA_SOURCE.setMaxWaitMillis(2000);//        DATA_SOURCE.setDriverClassName("org.postgresql.Driver");//        DATA_SOURCE.setUrl("jdbc:postgresql://localhost:5432/show-data");//        DATA_SOURCE.setUsername("postgres");//        DATA_SOURCE.setPassword("iamdp123");        /**         * 双跨         *///        DATA_SOURCE.setDriverClassName("org.postgresql.Driver");//        DATA_SOURCE.setUrl("jdbc:postgresql://139.9.169.82:5433/sushine_business");//        DATA_SOURCE.setUsername("postgres");//        DATA_SOURCE.setPassword("Sushine@2022!");        /**         * 陕煤测试数据         */    /*    DATA_SOURCE.setDriverClassName("org.postgresql.Driver");        DATA_SOURCE.setUrl("jdbc:postgresql://168.168.10.107:5433/sushine_sm");        DATA_SOURCE.setUsername("postgres");        DATA_SOURCE.setPassword("Sushine@2022!");*/        /**         * 边云平台 - 云端         */        DATA_SOURCE.setDriverClassName("org.postgresql.Driver");//        DATA_SOURCE.setUrl("jdbc:postgresql://192.168.185.107:30432/sushine_business");        DATA_SOURCE.setUrl("jdbc:postgresql://168.168.10.89:5433/project_cxgc_sushine_business");        DATA_SOURCE.setUsername("postgres");        DATA_SOURCE.setPassword("Luculent@2024!");    }    /**     * 获取数据库连接     *     * @return     */    public static Connection getConnection() {        Connection conn = CONNECTION_HOLDER.get();        if (conn == null) {            try {                conn = DATA_SOURCE.getConnection();                CONNECTION_HOLDER.set(conn);            } catch (Exception e) {                log.error("get connection failure", e);                throw new RuntimeException(e);            }        }        return conn;    }    /**     * 查询实体列表     *     * @param entityClass     * @param sql     * @param <T>     * @return     */    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {        List<T> entityList;        try {            Connection conn = getConnection();            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);        } catch (Exception e) {            log.error("query entity list failure", e);            throw new RuntimeException(e);        }        return entityList;    }    /**     * 查询实体     *     * @param entityClass     * @param sql     * @param params     * @param <T>     * @return     */    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {        T entity;        Connection conn = getConnection();        try {            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);        } catch (Exception e) {            log.error("query entity  failure", e);            throw new RuntimeException(e);        }        return entity;    }    /**     * 执行查询语句     *     * @param sql     * @param params     * @return     */    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {        List<Map<String, Object>> result;        Connection conn = getConnection();        try {             result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);        } catch (Exception e) {            log.error("execute query failure", e);            throw new RuntimeException(e);        }        return result;    }    /**     * 执行更新语句(包括插入，更新，删除)     *     * @param sql     * @param params     * @return     */    public static int executeUpdate(String sql, Object... params) {        int rows = 0;        Connection conn = getConnection();        try {            rows = QUERY_RUNNER.update(conn, sql, params);            System.out.println(sql);        } catch (SQLException e) {//            log.error("execute query failure", e);            throw new RuntimeException(e);        }        return rows;    }    /**     * 得到表名     *     * @param entityClass     * @return     */    private static String getTableName(Class<?> entityClass) {        return entityClass.getSimpleName();    }    /**     * 插入实体     *     * @param entityClass     * @param fieldMap     * @param <T>     * @return     */    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {        if (MapUtils.isEmpty(fieldMap)) {            log.error("can  not insert entity:fielsMap is empty");            return false;        }        String sql = "INSERT INTO " + getTableName(entityClass);        StringBuilder columns = new StringBuilder("(");        StringBuilder values = new StringBuilder("(");        for (String fieldName : fieldMap.keySet()) {            columns.append(fieldName).append(", ");            values.append("?, ");        }        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");//将最后一个“， ”替换成“)”        values.replace(values.lastIndexOf(", "), values.length(), ")");        sql += columns + " VALUES " + values;        Object[] params = fieldMap.values().toArray();        return executeUpdate(sql, params) == 1;//返回一行被修改    }    /**     * 更新实体     *     * @param entityClass     * @param id     * @param fieldMap     * @param <T>     * @return     */    public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {        if (MapUtils.isEmpty(fieldMap)) {            log.error("can  not insert entity:fielsMap is empty");            return false;        }        String sql = "UPDATE " + getTableName(entityClass) + " SET";        StringBuilder columns = new StringBuilder();        for (String fieldName : fieldMap.keySet()) {            columns.append(fieldName).append("=?, ");        }        sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id=?";//拼凑sql语句        List<Object> paramsList = new ArrayList<Object>();        paramsList.addAll(fieldMap.values());        paramsList.add(id);        Object[] params = paramsList.toArray();        return executeUpdate(sql, params) == 1;    }    /**     * 删除实体     *     * @param entityClass     * @param id     * @param <T>     * @return     */    public static <T> boolean deleteEntity(Class<T> entityClass, long id) {        String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id=?";        return executeUpdate(sql, id) == 1;    }    /**     * 执行SQL文件     *     * @param filePath     */    public static void executeSqlFile(String filePath) {        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);        BufferedReader reader = new BufferedReader((new InputStreamReader(is)));        try {            String sql;            while ((sql = reader.readLine()) != null) {                executeUpdate(sql);            }        } catch (IOException e) {//            log.error("execute sql file failure", e);            throw new RuntimeException(e);        }    }}