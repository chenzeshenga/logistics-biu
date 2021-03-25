package com.abc.chenzeshenga.logistics.service.common;

import com.abc.chenzeshenga.logistics.mapper.file.FileRecordMapper;
import com.abc.chenzeshenga.logistics.model.file.FileRecord;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.FileUtils;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @author chenzeshenga
 */
@Service
public class FileService extends ServiceImpl<FileRecordMapper, FileRecord> {

    private static final String tempPath = "/app/temp";

    public FileRecord upload(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String uuid = SnowflakeIdWorker.generateStrId();
        String currMonth = DateUtil.getMonthFromDate(new Date());
        FileUtils.createDirectory(tempPath + File.separator + currMonth);
        File tmpFile = new File(tempPath + File.separator + currMonth + File.separator + uuid + "-" + fileName);
        IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(tmpFile));

        FileRecord fileRecord = new FileRecord();
        fileRecord.setUuid(uuid);
        fileRecord.setFileName(fileName);
        fileRecord.setAbsolutePath(tempPath + File.separator + currMonth + File.separator + uuid + "-" + fileName);
        fileRecord.setUploadUser(UserUtils.getUserName());
        fileRecord.setUploadDate(new Date());
        baseMapper.insert(fileRecord);
        return fileRecord;
    }

    public void download(@PathVariable String uuid, HttpServletResponse httpServletResponse) throws IOException {
        FileRecord fileRecord = baseMapper.selectById(uuid);
        if (fileRecord == null) {
            httpServletResponse.getOutputStream().write("no file related".getBytes());
            httpServletResponse.flushBuffer();
        } else {
            httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            httpServletResponse.setHeader(
                    "Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileRecord.getFileName(), "utf-8"));
            File file = new File(fileRecord.getAbsolutePath());
            FileInputStream fileInputStream = new FileInputStream(file);
            IOUtils.copy(fileInputStream, httpServletResponse.getOutputStream());
            httpServletResponse.flushBuffer();
        }
    }

}
