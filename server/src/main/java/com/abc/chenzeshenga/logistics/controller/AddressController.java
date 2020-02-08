package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.JapanAddressCache;
import com.abc.chenzeshenga.logistics.model.AddressLabel;
import com.abc.vo.Json;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController
@RequestMapping("/address")
public class AddressController {

  private JapanAddressCache japanAddressCache;

  @Autowired
  public AddressController(JapanAddressCache japanAddressCache) {
    this.japanAddressCache = japanAddressCache;
  }

  @GetMapping("/getKen")
  public Json getKen() {
    List<AddressLabel> result = japanAddressCache.getKen();
    return Json.succ().data(result);
  }

  @GetMapping("/getCity")
  public Json getCityByKenId(@RequestParam String kenId) {
    List<AddressLabel> result = japanAddressCache.getCityByKenId(kenId);
    return Json.succ().data(result);
  }

  @GetMapping("/getTown")
  public Json getTownByCityId(@RequestParam String cityId) {
    List<AddressLabel> result = japanAddressCache.getTownByCityId(cityId);
    return Json.succ().data(result);
  }
}
