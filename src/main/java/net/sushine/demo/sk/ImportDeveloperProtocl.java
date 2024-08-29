package net.sushine.demo.sk;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import net.sushine.demo.sk.model.DeveloperInfo;
import net.sushine.demo.sk.model.DeveloperProtocol;
import net.sushine.demo.util.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 开发者协议数据
 */
public class ImportDeveloperProtocl {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Snowflake snowflake = IdUtil.getSnowflake(1, 2);

    /**
     * 数据写入主线
     *
     * @param args
     */
    public static void main(String[] args) {
        List<DeveloperProtocol> developerProtocolAll = readProtoclList();
        Map<String, List<DeveloperProtocol>> modelMap = developerProtocolAll.stream().collect(Collectors.groupingBy(e -> e.getDeveloperName()));

        List<DeveloperInfo> developerInfos = readDeveloperList();
        List<DeveloperProtocol> developerProtocols = new ArrayList<>();
        for (DeveloperInfo developerInfo : developerInfos) {
            List<DeveloperProtocol> developerProtocolList = modelMap.getOrDefault(developerInfo.getName(), null);
            if (developerProtocolList == null) {
                continue;
            }
            for (int i = 0; i < developerProtocolList.size(); i++) {
                DeveloperProtocol developerProtocolNew = new DeveloperProtocol();
                String id = snowflake.nextIdStr();
                DeveloperProtocol developerProtocol = developerProtocolList.get(i);
                developerProtocolNew.setProtocolNo(id);
                developerProtocolNew.setTenantNo("179");
                developerProtocolNew.setDevloperNo(developerInfo.getDevNo());
                developerProtocolNew.setName(developerProtocol.getName());
                developerProtocolNew.setDescription(developerProtocol.getDescription());
                developerProtocolNew.setStatus(Objects.equals(developerProtocol.getStatus(), "0") ? "normal" : "disabled");
                developerProtocolNew.setUpTime(developerProtocol.getUpTime());
                developerProtocolNew.setProtocolRealm(developerProtocol.getProtocolRealm());
                developerProtocols.add(developerProtocolNew);
            }
        }
        System.out.println(developerProtocols.size());
        writeProtoclList(developerProtocols);
    }

    /**
     * 读取协议信息
     *
     * @return
     */
    public static List<DeveloperProtocol> readProtoclList() {
        ExcelReader developerProtocolReader = ExcelUtil.getReader(FileUtil.file("all_developer_protocol.xlsx"), 1);
        List<DeveloperProtocol> developerProtocols = developerProtocolReader.readAll(DeveloperProtocol.class).stream().peek(o -> o.setUpTime(new Date(random(1554815321000L, 1649509789000L)))).collect(Collectors.toList());
        return developerProtocols;
    }

    /**
     * 读取开发者数据
     */
    private static List<DeveloperInfo> readDeveloperList() {
        String sql = "select dev_no as devNo, name from pm_sushine_developer ";
        List<DeveloperInfo> modelBasicInfos = DatabaseHelper.queryEntityList(DeveloperInfo.class, sql);
        return modelBasicInfos;
    }


    /**
     * 写入模型数据
     */
    private static void writeProtoclList(List<DeveloperProtocol> developerProtocols) {
        StringBuilder batchSql = new StringBuilder();
        for (int i = 0; i < developerProtocols.size(); i++) {
            DeveloperProtocol developerProtocol = developerProtocols.get(i);

            StringBuilder stringBuilder = new StringBuilder("INSERT INTO \"public\".\"pm_developer_protocol\"(\"protocol_no\", \"tenant_no\", \"org_no\", " + "\"devloper_no\", \"name\", \"protocol_realm\", \"status\", \"up_time\", \"description\") VALUES (");
            stringBuilder.append("'" + developerProtocol.getProtocolNo() + "',").append("'").append(developerProtocol.getTenantNo()).append("',").append("NULL,")//org_no
                    .append("'").append(developerProtocol.getDevloperNo()).append("',").append("'").append(developerProtocol.getName()).append("',").append("'").append(developerProtocol.getProtocolRealm()).append("',").append("'").append(developerProtocol.getStatus()).append("',").append("'").append(simpleDateFormat.format(developerProtocol.getUpTime())).append("',").append("'").append(developerProtocol.getDescription()).append("');");
            batchSql.append(stringBuilder);
            if ((i + 1) % 100 == 0) {
                DatabaseHelper.executeUpdate(batchSql.toString());
                batchSql = new StringBuilder();
            }
        }
        if (developerProtocols.size() % 100 != 0) {
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
