package com.zhao.util;

import java.util.Date;
import java.util.Random;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-13 19:01
 * @描述 生成随机密码
 */
public class RandomPasswordUtil {
    private static final String[] letterArr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    private static final String[] digitalArr = "0123456789".split("");
    private static final int[] pwdLengthArr = new int[]{6, 7, 8, 9, 10};

    /*
     *@描述  生成6-10位字母数字组合的随机密码
     *@创建时间 2018/6/13 19:12
     *@返回值  String
     **/
    public static String getPassword() {
        StringBuilder pwd = new StringBuilder();
        Random random = new Random(new Date().getTime());
        int pwdLength = pwdLengthArr[random.nextInt(pwdLengthArr.length)];
        boolean useLetter = true;
        //字母数字间隔取值
        for (int i = 0; i < pwdLength; i++) {
            String _char = "";
            if (useLetter) {
                _char = letterArr[random.nextInt(letterArr.length)];
            } else {
                _char = digitalArr[random.nextInt(digitalArr.length)];
            }
            useLetter = !useLetter;
            pwd.append(_char);
        }
        //return pwd.toString();
        //todo: 密码先固定为123456,方便测试
        return  "123456";
    }
}