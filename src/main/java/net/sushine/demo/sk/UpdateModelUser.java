package net.sushine.demo.sk;


import cn.hutool.core.util.RandomUtil;
import net.sushine.demo.util.DatabaseHelper;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 更新模型创建者
 */
public class UpdateModelUser {

    public static void main(String[] args) {
        List<Map<String, Object>> modelInfos = readModel();
        List<Map<String, Object>> factorys = readFactory();
        List<Map<String, Object>> users = readUser();
        if (CollectionUtils.isEmpty(modelInfos) || CollectionUtils.isEmpty(factorys) || CollectionUtils.isEmpty(users)) {
            return;
        }
        Map<String, List<Map<String,Object>>> hyMap = factorys.stream().collect(Collectors.groupingBy(e -> e.get("hy").toString()));
        Map<String, List<Map<String,Object>>> usrMap = users.stream().collect(Collectors.groupingBy(e -> e.get("company_name").toString()));
        for (Map modelInfo : modelInfos) {
            String dustry_nam = modelInfo.get("dustry_nam").toString();
            List<Map<String,Object>> facs = hyMap.get(dustry_nam);
            if (CollectionUtils.isEmpty(facs)) {
                facs = hyMap.get("其他");
            }
            List<Map<String,Object>> userList = getUserList(facs, usrMap);
            if(CollectionUtils.isEmpty(userList)){
                continue;
            }
            int j = userList.size() == 1 ? 0 : RandomUtil.randomInt(0, userList.size() - 1);
            String usr = userList.get(j).get("name").toString();
            System.out.println("update fm_model_manage_mst set create_usr = '" + usr + "' where lin_no = '" + modelInfo.get("lin_no").toString() + "';");
            userList.remove(j);
        }
    }

    public static List<Map<String,Object>> getUserList(List<Map<String,Object>> facs, Map<String, List<Map<String,Object>>> usrMap) {
        int i = facs.size() == 1 ? 0 : RandomUtil.randomInt(0, facs.size() - 1);
        String gs = facs.get(i).get("gs").toString();
        List<Map<String,Object>> userList = usrMap.get(gs);
        if (CollectionUtils.isEmpty(userList)) {
            facs.remove(i);
            if(facs.size() > 0){
                userList = getUserList(facs,usrMap);
            }
        }
        return userList;
    }

    private static List<Map<String, Object>> readUser() {
        String sql = "select company_name,name from pm_sushine_developer where name not in (select create_usr from fm_model_manage_mst);";
        List<Map<String, Object>> modelBasicInfos = DatabaseHelper.executeQuery(sql);
        return modelBasicInfos;
    }

    /**
     * 读取非开发者名单内的模型
     */
    private static List<Map<String, Object>> readModel() {
        String sql = "select * from fm_model_manage_mst where create_usr not in (select name from pm_sushine_developer);";
        List<Map<String, Object>> modelBasicInfos = DatabaseHelper.executeQuery(sql);
        return modelBasicInfos;
    }

    /**
     * 读取行业推荐的公司
     */
    private static List<Map<String, Object>> readFactory() {
        String sql = "select * from pm_hytjgs;";
        List<Map<String, Object>> modelBasicInfos = DatabaseHelper.executeQuery(sql);
        return modelBasicInfos;
    }
}
