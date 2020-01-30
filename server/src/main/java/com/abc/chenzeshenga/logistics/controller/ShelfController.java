package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.shelf.ShelfMapper;
import com.abc.chenzeshenga.logistics.model.shelf.Shelf;
import com.abc.chenzeshenga.logistics.service.ShelfService;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzeshenga
 * @since 2020-01-01 23:42
 */
@RestController
@RequestMapping("/shelf")
public class ShelfController {

  @Resource private ShelfMapper shelfMapper;

  private ShelfService shelfService;

  @Autowired
  public ShelfController(ShelfService shelfService) {
    this.shelfService = shelfService;
  }

  @PostMapping("/list")
  @SuppressWarnings("rawtypes")
  public Json list(@RequestParam String reg, @RequestBody String body) {
    JSONObject jsonObject = JSON.parseObject(body);
    Page page = PageUtils.getPageParam(jsonObject);
    Page<Shelf> shelfPage = shelfService.list(page, reg);
    return Json.succ().data("page", shelfPage);
  }

  @GetMapping("/list/enable")
  public Json listEnable() {
    List<Shelf> shelves = shelfService.listEnable();
    return Json.succ().data("data", shelves);
  }

  @GetMapping("/enable")
  public Json enable(@RequestParam String shelfNo) {
    shelfMapper.enable(shelfNo);
    return Json.succ();
  }

  @GetMapping("/disable")
  public Json disable(@RequestParam String shelfNo) {
    shelfMapper.disable(shelfNo);
    return Json.succ();
  }

  @GetMapping("/drop")
  public Json drop(@RequestParam String shelfNo) {
    shelfMapper.drop(shelfNo);
    return Json.succ();
  }

  @PostMapping(value = "/add")
  public Json add(@RequestBody Shelf shelf) {
    shelfService.insert(shelf);
    return Json.succ();
  }
}
