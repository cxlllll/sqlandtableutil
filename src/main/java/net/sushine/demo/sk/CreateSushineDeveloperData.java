package net.sushine.demo.sk;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import net.sushine.demo.sk.enums.CategoryType;
import net.sushine.demo.sk.enums.RealmType;
import net.sushine.demo.sk.model.SushineDeveloper;
import net.sushine.demo.util.CardIdUtil;
import net.sushine.demo.util.City;
import net.sushine.demo.util.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CreateSushineDeveloperData {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    private static HashMap<String,List<String>> listHashMap=new HashMap<>();
//    static {
//        listHashMap.put("")
//    }



    public static void main(String[] args) {
//        String sql ="select dev_no devNo from pm_sushine_developer where  to_char(last_login_time,'yyyy-MM-dd hh24:mi:ss')<'2023-03-11 21:08:41' order by last_login_time desc  limit 4635 ";
//        List<Map<String, Object>> sqlrs = DatabaseHelper.executeQuery(sql);
//        for(Map<String, Object> strMaps: sqlrs){
//            String devNo = (String) strMaps.get("devNo");
//            String updatesql ="update pm_sushine_developer set last_login_time='"+simpleDateFormat.format(new Date(random(1678540121000L, 1681045721000L))) +"' where dev_no='"+devNo+"'";
//            DatabaseHelper.executeUpdate(updatesql);
//        }

        String[] sex = new String[]{"man", "women", "man", "women", "man", "women", "man", "women", "man", "women"};
        String[] emailRandomArr = new String[]{"@sohu.com", "@163.com", "@sina.com", "@yahoo.com.cn", "@126.com"};
        //通过sheet编号获取
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("pm_sushine_developer_new.xlsx"), 0);

        List<SushineDeveloper> sushineDevelopers = reader.readAll(SushineDeveloper.class);
        System.out.println(sushineDevelopers.size());
        Collections.shuffle(sushineDevelopers);


        //1240个协议开发者//
//        int personalCount = 5;
//        sushineDevelopers.size()
        for (int i = 0; i < sushineDevelopers.size(); i++) {
            String sex1 = sex[new Random().nextInt(sex.length - 1)];
            SushineDeveloper sushineDeveloper = sushineDevelopers.get(i);

            sushineDeveloper.setSex(sex1);
            sushineDeveloper.setStatus("normal");
            sushineDeveloper.setWorkLength(String.valueOf(new Random().nextInt(13) + 2));
            String randomID = CardIdUtil.getRandomID();
            sushineDeveloper.setCardId(randomID);
            sushineDeveloper.setAreaSheng(City.getNameString(Integer.valueOf(randomID.substring(0, 2))));
            sushineDeveloper.setAreaShi(City.getNameString(Integer.valueOf(randomID.substring(0, 4))));


            sushineDeveloper.setWechat(sushineDeveloper.getPhoneNumber());
            sushineDeveloper.setQq(String.valueOf(random(10322993232L, 17328238923L)));
            sushineDeveloper.setLastLoginTime(new Date(random(1678540121000L, 1681045721000L)));
            sushineDeveloper.setUsername(PinyinUtil.getPinyin(sushineDeveloper.getName(), ""));
            sushineDeveloper.setEmail(PinyinUtil.getPinyin(sushineDeveloper.getName(), "") + emailRandomArr[new Random().nextInt(4)]);
            sushineDeveloper.setIntroduction(sushineDeveloper.getCompanyName() + "的" + sushineDeveloper.getName());

            sushineDeveloper.setTenantNo("179");
            String buildSql = buildDeveloperSql(sushineDeveloper);

            DatabaseHelper.executeUpdate(buildSql);
        }


    }

    public static String getCategoryType(RealmType realmType, int personalCount) {
        switch (realmType) {
            case protocolDev:
                if (personalCount > 0) {
                    return CategoryType.personal.name();
                } else {
                    return CategoryType.enterprise.name();
                }
            case aiModelDev:
            default:
                return CategoryType.enterprise.name();
        }
    }

    private static String buildDeveloperSql(SushineDeveloper sushineDeveloper) {
        StringBuilder stringBuilder = new StringBuilder(" INSERT INTO \"public\".\"pm_sushine_developer\"(" +
                "\"dev_no\", \"fstusr_id\", \"fstusr_dtm\", \"lstusr_id\", \"lstusr_dtm\", \"tenant_no\", \"org_no\", " +
                "\"name\", \"company_name\", \"phone_number\", \"status\", \"introduction\", \"last_login_time\", \"area_sheng\", " +
                "\"area_shi\", \"work_length\", \"sex\", \"qq\", \"wechat\", \"card_id\", \"username\",\"user_category\",\"email\") VALUES (");


//                '12133213223', NULL, NULL, NULL, NULL, '179', NULL, '丁鹏', '南京朗坤智慧股份有限公司', '18860401916', 'normal', '一名工业物联网研发工程师，擅长改bug', '2022-04-09 14:02:48', '江苏省', '南京市', '4', 'man', '173113351', '18860401916', '341024199411079712', NULL);

        stringBuilder.append("'" + sushineDeveloper.getDevNo() + "',")//devNo
                .append("NULL,")//fstusr_id
                .append("NULL,")//fstusr_dtm
                .append("NULL,")//lstusr_id
                .append("NULL,")//lstusr_dtm
                .append("'").append(sushineDeveloper.getTenantNo()).append("',")//tenant_no
                .append("NULL,")//org_no
                .append("'").append(sushineDeveloper.getName()).append("',")//name
                .append("'").append(sushineDeveloper.getCompanyName()).append("',")
                .append("'").append(sushineDeveloper.getPhoneNumber()).append("',")
                .append("'").append(sushineDeveloper.getStatus()).append("',")
                .append("'").append(sushineDeveloper.getIntroduction()).append("',")
                .append("'").append(simpleDateFormat.format(sushineDeveloper.getLastLoginTime())).append("',")
                .append("'").append(sushineDeveloper.getAreaSheng()).append("',")
                .append("'").append(sushineDeveloper.getAreaShi()).append("',")
                .append("'").append(sushineDeveloper.getWorkLength()).append("',")
                .append("'").append(sushineDeveloper.getSex()).append("',")
                .append("'").append(sushineDeveloper.getQq()).append("',")
                .append("'").append(sushineDeveloper.getWechat()).append("',")
                .append("'").append(sushineDeveloper.getCardId()).append("',")
                .append("'").append(sushineDeveloper.getUsername()).append("',")
                .append("'").append(sushineDeveloper.getCategory()).append("',")
                .append("'").append(sushineDeveloper.getEmail())
                .append("')");

        String sql = stringBuilder.toString();
        return sql;

    }

    private static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
