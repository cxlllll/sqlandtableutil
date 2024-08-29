package net.sushine.demo.util;

import cn.hutool.core.util.IdcardUtil;
import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CardIdUtil {

    public static void main(String[] args) {
        final String randomID = CardIdUtil.getRandomID();
        System.out.println(randomID);
        String province = IdcardUtil.getProvinceByIdCard(randomID);
        System.out.println(Integer.valueOf(randomID.substring(0,4)));
        System.out.println( City.getNameString(Integer.valueOf(randomID.substring(0,4))));
//        String city = IdcardUtil.(randomID);
//        Assert.assertEquals(province, "江苏");
        System.out.println(province);
//        System.out.println(city);
    }

    // 随机生成出生年月 7-14
    private static SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
    private static Date beginDate;

    static {
        try {
            beginDate = dft.parse("19981001");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // 随机生成省、自治区、直辖市代码 1-2
    private static String provinces[] = {"11", "12", "13", "14", "15", "21", "22", "23",
            "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
            "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
            "63", "64", "65", "71", "81", "82"};

    // 随机生成地级市、盟、自治州代码 3-4
    private static String citys[] = {"01", "02", "03", "04", "05", "06", "07", "08",
            "09", "10", "21", "22", "23", "24", "25", "26", "27", "28"};

    // 随机生成县、县级市、区代码 5-6
    private static String countys[] = {"01", "02", "03", "04", "05", "06", "07", "08",
            "09", "10", "21", "22", "23", "24", "25", "26", "27", "28",
            "29", "30", "31", "32", "33", "34", "35", "36", "37", "38"};

    // 18位身份证号码各位的含义:
    // 1-2位省、自治区、直辖市代码；
    // 3-4位地级市、盟、自治州代码；
    // 5-6位县、县级市、区代码；
    // 7-14位出生年月日，比如19670401代表1967年4月1日；
    // 15-17位为顺序号，其中17位（倒数第二位）男为单数，女为双数；
    // 18位为校验码，0-9和X。
    // 作为尾号的校验码，是由把前十七位数字带入统一的公式计算出来的，
    // 计算的结果是0-10，如果某人的尾号是0－9，都不会出现X，但如果尾号是10，那么就得用X来代替，
    // 因为如果用10做尾号，那么此人的身份证就变成了19位。X是罗马数字的10，用X来代替10

    @SneakyThrows
    public static String getRandomID() {
        String id = "";

        String province = provinces[new Random().nextInt(provinces.length - 1)];
        String city = citys[new Random().nextInt(citys.length - 1)];
        String county = countys[new Random().nextInt(countys.length - 1)];

        while (City.getNameString(Integer.valueOf(province)) == null ||
                City.getNameString(Integer.valueOf(province + city)) == null ||
                City.getNameString(Integer.valueOf(province + city + county)) == null) {
            province = provinces[new Random().nextInt(provinces.length - 1)];
            city = citys[new Random().nextInt(citys.length - 1)];
            county = countys[new Random().nextInt(countys.length - 1)];
        }


        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE,
                date.get(Calendar.DATE) - new Random().nextInt(365 * 20));
        String birth = dft.format(date.getTime());
        // 随机生成顺序号 15-17
        String no = new Random().nextInt(999) + "";
        // 随机生成校验码 18
        String checks[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "X"};
        String check = checks[new Random().nextInt(checks.length - 1)];
        // 拼接身份证号码
        id = province + city + county + birth + no + check;


        return id;
    }

}
