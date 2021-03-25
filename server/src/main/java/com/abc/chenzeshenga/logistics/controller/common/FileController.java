package com.abc.chenzeshenga.logistics.controller.common;

import com.abc.chenzeshenga.logistics.service.common.FileService;
import com.abc.vo.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author chenzeshenga
 */
@RestController
@RequestMapping("/v2/common/file")
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public Json upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        fileService.upload(multipartFile);
        return Json.succ();
    }

}
