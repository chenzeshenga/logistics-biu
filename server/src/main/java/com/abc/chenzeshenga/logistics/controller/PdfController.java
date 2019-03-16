package com.abc.chenzeshenga.logistics.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController @Slf4j @RequestMapping("/pdf") public class PdfController {

    @GetMapping("/ord/{ordno}") public void pdf(HttpServletResponse response, @PathVariable String ordno) throws IOException {

    }

}
