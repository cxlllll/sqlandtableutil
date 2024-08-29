package net.sushine.demo.sk;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import net.sushine.demo.sk.model.Device;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceData {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String fileName = "device4";

    public static void main(String[] args) {
        //通过sheet编号获取
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(fileName + ".xlsx"), 0);

        List<Device> devices = reader.readAll(Device.class);
        List<String> list = buildDeveloperProtocolSql(devices);

        FileUtil.writeLines(list, "/Users/dingpeng/Documents/Work/LK/" + fileName + simpleDateFormat.format(new Date()) + ".txt", CharsetUtil.CHARSET_UTF_8);
//        list.forEach(DatabaseHelper::executeUpdate);
//        System.out.println("插入完成");
    }

    private static List<String> buildDeveloperProtocolSql(List<Device> devices) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);

            StringBuilder stringBuilder = new StringBuilder("INSERT INTO \"public\".\"pm_deviceledgermst\"(\"device_no\", \"device_encoding\", \"device_name\", " +
                    "\"device_type\", \"device_location\", \"company\") VALUES (");
            stringBuilder.append("'" + device.getDeviceNo() + "',")
                    .append("'").append(device.getDeviceEncoding()).append("',")
                    .append("'").append(device.getDeviceName()).append("',")
                    .append("'").append(device.getDeviceType()).append("',")

                    .append("'").append(device.getDeviceLocation()).append("',")
                    .append("'").append(device.getCompany()).append("');");
            list.add(stringBuilder.toString());
        }
        return list;
    }
}
