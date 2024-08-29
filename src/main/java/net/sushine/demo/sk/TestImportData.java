package net.sushine.demo.sk;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;

import java.util.*;

public class TestImportData {
    private static String jdbcUrl = "jdbc:postgresql://119.3.32.58:5433/sushine_business";
    private static String user = "postgres";
    private static String password = "Sushine@2022!";

    public static void main(String[] args) {
        List<String> dataList = FileUtil.readLines("E:\\data\\org.txt", CharsetUtil.CHARSET_UTF_8);
        List<Map<String, Object>> dataToImport = new ArrayList<>();
        dataList.forEach(item -> {
            String[] dataItem = (item + "\t补充结尾").replaceAll("\t", "\t").split("\t");
            Map<String, Object> dataItemToImport = new LinkedHashMap<>();
            dataItemToImport.put("organization_no", dataItem[0].trim());
            dataItemToImport.put("organization_name", dataItem[1].trim());
            String regCapStr = dataItem[4].trim();
            String regCapInt = regCapStr.replaceAll("万美元", "")
                    .replaceAll("万港元", "")
                    .replaceAll("万元人民币", "")
                    .replaceAll("万人民币", "")
                    .replaceAll("-", "0")
                    .replaceAll("万元", "")
                    .replaceAll("万", "");
            regCapInt = StrUtil.isEmpty(regCapInt) ? "0" : regCapInt;
            Double regCapDouble = Double.parseDouble(regCapInt) * (regCapStr.contains("万美元") ? 6.5 : (regCapStr.contains("万港元") ? (0.812) : 1));
            dataItemToImport.put("reg_cap", regCapDouble == null ? 0D : regCapDouble);
            dataItemToImport.put("reg_dtm", StrUtil.isBlank(dataItem[5].trim().replaceAll("-", "")) ? null : DateUtil.parse(dataItem[5].trim().replaceAll("-", ""), "yyyy/MM/dd"));
            dataItemToImport.put("fstusr_dtm", StrUtil.isBlank(dataItem[6].trim().replaceAll("-", "")) ? null : DateUtil.parse(dataItem[6].trim().replaceAll("-", ""), "yyyy/MM/dd"));
            dataItemToImport.put("tyshxy_code", dataItem[8].trim());
            dataItemToImport.put("sshy", dataItem[9].trim());
            dataItemToImport.put("address", dataItem[10].trim());
            dataToImport.add(dataItemToImport);
        });

        addData(dataToImport);
    }

    public static void addData(List<Map<String, Object>> dataToImport) {
        List<String> datas = new ArrayList<>();
        dataToImport.forEach(item -> {
            Collection<Object> values = item.values();
            datas.add(StrUtil.join("`", values));
        });
        FileUtil.writeLines(datas, "E:\\data\\org" + new Date().getTime() + ".txt", CharsetUtil.CHARSET_UTF_8);
    }

}
