//package net.sushine.demo.sk;
//
//import cn.hutool.core.io.FileUtil;
//import cn.hutool.poi.excel.ExcelReader;
//import cn.hutool.poi.excel.ExcelUtil;
//import net.sushine.demo.sk.model.DeveloperUpdate;
//import net.sushine.demo.util.DatabaseHelper;
//
//import java.util.List;
//
//public class UpdateSushineData {
//    public static void main(String[] args) {
//        //通过sheet编号获取
//        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("sushine_developer_update.xlsx"), 0);
//
//        List<DeveloperUpdate> sushineDevelopers = reader.readAll(DeveloperUpdate.class);
//
//        sushineDevelopers.forEach(e -> {
//            String buildSql = buildUpdateSql(e);
//            DatabaseHelper.executeUpdate(buildSql);
//        });
//    }
//
//    public static String buildUpdateSql(DeveloperUpdate update) {
//        StringBuilder stringBuilder = new StringBuilder("UPDATE pm_sushine_developer SET company_name='");
//        return stringBuilder.append(update.getCompanyName() + "',")
//                .append("user_category='" + update.getUserCategory() + "' ")
//                .append(" WHERE phone_number='" + update.getPhoneNumber() + "'")
//                .toString();
//    }
//}
