package com.gioov.nimrod.common.exportbyexcel;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExportByExcel {

    /**
     * 导出显示名
     *
     * @return String
     */
    @AliasFor("name")
    String value() default "";

    /**
     * 导出显示名
     *
     * @return String
     */
    @AliasFor("value")
    String name() default "";

    /**
     * @return
     */
    String pattern() default "";

}
