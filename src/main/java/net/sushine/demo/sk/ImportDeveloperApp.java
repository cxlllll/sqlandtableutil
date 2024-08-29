package net.sushine.demo.sk;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import net.sushine.demo.sk.model.DeveloperApp;
import net.sushine.demo.sk.model.DeveloperInfo;
import net.sushine.demo.util.DatabaseHelper;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 开发者应用随机生成&导入程序
 */
public class ImportDeveloperApp {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Snowflake snowflake = IdUtil.getSnowflake(1, 2);

    /**
     * 数据写入主线
     *
     * @param args
     */
    public static void main(String[] args) {
        // 读取可用APP列表 Excel 文件提供
        List<DeveloperApp> developerApps = readAppList();
        Map<String, List<DeveloperApp>> modelMap = developerApps.stream().collect(Collectors.groupingBy(e -> e.getAppRealm()));

        List<DeveloperInfo> developerInfos = readDeveloperList();
        List<DeveloperApp> developerInfoArrayList = new ArrayList<>();
        for (DeveloperInfo developerInfo : developerInfos) {
            List<DeveloperApp> developerAppList = modelMap.getOrDefault(developerInfo.getName(), null);
            if (developerAppList == null || developerAppList.size() == 0) {
                System.out.println(developerInfo.toString());
                continue;
            }
//            int n = developerAppList.size() > 1 ? RandomUtil.randomInt(1, 2) : 0;
            int n = developerAppList.size() > 1 ? 1 : 0;
            for (int j = 0; j < n; j++) {
                DeveloperApp app = new DeveloperApp();
                String id = snowflake.nextIdStr();

                int i = developerAppList.size() > 1 ? RandomUtil.randomInt(0, developerAppList.size() - 1) : 0;
                DeveloperApp developerApp = developerAppList.get(i);
                app.setAppNo(id);
                app.setTenantNo("179");
                app.setDevloperNo(developerInfo.getDevNo());
                app.setName(developerApp.getName());
                app.setAppRealm(developerApp.getAppRealm());
                app.setStatus("normal");
                app.setUpTime(developerApp.getUpTime());
                developerInfoArrayList.add(app);
            }

        }
        System.out.println(developerInfoArrayList.size());
        writeAppList(developerInfoArrayList);

    }

    /**
     * 读取APP数据
     *
     * @return
     */
    private static List<DeveloperApp> readAppList() {

//        ExcelReader developerAppReader = ExcelUtil.getReader(FileUtil.file("App.xls"), 1);
//        ExcelReader developerAppReader = ExcelUtil.getReader(FileUtil.file("app_new.xlsx"), 2);
        ExcelReader developerAppReader = ExcelUtil.getReader(FileUtil.file("apporder_20230505.xlsx"), 2);
        List<DeveloperApp> developerApps = developerAppReader.readAll(DeveloperApp.class)
                .stream().filter(e -> e.getName() != null && StringUtils.isNotEmpty(e.getName().trim())).peek(o -> o.setUpTime(new Date(random(1554815321000L, 1649509789000L))))
                .collect(Collectors.toList());
        return developerApps;
    }

    /**
     * 读取开发者数据
     */
    private static List<DeveloperInfo> readDeveloperList() {
//        String sql = "select d.dev_no as devNo,  f.hy as name from pm_sushine_developer d left join pm_compare_developer f on d.company_name = f.name  where f.hy is not null  ";
       String sql = "SELECT " +
               "d.dev_no as devNo," +
               "CASE " +
               "WHEN f.hy IS NULL " +
               "AND d.company_name LIKE'%电%' THEN " +
               "'电力、热力生产和供应业' " +
               "WHEN f.hy IS NULL " +
               "AND ( d.company_name LIKE'%陕%' OR d.company_name LIKE'%煤矿%' ) THEN " +
               "'煤炭开采和洗选业' " +
               "WHEN f.hy IS NULL " +
               "AND d.company_name LIKE'%南京%' THEN " +
               "'软件和信息技术服务业' ELSE f.hy " +
               "END AS NAME " +
               "FROM " +
               "pm_sushine_developer d " +
               "LEFT JOIN pm_compare_developer f ON d.company_name = f.NAME " +
               "WHERE " +
               "f.hy IS NOT NULL " +
               "OR ( d.company_name LIKE'%电%' OR d.company_name LIKE'%陕%' OR d.company_name LIKE'%煤矿%' or d.company_name LIKE'%南京%' )";
        List<DeveloperInfo> modelBasicInfos = DatabaseHelper.queryEntityList(DeveloperInfo.class, sql);
        return modelBasicInfos;
    }

    /**
     * 写入APP数据
     */
    private static void writeAppList(List<DeveloperApp> developerApps) {
        StringBuilder batchSql = new StringBuilder();
        for (int i = 0; i < developerApps.size(); i++) {
            DeveloperApp developerApp = developerApps.get(i);

            StringBuilder stringBuilder = new StringBuilder("INSERT INTO \"public\".\"pm_developer_app\"(\"app_no\", \"tenant_no\", \"org_no\", " +
                    "\"devloper_no\", \"name\", \"app_realm\", \"status\", \"up_time\") VALUES (");
            stringBuilder.append("'" + developerApp.getAppNo() + "',")
                    .append("'").append(developerApp.getTenantNo()).append("',")//tenantNo
                    .append("NULL,")//org_no
                    .append("'").append(developerApp.getDevloperNo()).append("',")
                    .append("'").append(developerApp.getName()).append("',")
                    .append("'").append(developerApp.getAppRealm()).append("',")
                    .append("'").append(developerApp.getStatus()).append("',")
                    .append("'").append(simpleDateFormat.format(developerApp.getUpTime())).append("');");

            batchSql.append(stringBuilder);
            if ((i + 1) % 100 == 0) {
                DatabaseHelper.executeUpdate(batchSql.toString());
                batchSql = new StringBuilder();
            }
        }
        if (developerApps.size() % 100 != 0) {
            DatabaseHelper.executeUpdate(batchSql.toString());
        }
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
}
