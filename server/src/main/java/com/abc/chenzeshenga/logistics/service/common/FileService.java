package com.abc.chenzeshenga.logistics.service.common;

import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.FileUtils;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class FileService {

    private static final String tempPath = "/app/temp";

    public void upload(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String uuid = SnowflakeIdWorker.generateStrId();
        String currMonth = DateUtil.getMonthFromDate(new Date());
        FileUtils.createDirectory(tempPath + currMonth);
        File tmpFile = new File(tempPath + currMonth + File.separator + uuid + multipartFile.getContentType());
        IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(tmpFile));

    }

}
