package com.abc.chenzeshenga.logistics.controller.dict;

import com.abc.chenzeshenga.logistics.model.dict.Dict;
import com.abc.chenzeshenga.logistics.service.dict.IDictService;
import com.abc.vo.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenzeshenga
 * @since 2020-11-30 21:52
 */
@RestController
@RequestMapping("/dictController")
public class DictController {

  private final IDictService dictService;

  @Autowired
  public DictController(IDictService dictService) {
    this.dictService = dictService;
  }

  @GetMapping("/get")
  public Json get() {
    return Json.succ().data(dictService.get());
  }

  @GetMapping("/getById")
  public Json getById(@RequestParam int id) {
    return Json.succ().data(dictService.getById(id));
  }

  @GetMapping("/getByKey")
  public Json getById(@RequestParam String key) {
    return Json.succ().data(dictService.getByKey(key));
  }

  @PostMapping("/insert")
  public void insert(@RequestBody Dict dict) {
    dictService.insert(dict);
  }

  @GetMapping("/delete")
  public void delete(@RequestParam int id) {
    dictService.delete(id);
  }
}
