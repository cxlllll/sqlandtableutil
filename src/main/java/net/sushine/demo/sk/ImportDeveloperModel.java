package net.sushine.demo.sk;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import net.sushine.demo.sk.model.*;
import net.sushine.demo.util.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 开发者模型数据
 */
public class ImportDeveloperModel {

    private static Snowflake snowflake = IdUtil.getSnowflake(1, 2);

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 数据写入主线
     *
     * @param args
     */
    public static void main(String[] args) {
        List<ModelBasicInfo> modelBasicInfos = readModelList();
        Map<String, List<ModelBasicInfo>> modelMap = modelBasicInfos.stream().collect(Collectors.groupingBy(e -> e.getUser()));

        List<DeveloperInfo> developerInfos = readDeveloperList();
        List<DeveloperModel> developerApps = new ArrayList<>();
        for (DeveloperInfo developerInfo : developerInfos) {
            List<ModelBasicInfo> modelBasicInfoList = modelMap.getOrDefault(developerInfo.getName(), null);
            if (modelBasicInfoList == null) {
                continue;
            }
            for (int i = 0; i < modelBasicInfoList.size(); i++) {
                DeveloperModel developerApp = new DeveloperModel();
                developerApp.setModelNo(snowflake.nextIdStr());
                developerApp.setTenantNo("179");
                developerApp.setDevloperNo(developerInfo.getDevNo());
                ModelBasicInfo modelBasicInfo = modelBasicInfoList.get(i);
                developerApp.setUpTime(modelBasicInfo.getUpTime());
                developerApp.setStatus(Objects.equals(modelBasicInfo.getStatus(), "1") ? "normal" : "disabled");
                developerApp.setModelRealm(modelBasicInfo.getModelArea());
                developerApp.setName(modelBasicInfo.getModelNam());
                developerApps.add(developerApp);
            }

        }
        System.out.println(developerApps.size());
        writeModelList(developerApps);
    }


    /**
     * 读取模型数据
     *
     * @return
     */
    private static List<ModelBasicInfo> readModelList() {

        String sql = "select online_dtm as upTime, model_nam as modelNam,model_area as modelArea ,model_sta as status,create_usr as user from fm_model_manage_mst ";
        List<ModelBasicInfo> modelBasicInfos = DatabaseHelper.queryEntityList(ModelBasicInfo.class, sql);
        return modelBasicInfos;
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
    private static void writeModelList(List<DeveloperModel> developerModels) {
        StringBuilder batchSql = new StringBuilder();
        for (int i = 0; i < developerModels.size(); i++) {
            DeveloperModel developerModel = developerModels.get(i);

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
            batchSql.append(stringBuilder);
            if ((i + 1) % 100 == 0) {
                DatabaseHelper.executeUpdate(batchSql.toString());
                batchSql = new StringBuilder();
            }
        }
        if (developerModels.size() % 100 != 0) {
            DatabaseHelper.executeUpdate(batchSql.toString());
        }
    }
}
