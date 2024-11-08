package com.example.demo.common.annotation

import java.lang.annotation.Inherited


@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
annotation class UserAuthenticate(val permission: Boolean)
