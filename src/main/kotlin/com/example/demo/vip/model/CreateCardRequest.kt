package com.example.demo.vip.model

import io.swagger.v3.oas.annotations.media.Schema


data class CreateCardRequest(
    @Schema(title = "用户唯一标识")
    var uin: String,
)