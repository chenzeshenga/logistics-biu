package com.abc.chenzeshenga.logistics.controller.common;

import com.abc.chenzeshenga.logistics.service.common.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public void upload(@RequestParam("file") MultipartFile multipartFile) {

    }

}
