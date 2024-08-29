package net.sushine.demo.sk;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import net.sushine.demo.sk.model.DeveloperApp;
import net.sushine.demo.sk.model.DeveloperModel;
import net.sushine.demo.sk.model.DeveloperProtocol;
import net.sushine.demo.sk.model.SushineDeveloper;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class AppModelProtocol {

    private static Snowflake snowflake = IdUtil.getSnowflake(1, 2);
    private static int[] subTable = new int[]{1, 2, 3, 4, 0, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 0, 11, 2, 6, 8, 4, 23, 11, 0, 2, 4, 5};
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private static List<SushineDeveloper> sushineDevelopers;
    private static Map<String, List<SushineDeveloper>> listMap;

    private static List<DeveloperApp> developerApps;
    public static List<DeveloperModel> developerModels;
    public static List<DeveloperProtocol> developerProtocols;

    public static void main(String[] args) {
        //通过sheet编号获取
        ExcelReader developersReader = ExcelUtil.getReader(FileUtil.file("all_sushine_developer.xlsx"), 0);
        sushineDevelopers = developersReader.readAll(SushineDeveloper.class);
        listMap = sushineDevelopers.stream()
                .collect(Collectors.groupingBy(SushineDeveloper::getName));

        ExcelReader developerAppReader = ExcelUtil.getReader(FileUtil.file("all_developer_app.xlsx"), 0);
        developerApps = developerAppReader.readAll(DeveloperApp.class)
                .stream().peek(o -> o.setUpTime(new Date(random(1649509789000L, 1680786589000L))))
                .collect(Collectors.toList());
        writeAppSqlFile();

        ExcelReader developerModelReader = ExcelUtil.getReader(FileUtil.file("all_developer_model.xlsx"), 0);
        developerModels = developerModelReader.readAll(DeveloperModel.class)
                .stream().peek(o -> o.setUpTime(new Date(random(1649509789000L, 1680786589000L))))
                .collect(Collectors.toList());
        writeModelSqlFile();

        ExcelReader developerProtocolReader = ExcelUtil.getReader(FileUtil.file("all_developer_protocol.xlsx"), 0);
        developerProtocols = developerProtocolReader.readAll(DeveloperProtocol.class)
                .stream().peek(o -> o.setUpTime(new Date(random(1649509789000L, 1680786589000L))))
                .collect(Collectors.toList());
        writeProtocolSqlFile();

    }


    private static void writeAppSqlFile() {
        List<DeveloperApp> sqlProtocols = developerApps.stream()
                .peek(obj -> {
                    obj.setAppNo(String.valueOf(snowflake.nextId()));
                    obj.setStatus("normal");
                    SushineDeveloper developer;
                    if (obj.getDeveloperName() != null && CollectionUtils.isNotEmpty(listMap.get(obj.getDeveloperName()))) {
                        developer = listMap.get(obj.getDeveloperName()).get(0);
                    } else {
                        developer = sushineDevelopers.get(new Random().nextInt(sushineDevelopers.size()-1));
                        sushineDevelopers.remove(developer);
                    //sushineDevelopers.get(subTable[new Random().nextInt(subTable.length - 1)]);
                    }

                    obj.setDevloperNo(developer.getDevNo());
                    obj.setTenantNo(developer.getTenantNo());

                }).collect(Collectors.toList());

        List<String> developerModelSql = buildDeveloperAppSql(sqlProtocols);
        FileUtil.writeLines(developerModelSql, new File("D:\\job\\2023\\双跨\\代码\\数据导入\\" + "developerAppSql.txt"), CharsetUtil.CHARSET_UTF_8);
    }

    private static List<String> buildDeveloperAppSql(List<DeveloperApp> developerApps1) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < developerApps1.size(); i++) {
            DeveloperApp developerApp = developerApps1.get(i);

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

            list.add(stringBuilder.toString());
        }
        return list;
    }


    public static void writeModelSqlFile() {
        List<DeveloperModel> sqlModels = developerModels.stream()
                .peek(o -> {
                    o.setModelNo(String.valueOf(snowflake.nextId()));
                    o.setStatus("normal");
//                    SushineDeveloper developer = listMap.get(o.getDeveloperName()).stream().findFirst()
//                            .orElseGet(() -> sushineDevelopers.get(subTable[new Random().nextInt(subTable.length - 1)]));

                    SushineDeveloper developer;
                    if (o.getDeveloperName() != null && CollectionUtils.isNotEmpty(listMap.get(o.getDeveloperName()))) {
                        developer = listMap.get(o.getDeveloperName()).get(0);
                    } else {
                        developer = sushineDevelopers.get(new Random().nextInt(sushineDevelopers.size()-1));
                        sushineDevelopers.remove(developer);
                        //sushineDevelopers.get(subTable[new Random().nextInt(subTable.length - 1)]);
                    }
                    o.setDevloperNo(developer.getDevNo());
                    o.setTenantNo(developer.getTenantNo());
                }).collect(Collectors.toList());

        List<String> developerModelSql = buildDeveloperModelSql(sqlModels);
        FileUtil.writeLines(developerModelSql, new File("D:\\job\\2023\\双跨\\代码\\数据导入\\"  + "developerModelSql.txt"), CharsetUtil.CHARSET_UTF_8);
    }

    private static List<String> buildDeveloperModelSql(List<DeveloperModel> developerModels1) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < developerModels1.size(); i++) {
            DeveloperModel developerModel = developerModels1.get(i);


            StringBuilder stringBuilder = new StringBuilder("INSERT INTO \"public\".\"pm_developer_model\"(\"model_no\", \"tenant_no\", \"org_no\", " +
                    "\"devloper_no\", \"name\", \"model_realm\", \"status\", \"up_time\") VALUES (");
            stringBuilder.append("'" + developerModel.getModelNo() + "',")
                    .append("'").append(developerModel.getTenantNo()).append("',")//tenantNo
                    .append("NULL,")//org_no
                    .append("'").append(developerModel.getDevloperNo()).append("',")
                    .append("'").append(developerModel.getName()).append("',")
                    .append("'").append(developerModel.getModelRealm()).append("',")
                    .append("'").append(developerModel.getStatus()).append("',")
                    .append("'").append(simpleDateFormat.format(developerModel.getUpTime())).append("');");

            list.add(stringBuilder.toString());
        }
        return list;
    }


    private static void writeProtocolSqlFile() {
        List<DeveloperProtocol> sqlProtocols = developerProtocols.stream()
                .peek(o -> {
                    o.setProtocolNo(String.valueOf(snowflake.nextId()));
                    o.setStatus("normal");
                    SushineDeveloper developer;
                    if (o.getDeveloperName() != null && CollectionUtils.isNotEmpty(listMap.get(o.getDeveloperName()))) {
                        developer = listMap.get(o.getDeveloperName()).get(0);
                    } else {
                        //developer = sushineDevelopers.get(subTable[new Random().nextInt(subTable.length - 1)]);
                        developer = sushineDevelopers.get(new Random().nextInt(sushineDevelopers.size()-1));
                        sushineDevelopers.remove(developer);
                    }

                    o.setDevloperNo(developer.getDevNo());
                    o.setTenantNo(developer.getTenantNo());

                }).collect(Collectors.toList());

        List<String> developerModelSql = buildDeveloperProtocolSql(sqlProtocols);
        FileUtil.writeLines(developerModelSql, new File("D:\\job\\2023\\双跨\\代码\\数据导入\\"  + "developerProtocolSql.txt"), CharsetUtil.CHARSET_UTF_8);

    }

    private static List<String> buildDeveloperProtocolSql(List<DeveloperProtocol> developerProtocols1) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < developerProtocols1.size(); i++) {
            DeveloperProtocol developerProtocol = developerProtocols1.get(i);

            StringBuilder stringBuilder = new StringBuilder("INSERT INTO \"public\".\"pm_developer_protocol\"(\"protocol_no\", \"tenant_no\", \"org_no\", " +
                    "\"devloper_no\", \"name\", \"protocol_realm\", \"status\", \"up_time\", \"description\") VALUES (");
            stringBuilder.append("'" + developerProtocol.getProtocolNo() + "',")
                    .append("'").append(developerProtocol.getTenantNo()).append("',")
                    .append("NULL,")//org_no
                    .append("'").append(developerProtocol.getDevloperNo()).append("',")
                    .append("'").append(developerProtocol.getName()).append("',")
                    .append("'").append(developerProtocol.getProtocolRealm()).append("',")
                    .append("'").append(developerProtocol.getStatus()).append("',")
                    .append("'").append(simpleDateFormat.format(developerProtocol.getUpTime())).append("',")
                    .append("'").append(developerProtocol.getDescription()).append("');");
            list.add(stringBuilder.toString());
        }
        return list;
    }

    //    (2019-04-09 21:08:41)1554815321000--(2022-04-09 21:09:49)1649509789000
    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
}
