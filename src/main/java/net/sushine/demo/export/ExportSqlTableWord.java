package net.sushine.demo.export;

import cn.hutool.core.date.DateUtil;
import net.sushine.demo.util.DatabaseHelper;
import net.sushine.demo.util.TableToWordUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExportSqlTableWord {

    public static void main(String[] args) {
        List<Tables> tables = null;
        // 1、获取数据库所有表信息(若param传入表名则导出单个表信息)
        List<Tables> tablesResult = new ArrayList<>();
        String sql = "\n" +
                "select relname as name,\n" +
                "cast(obj_description(relfilenode,'pg_class') as varchar) as comment from pg_class c \n" +
                "where  relkind = 'r' and relname not like 'pg_%' and relname not like 'sql_%' \n" +
                "and relname in (select table_name from information_schema.tables where table_schema ='public') \n" +
                "order by relname;";
        tables = DatabaseHelper.queryEntityList(Tables.class, sql);

//        for (Tables table : tables) {
//            for (FmTableRelation fmTableRelation : FmTableRelation.values()) {
//                if (table.getName().equals(fmTableRelation.getTableNam())) {
//                    tablesResult.add(table);
//                    break;
//                }
//            }
//        }
//
//        for (Tables table : tables) {
//            for (TableEmun tableEmun : TableEmun.values()) {
//                if (table.getName().equals(tableEmun.getTableNam())) {
//                    tablesResult.add(table);
//                    break;
//                }
//            }
//        }

        if (CollectionUtils.isEmpty(tables)) {
            return;
        }
        // 2、生成文件名信息 - 年月日时分秒
        String date = DateUtil.formatDate(new Date());
        String docFileName = ExportConstants.FILE_PATH + "\\" + ExportConstants.FILE_NAME + "-" + date + ".doc";

        // 3、调用工具类生成文件
        TableToWordUtil.toWord(tables, docFileName, ExportConstants.FILE_NAME);

        // 4、返回文件地址
        String filePath = docFileName.replaceAll("\\\\", "/");
        return;

    }
}
