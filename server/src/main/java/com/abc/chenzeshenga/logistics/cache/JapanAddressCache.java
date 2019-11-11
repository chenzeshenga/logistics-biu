package com.abc.chenzeshenga.logistics.cache;

import com.abc.chenzeshenga.logistics.mapper.AddressMapper;
import com.abc.chenzeshenga.logistics.model.AddressLabel;
import com.abc.chenzeshenga.logistics.model.JpAddress;
import com.abc.chenzeshenga.logistics.model.JpDetailAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Component @Slf4j public class JapanAddressCache {

    @Resource private AddressMapper addressMapper;

    private Map<String, List<JpDetailAddress>> kenMap = new HashMap<>();

    private Map<String, List<JpDetailAddress>> cityMap = new HashMap<>();

    private Map<String, List<JpDetailAddress>> townMap = new HashMap<>();

    @Scheduled(cron = "0 0 0 * * ?") @PostConstruct public void init() {
        log.info("begin to load japan address cache");
        List<JpDetailAddress> jpDetailAddressList = addressMapper.getAll();
        log.info("japan address cache size is {}", jpDetailAddressList.size());
        jpDetailAddressList.forEach(ken -> {
            List<JpDetailAddress> kenJpDetailAddressList = kenMap.get(ken.getKenId());
            if (kenJpDetailAddressList == null || jpDetailAddressList.isEmpty()) {
                kenJpDetailAddressList = new ArrayList<>();
                kenJpDetailAddressList.add(ken);
            } else {
                kenJpDetailAddressList.add(ken);
            }
            kenMap.put(ken.getKenId(), kenJpDetailAddressList);
        });
        jpDetailAddressList.forEach(city -> {
            List<JpDetailAddress> townJpDetailAddressList = cityMap.get(city.getCityId());
            if (townJpDetailAddressList == null || jpDetailAddressList.isEmpty()) {
                townJpDetailAddressList = new ArrayList<>();
                townJpDetailAddressList.add(city);
            } else {
                townJpDetailAddressList.add(city);
            }
            cityMap.put(city.getCityId(), townJpDetailAddressList);
        });
        jpDetailAddressList.forEach(town -> {
            List<JpDetailAddress> townJpDetailAddressList = townMap.get(town.getTownId());
            if (townJpDetailAddressList == null || jpDetailAddressList.isEmpty()) {
                townJpDetailAddressList = new ArrayList<>();
                townJpDetailAddressList.add(town);
            } else {
                townJpDetailAddressList.add(town);
            }
            townMap.put(town.getTownId(), townJpDetailAddressList);
        });
        log.info("finished loading japan address cache");
    }

    public List<AddressLabel> getKen() {
        Set<AddressLabel> result = new HashSet<>();
        for (Map.Entry<String, List<JpDetailAddress>> entry : kenMap.entrySet()) {
            AddressLabel jpAddress = new AddressLabel();
            jpAddress.setValue(entry.getKey());
            jpAddress.setLabel(entry.getValue().get(0).getKenName());
            result.add(jpAddress);
        }
        return new ArrayList<>(result);
    }

    public List<JpAddress> getAllCity() {
        Set<JpAddress> result = new HashSet<>();
        for (Map.Entry<String, List<JpDetailAddress>> entry : cityMap.entrySet()) {
            JpAddress jpAddress = new JpAddress();
            jpAddress.setId(entry.getKey());
            jpAddress.setName(entry.getValue().get(0).getCityName());
            result.add(jpAddress);
        }
        return new ArrayList<>(result);
    }

    public List<AddressLabel> getCityByKenId(String kenId) {
        Set<AddressLabel> result = new HashSet<>();
        List<JpDetailAddress> jpDetailAddressList = kenMap.get(kenId);
        jpDetailAddressList.forEach(jpDetailAddress -> {
            AddressLabel jpAddress = new AddressLabel();
            jpAddress.setValue(jpDetailAddress.getCityId());
            jpAddress.setLabel(jpDetailAddress.getCityName());
            result.add(jpAddress);
        });
        result.forEach(addressLabel -> addressLabel.setChildren(getTownByCityId(addressLabel.getValue())));
        return new ArrayList<>(result);
    }

    public List<JpAddress> getAllTown() {
        Set<JpAddress> result = new HashSet<>();
        for (Map.Entry<String, List<JpDetailAddress>> entry : townMap.entrySet()) {
            JpAddress jpAddress = new JpAddress();
            jpAddress.setId(entry.getKey());
            jpAddress.setName(entry.getValue().get(0).getTownName());
            result.add(jpAddress);
        }
        return new ArrayList<>(result);
    }

    public List<AddressLabel> getTownByCityId(String cityId) {
        Set<AddressLabel> result = new HashSet<>();
        List<JpDetailAddress> jpDetailAddressList = cityMap.get(cityId);
        jpDetailAddressList.forEach(jpDetailAddress -> {
            AddressLabel jpAddress = new AddressLabel();
            jpAddress.setValue(jpDetailAddress.getTownId());
            jpAddress.setLabel(jpDetailAddress.getTownName());
            result.add(jpAddress);
        });
        return new ArrayList<>(result);
    }

    public JpDetailAddress getJpDetailAddress(Integer kenId, Integer cityId, Integer townId) {
        List<JpDetailAddress> jpDetailAddressList = townMap.get(String.valueOf(townId));
        if (jpDetailAddressList != null && jpDetailAddressList.size() > 0) {
            for (JpDetailAddress jpDetailAddress : jpDetailAddressList) {
                if (String.valueOf(cityId).equals(jpDetailAddress.getCityId()) && String.valueOf(kenId).equals(jpDetailAddress.getKenId())) {
                    return jpDetailAddress;
                }
            }
        }
        return null;
    }

}
