package com.pzhu.myblog.common.utils;

/**
 * 处理并记录日志文件
 * @Author Guo Huaijian
 * @Date 2020/2/22 14:57
 */
public class LogUtils {
    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
