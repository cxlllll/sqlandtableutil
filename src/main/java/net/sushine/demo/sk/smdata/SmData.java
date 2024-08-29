package net.sushine.demo.sk.smdata;

import net.sushine.demo.util.DatabaseHelper;

import java.util.*;
import java.util.stream.Collectors;

public class SmData {

    private static Map<String, String> tableInfo = new HashMap<String, String>() {{
        put("fmrtparammst", "reportparam_no,dev_no,param_no");
        put("fmrunrepmst", "runrep_no,dev_no,syslin_no,sys_no,factory_no,param_no");
        put("fmscorelin", "score_no,sys_no,syslin_no,dev_no,pk_value");
        put("fmscoremst", "score_no,dev_no");
        put("fmtyplevellin", "lin_no,typ_no");
        put("fmwarnrecordlin", "warn_no,task_no,eqlevel_no,warn_typ_no");
        put("rldatasetmst", "set_no,task_no");
        put("rltaskmst", "task_no");
        put("tdmdevalarmwarnmst", "alarm_no,productline_no,sys_no,dev_no,syslin_no,level_no,report_no,task_no,alarm_typ");
        put("tdmfaultsupperssionmst", "supper_no,trouble_no,level_no,dev_no");
    }};
    private static Map<String, String> orgMap = new HashMap<String, String>() {{
        put("ASMK", "A");
        put("HJHMK", "B");
        put("MCMK", "C");
        put("WJPMK", "D");
        put("HJWMK", "E");
        put("DKMK", "F");
        put("YHMK", "G");
        put("JZMK", "H");
        put("XZMK", "I");
        put("DDMK", "J");
        put("HYMK", "K");
        put("SYMK", "L");
        put("SLMK", "M");
        put("RNMK", "N");
        put("EHMK", "O");
        put("CJSMK", "P");
        put("CJGMK", "Q");
        put("YDTMK", "R");
        put("SJMK", "S");
    }};


    private static List<String> orgList = new ArrayList<>(orgMap.keySet());

//    private static List<String> orgList  = new ArrayList<String>(){{
//        add("ASMK");
//        add("HJHMK");
//        add("MCMK");
//        add("WJPMK");
//        add("HJWMK");
//        add("DKMK");
//        add("YHMK");
//        add("JZMK");
//        add("XZMK");
//        add("DDMK");
//        add("HYMK");
//        add("SYMK");
//        add("SLMK");
//        add("RNMK");
//        add("EHMK");
//        add("CJSMK");
//        add("CJGMK");
//        add("YDTMK");
//        add("SJMK");
//    }};

    private static String defaultOrg = "1551847602947883008";

    public static void main(String[] args) {
        List<String> sqls = new ArrayList<>();
        // 数据
        Set<String> tables = tableInfo.keySet();
        for (String table : tables) {
            String basicSql = "select column_name as name from information_schema.columns where table_name = '%s';";
            basicSql = String.format(basicSql, table);
            List<SmDataModel> smDataModels = DatabaseHelper.queryEntityList(SmDataModel.class, basicSql);
            List<String> cols = smDataModels.stream().map(e -> e.getName()).collect(Collectors.toList());
            for (int j = 0; j < orgList.size(); j++) {
                StringBuilder str = new StringBuilder();
                str.append("insert into ").append(table).append(" (");
                for (int i = 0; i < cols.size(); i++) {
                    str.append(cols.get(i)).append(",");
                }
                if (cols.size() > 0) {
                    str.delete(str.length() - 1, str.length());
                }
                str.append(") ");
                String keycol = tableInfo.get(table);
                List<String> keyCols = Arrays.asList(keycol.split(","));
                str.append("select ");
                for (int i = 0; i < cols.size(); i++) {
                    if (Objects.equals(cols.get(i), "org_no")) {
                        str.append("'").append(orgList.get(j)).append("' as org_no").append(",");
                    } else if (keyCols.contains(cols.get(i))) {
                        str.append("('").append(orgMap.get(orgList.get(j))).append("'||").append(cols.get(i)).append(") as ").append(cols.get(i)).append(",");
                    } else if(Objects.equals(table,"fmrunrepmst") && Objects.equals(cols.get(i),"point_id")) {
                        str.append("case when coalesce(viba_typ,'') = '' then point_id else ");
                        str.append("('").append(orgMap.get(orgList.get(j))).append("'||").append(cols.get(i)).append(") end as ").append(cols.get(i)).append(",");
                    } else {
                        str.append(cols.get(i)).append(",");
                    }
                }
                if (cols.size() > 0) {
                    str.delete(str.length() - 1, str.length());
                }
                str.append(" from ").append(table);
                str.append(" ").append("where org_no = '").append(defaultOrg).append("';");
                sqls.add(str.toString());
            }
        }
        for (String sql : sqls) {
            System.out.println(sql);
        }

    }
}
