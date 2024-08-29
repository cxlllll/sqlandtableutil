package net.sushine.demo;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateSqlScript {

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static final Integer gateCount = 500;

    private static final Integer deviceCount = 150000;

    private static final List<Long> gatewayCodeList = new ArrayList<>();

    private static final List<Long> deviceCodeList = new ArrayList<>();

    private static final List<String> gatewayNameList = new ArrayList<>();

    private static final List<String> deviceNameList = new ArrayList<>();

    private static final List<String> gatewaySql = new ArrayList<>();


    static {
        Long gatewayCodeStart = 1505019289516638208L;
        Long deviceCodeStart = 1505102399092555776L;

        for (int i = 0; i < gateCount; i++) {
            gatewayCodeList.add(--gatewayCodeStart);
        }

        for (int i = 0; i < deviceCount; i++) {
            deviceCodeList.add(--deviceCodeStart);
        }

        List<String> deviceNames = Arrays.asList("压风机", "通风机",
                "提升机", "汇流箱", "逆变器",
                "压力机", "量热仪", "电表",
                "荧光仪", "跨带仪");
        deviceNameList.addAll(deviceNames);
        gatewayNameList.add("朗坤边缘网关");

    }

//    private Random

    String gateway_table = "INSERT INTO \"wlgatewaymst\"(\"gateway_no\", \"gateway_id\", \n" +
            "\"gateway_nam\", \"gateway_key\", \"gateway_sta\", \n" +
            " \"device_cod\", \"gateway_dsc\", \"lstusr_id\", \n" +
            " \"lstusr_dtm\", \"fstusr_id\", \"fstusr_dtm\", \"tenant_no\") \n" +
            " VALUES (";


    String device_table = "INSERT INTO \"wldevicemst\"(\"device_no\", \"gateway_no\", \"device_nam\", \n" +
            "        \"device_typ\",  \"device_id\", \"device_key\", \"device_sta\",\n" +
            "        \"timeout\", \"devmodel\", \"lstusr_id\", \"lstusr_dtm\", \n" +
            "        \"fstusr_id\", \"fstusr_dtm\", \"tenant_no\") VALUES (";

//    public void initGatewayCodeList() {
//        for (int i = 0; i < deviceCodeList.size(); i++) {
//
//        }
//    }

    void createGatewaySqlScript() throws IOException {
        //    ('1505019289516638208', 'jXGvMTZoN1XK', '朗坤边缘网关',
//            'sV+ZlNDy6nP9cGR5Sczu2SvWLTHIYHTxycE+LeEHGJ8=',
//            '00', 'Gateway_LK', '朗坤演示环境边缘网关',
//            'GDT', '2022-03-19 11:12:22.497', 'GDT', '2022-03-19 11:12:15.184', '179');

        for (int i = 0; i < gatewayCodeList.size(); i++) {
            final StringBuilder builder = new StringBuilder(gateway_table);
            builder.append("'").append(gatewayCodeList.get(i)).append("'").append(",");
            builder.append("'").append(UUID.randomUUID().toString(), 0, 12).append("'").append(",");
            builder.append("'").append("朗坤边缘网关").append("'").append(",");
            builder.append("'").append(UUID.randomUUID().toString()).append("'").append(",");
            builder.append("'").append("00").append("'").append(",");
            builder.append("'").append(UUID.randomUUID().toString(), 0, 10).append("'").append(",");
            builder.append("'").append("朗坤演示环境边缘网关").append("'").append(",");
            builder.append("'").append("GDT").append("'").append(",");
            builder.append("'").append(df.format(new Date())).append("'").append(",");
            builder.append("'").append("GDT").append("'").append(",");
            builder.append("'").append(df.format(new Date())).append("'").append(",");
            builder.append("'").append("179").append("'").append(");");

            gatewaySql.add(builder.toString());
        }

        final File file = new File("/Users/dingpeng/Desktop/gatewayCreate.sql");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileUtil.appendLines(gatewaySql, file, "UTF-8");
    }

    void createDeviceSqlScript() {

        final StringBuilder builder = new StringBuilder(device_table);
//        builder.append()
    }


//            ('1505102399092555776', '1505019289516638208',
//                    '模拟设备', '00', 'control', 'Va8llUxIr1zhttS0i78CFxnl63tuXSKmcSJiqT+uRQ4=',
//                    '00', '', '', '', '', '', '', ' ', '', '', NULL, '',
//            '60000', '1505019531393761280', '', 'SYS', '2022-03-19 16:46:05.205', 'SYS', '2022-03-19 16:42:30.051', '179');

    public static void main(String[] args) {
//        Set<Integer> set = RandomUtil.randomEleSet(CollUtil.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 19);
//        System.currentTimeMillis()
//        System.out.println(RandomUtils.nextLong(999999999999999999L, 1000000000000000000L));
        final CreateSqlScript createSqlScript = new CreateSqlScript();
        try {
            createSqlScript.createGatewaySqlScript();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
