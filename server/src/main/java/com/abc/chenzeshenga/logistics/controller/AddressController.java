package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.JapanAddressCache;
import com.abc.chenzeshenga.logistics.model.AddressLabel;
import com.abc.chenzeshenga.logistics.model.JpAddress;
import com.abc.vo.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController @RequestMapping("/address") public class AddressController {

    private JapanAddressCache japanAddressCache;

    @Autowired public AddressController(JapanAddressCache japanAddressCache) {
        this.japanAddressCache = japanAddressCache;
    }

    @GetMapping("/getKen") public Json getKen() {
        List<AddressLabel> result = japanAddressCache.getKen();
        result.forEach(addressLabel -> {
            addressLabel.setChildren(japanAddressCache.getCityByKenId(addressLabel.getValue()));
        });
        return Json.succ().data(result);
    }

}
