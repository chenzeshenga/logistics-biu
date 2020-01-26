package com.abc.chenzeshenga.logistics.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Slf4j
public class ObjectUtil {

  public static Object reflectFromOri(Object ori, Object curr) {
    Class oriClazz = ori.getClass();
    Class currClazz = curr.getClass();
    if (oriClazz != currClazz) {
      return curr;
    }
    Field[] fields = curr.getClass().getFields();
    for (Field field : fields) {
      try {
        Method getMethod =
            ori.getClass()
                .getMethod(
                    "get"
                        + field.getName().substring(0, 1).toUpperCase()
                        + field.getName().substring(1));
        Method setMethod =
            ori.getClass()
                .getMethod(
                    "set"
                        + field.getName().substring(0, 1).toUpperCase()
                        + field.getName().substring(1),
                    oriClazz);
        Object oriValue = getMethod.invoke(ori);
        Object currValue = getMethod.invoke(curr);
        if (oriValue == currValue) {
          setMethod.invoke(ori, oriValue);
        } else {
          setMethod.invoke(ori, currValue);
        }
      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        log.error("reflectFromOri");
        log.error("error stack info ", e);
      }
    }
    return ori;
  }
}
