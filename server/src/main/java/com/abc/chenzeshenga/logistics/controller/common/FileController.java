package com.abc.chenzeshenga.logistics.controller.common;

import com.abc.chenzeshenga.logistics.service.common.FileService;
import com.abc.vo.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenzeshenga
 */
@RestController
@RequestMapping("/v2/common/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public Json upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return Json.succ().data("data", fileService.upload(multipartFile));
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    public void download(@PathVariable String uuid, HttpServletResponse httpServletResponse) throws IOException {
        fileService.download(uuid, httpServletResponse);
    }
}
