package org.crazyit.app.access;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2022, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 *
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit
{
    boolean needLogin() default true;
    // 该注解限制被修饰的方法在指定时间内最多访问几次
    // -1表示不限制
    int seconds() default -1;
    int maxCount() default -1;
}
