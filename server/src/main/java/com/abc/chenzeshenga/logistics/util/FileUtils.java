package com.abc.chenzeshenga.logistics.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 文件工具类
 *
 * @author chenzeshenga
 */
@Slf4j
public class FileUtils {

    private FileUtils() {
    }

    /**
     * 创建目录
     *
     * @param directoryName 完整的路径名
     */
    public static void createDirectory(String directoryName) {
        if (!directoryName.endsWith(File.separator)) {
            directoryName = directoryName + File.separator;
        }
        File directory = new File(directoryName);
        if (directory.exists()) {
            return;
        }
        directory.mkdirs();
    }

}
