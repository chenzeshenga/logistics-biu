package com.abc.chenzeshenga.logistics.annotation;

import java.lang.annotation.*;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD}) @Retention(RetentionPolicy.RUNTIME) @Documented
public @interface PermInfo {

    /**
     * 权限值
     *
     * @return 权限值
     */
    String pval() default "";

    /**
     * 权限名称
     * pname的别名
     *
     * @return 权限名称
     */
    String value() default "";

}
