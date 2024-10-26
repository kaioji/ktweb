package com.example.demo.controller

import com.example.demo.vip.model.CreateCardRequest
import com.example.demo.vip.model.CreateCardResponse
import com.example.demo.vip.model.QueryCardRequest
import com.example.demo.vip.service.VipService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("vip")
@Tag(name = "vip接口")
class VipController(
    private val vipService: VipService
) {

    @PostMapping("/create")
    @Operation(summary = "创建用户VIP信息")
    fun createCard(@RequestBody request: CreateCardRequest): CreateCardResponse {
        return vipService.createVip(request)
    }

    @PostMapping("/query")
    @Operation(summary = "查询用户VIP信息")
    fun queryVip(@RequestBody request: QueryCardRequest): CreateCardResponse {
        return vipService.queryVip(request)
    }
}