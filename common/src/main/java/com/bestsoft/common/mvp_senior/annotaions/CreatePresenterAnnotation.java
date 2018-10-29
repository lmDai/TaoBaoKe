package com.bestsoft.common.mvp_senior.annotaions;


import com.bestsoft.common.https.BasePresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Administrator
 * @date 2018/10/16  14:24
 * @description一个presenter的注解
 * @Inherited 这个注解表示，只能在类上使用并且是运行时
 **/
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenterAnnotation {
    Class<? extends BasePresenter> value();
}
