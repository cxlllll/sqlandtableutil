package net.sushine.demo.sk;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import net.sushine.demo.sk.model.AppBasicInfo;
import net.sushine.demo.sk.model.AppOrder;
import net.sushine.demo.sk.model.AppOrderBasicInfo;
import net.sushine.demo.util.DatabaseHelper;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 租户管理 随机生成 企业APP订单
 */
public class ImportAppOrder {

    private static Snowflake snowflake = IdUtil.getSnowflake(1, 2);

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
//        createNoCommOrgData();
//        // 这个方法执行必须确保上面的方法执行之后，不然数据可能会有偏差
//        createNoCommOrgDataAdditionalRecording();
//        createCommOrgData();
        // 最新文件的导入规则
        createOrgData();
    }

    /**
     * 企业数据生成
     * 分类 中小型企业 非中小型企业两类
     */
    private static void createOrgData() {
        // 企业用户
        List<AppOrder> userList = readAllOrganizationList();
        // 订单APP集合
        List<AppOrderBasicInfo> appOrderAllList = readAllAppOrderListByExcel();
        Map<String, List<AppOrderBasicInfo>> appOrderMap = appOrderAllList.stream().collect(Collectors.groupingBy(e -> e.getHy()));
        List<AppBasicInfo> appList = readAppBasicList();
        // key appNam value appNos
        Map<String, List<AppBasicInfo>> appMap = appList.stream().collect(Collectors.groupingBy(e -> e.getAppNam()));
        // 结果集
        List<AppOrder> appOrders = new ArrayList<>();
        for (AppOrder user : userList) {
            List<AppOrderBasicInfo> appOrderList = appOrderMap.get(user.getHy());
            int i = Objects.equals(user.getHy(), "中小企业") ? RandomUtil.randomInt(2, 3) : RandomUtil.randomInt(3, 4);
            List<Integer> oders = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                int n = RandomUtil.randomInt(0, appOrderList.size() - 1);
                while (oders.contains(n)) {
                    n = RandomUtil.randomInt(0, appOrderList.size() - 1);
                }
                oders.add(n);
                AppOrder appOrder = new AppOrder();
                appOrder.setOrderNo(snowflake.nextIdStr());
                appOrder.setTenantNo("179");
                appOrder.setOrganizationNo(user.getOrganizationNo());
                appOrder.setOrganizationName(user.getOrganizationName());
                List<AppBasicInfo> appBasicInfoList = appMap.getOrDefault(appOrderList.get(n).getAppname(), null);
                if (CollectionUtils.isEmpty(appBasicInfoList)) {
                    continue;
                }
                int m = appBasicInfoList.size() == 1 ? 0 : RandomUtil.randomInt(0, appBasicInfoList.size() - 1);
                appOrder.setAppNo(appBasicInfoList.get(m).getAppNo());
                appOrder.setOpenDtm(new Date(random(1554815321000L, 1669824000000L)));
                appOrders.add(appOrder);
            }
        }
        writeAppOrderList(appOrders);
        System.out.println(appOrders.size());
    }


    /**
     * 标记企业数据生成
     */
    public static void createCommOrgData() {
        // 企业用户
        List<AppOrder> userList = readOrganizationList();
        // 订单APP集合
        List<String> appOrderList = readAppOrderList();
        List<AppBasicInfo> appList = readAppBasicList();
        // key appNam value appNos
        Map<String, List<AppBasicInfo>> appMap = appList.stream().collect(Collectors.groupingBy(e -> e.getAppNam()));
        // 结果集
        List<AppOrder> appOrders = new ArrayList<>();
        for (AppOrder user : userList) {
            int i = RandomUtil.randomInt(3, 5);
            List<Integer> oders = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                int n = RandomUtil.randomInt(0, appOrderList.size() - 1);
                while (oders.contains(n)) {
                    n = RandomUtil.randomInt(0, appOrderList.size() - 1);
                }
                oders.add(n);
                AppOrder appOrder = new AppOrder();
                appOrder.setOrderNo(snowflake.nextIdStr());
                appOrder.setTenantNo("179");
                appOrder.setOrganizationNo(user.getOrganizationNo());
                appOrder.setOrganizationName(user.getOrganizationName());
                List<AppBasicInfo> appBasicInfoList = appMap.getOrDefault(appOrderList.get(n), null);
                if (CollectionUtils.isEmpty(appBasicInfoList)) {
                    continue;
                }
                int m = appBasicInfoList.size() == 1 ? 0 : RandomUtil.randomInt(0, appBasicInfoList.size() - 1);
                appOrder.setAppNo(appBasicInfoList.get(m).getAppNo());
                appOrder.setOpenDtm(new Date(random(1554815321000L, 1669824000000L)));
                appOrders.add(appOrder);
            }
        }
        writeAppOrderList(appOrders);
        System.out.println(appOrders.size());
    }

    /**
     * 标记企业 第一次生成未生成数据补录
     */
    public static void createNoCommOrgDataAdditionalRecording() {
        // 企业用户
        List<AppOrder> userList = readOrganizationNoAppTargetList();
        // 订单APP集合
        List<String> appOrderList = readAppOrderList();
        List<AppBasicInfo> appList = readAppBasicList();
        // key appNam value appNos
        Map<String, List<AppBasicInfo>> appMap = appList.stream().collect(Collectors.groupingBy(e -> e.getAppNam()));
        // 结果集
        List<AppOrder> appOrders = new ArrayList<>();
        for (AppOrder user : userList) {
            int i = RandomUtil.randomInt(3, 5);
            List<Integer> oders = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                int n = RandomUtil.randomInt(0, appOrderList.size() - 1);
                while (oders.contains(n)) {
                    n = RandomUtil.randomInt(0, appOrderList.size() - 1);
                }
                oders.add(n);
                AppOrder appOrder = new AppOrder();
                appOrder.setOrderNo(snowflake.nextIdStr());
                appOrder.setTenantNo("179");
                appOrder.setOrganizationNo(user.getOrganizationNo());
                appOrder.setOrganizationName(user.getOrganizationName());
                List<AppBasicInfo> appBasicInfoList = appMap.getOrDefault(appOrderList.get(n), null);
                if (CollectionUtils.isEmpty(appBasicInfoList)) {
                    continue;
                }
                int m = appBasicInfoList.size() == 1 ? 0 : RandomUtil.randomInt(0, appBasicInfoList.size() - 1);
                appOrder.setAppNo(appBasicInfoList.get(m).getAppNo());
                appOrder.setOpenDtm(new Date(random(1554815321000L, 1669824000000L)));
                appOrders.add(appOrder);
            }
        }
        writeAppOrderList(appOrders);
        System.out.println(appOrders.size());
    }

    /**
     * 标记企业APP生成
     */
    public static void createNoCommOrgData() {
        // 企业用户
        List<AppOrder> userList = readOrganizationNoCommList();
        // 订单APP集合
        List<AppOrderBasicInfo> appOrderList = readAppOrderListByExcel();
        List<AppBasicInfo> appList = readAppBasicList();
        // key appNam value appNos
        Map<String, List<AppBasicInfo>> appMap = appList.stream().collect(Collectors.groupingBy(e -> e.getAppNam()));
        // 结果集
        List<AppOrder> appOrders = new ArrayList<>();
        Map<String, List<AppOrderBasicInfo>> appOrderMap = appOrderList.stream().collect(Collectors.groupingBy(e -> e.getHy()));
        for (AppOrder user : userList) {
            int i = RandomUtil.randomInt(3, 5);
            List<AppOrderBasicInfo> appOrderBasicInfos = appOrderMap.get(user.getHy());
            if (appOrderBasicInfos == null || appOrderBasicInfos.size() == 0) {
                continue;
            }
            List<Integer> oders = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                int n = RandomUtil.randomInt(0, appOrderBasicInfos.size() - 1);
                while (oders.contains(n)) {
                    n = RandomUtil.randomInt(0, appOrderBasicInfos.size() - 1);
                }
                oders.add(n);
                AppOrder appOrder = new AppOrder();
                appOrder.setOrderNo(snowflake.nextIdStr());
                appOrder.setTenantNo("179");
                appOrder.setOrganizationNo(user.getOrganizationNo());
                appOrder.setOrganizationName(user.getOrganizationName());
                List<AppBasicInfo> appBasicInfoList = appMap.getOrDefault(appOrderBasicInfos.get(n).getAppname(), null);
                if (CollectionUtils.isEmpty(appBasicInfoList)) {
                    continue;
                }
                int m = appBasicInfoList.size() == 1 ? 0 : RandomUtil.randomInt(0, appBasicInfoList.size() - 1);
                appOrder.setAppNo(appBasicInfoList.get(m).getAppNo());
                appOrder.setOpenDtm(new Date(random(1554815321000L, 1669824000000L)));
                appOrders.add(appOrder);
            }
        }
        writeAppOrderList(appOrders);
        System.out.println(appOrders.size());
    }

    private static void writeAppOrderList(List<AppOrder> appOrders) {
        StringBuilder batchSql = new StringBuilder();
        for (int i = 0; i < appOrders.size(); i++) {
            AppOrder appOrder = appOrders.get(i);
            StringBuilder stringBuilder = new StringBuilder("INSERT INTO \"public\".\"pm_apporder\"(\"order_no\", \"tenant_no\", \"app_no\", " + "\"organization_no\", \"organization_name\", \"open_dtm\") VALUES (");
            stringBuilder.append("'" + appOrder.getOrderNo() + "',").append("'").append(appOrder.getTenantNo()).append("',")//tenantNo
                    .append("'").append(appOrder.getAppNo()).append("',").append("'").append(appOrder.getOrganizationNo()).append("',").append("'").append(appOrder.getOrganizationName()).append("',").append("'").append(simpleDateFormat.format(appOrder.getOpenDtm())).append("');");
            batchSql.append(stringBuilder);
            if ((i + 1) % 100 == 0) {
                DatabaseHelper.executeUpdate(batchSql.toString());
//                System.out.println(batchSql);
                batchSql = new StringBuilder();
            }
        }
        if (appOrders.size() % 100 != 0) {
//            System.out.println(batchSql);
            DatabaseHelper.executeUpdate(batchSql.toString());
        }
    }

    /**
     * App 基础数据
     *
     * @return
     */
    private static List<AppBasicInfo> readAppBasicList() {
        String sql = "select app_no as appNo,app_name as appNam from pm_application";
        List<AppBasicInfo> appBasicInfos = DatabaseHelper.queryEntityList(AppBasicInfo.class, sql);
        return appBasicInfos;
    }


    /**
     * 读取 用户信息
     *
     * @return
     */
    private static List<AppOrder> readOrganizationList() {
        String sql = "select organization_no as organizationNo,organization_name as organizationName from pm_organization where common_tags is not true;";
        List<AppOrder> userList = DatabaseHelper.queryEntityList(AppOrder.class, sql);
        return userList;
    }

    private static List<AppOrder> readAllOrganizationList() {
        String sql = "select organization_no as organizationNo,organization_name as organizationName,sshy as hy from pm_organization;";
        List<AppOrder> userList = DatabaseHelper.queryEntityList(AppOrder.class, sql);
        return userList;
    }

    private static List<AppOrder> readOrganizationNoAppTargetList() {
        String sql = "select organization_no as organizationNo,organization_name as organizationName from pm_organization where common_tags = 't' and organization_no not in (select DISTINCT organization_no from pm_apporder);";
        List<AppOrder> userList = DatabaseHelper.queryEntityList(AppOrder.class, sql);
        return userList;
    }

    /**
     * 读取 用户信息
     *
     * @return
     */
    private static List<AppOrder> readOrganizationNoCommList() {
        String sql = "select organization_no as organizationNo,organization_name as organizationName,sshy as hy from pm_organization where common_tags = 't';";
        List<AppOrder> userList = DatabaseHelper.queryEntityList(AppOrder.class, sql);
        return userList;
    }

    /**
     * 读取订单APP
     *
     * @return
     */
    private static List<AppOrderBasicInfo> readAppOrderListByExcel() {
        ExcelReader appOrderReader = ExcelUtil.getReader(FileUtil.file("app_order.xlsx"), 0);
        List<AppOrderBasicInfo> appOrderBasicInfos = appOrderReader.readAll(AppOrderBasicInfo.class);
        return appOrderBasicInfos;
    }

    private static List<AppOrderBasicInfo> readAllAppOrderListByExcel() {
        // 202304 app_new.xlsx
        // 202305 apporder_20230505
        ExcelReader appOrderReader = ExcelUtil.getReader(FileUtil.file("apporder_20230505.xlsx"), 0);
        List<AppOrderBasicInfo> appOrderBasicInfos = appOrderReader.readAll(AppOrderBasicInfo.class);
        ExcelReader appOrderReader2 = ExcelUtil.getReader(FileUtil.file("apporder_20230505.xlsx"), 1);
        appOrderBasicInfos.addAll(appOrderReader2.readAll(AppOrderBasicInfo.class));
        return appOrderBasicInfos;
    }

    /**
     * 读取订单APP
     *
     * @return
     */
    private static List<String> readAppOrderList() {
        String apps = "物资超市工业APP," + "供应商管理工业APP," + "供应商分级工业APP," + "询报价采购工业APP," + "招标采购工业APP," + "采购计划管理工业APP," + "订单管理工业APP," + "文档管理工业APP";
        return Arrays.asList(apps.split(","));
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
}
