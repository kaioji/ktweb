package com.example.demo.vip.service

import com.example.demo.vip.dao.entity.VipCardEntity
import com.example.demo.vip.dao.repository.VipCardRepository
import com.example.demo.vip.model.CreateCardRequest
import com.example.demo.vip.model.CreateCardResponse
import com.example.demo.vip.model.QueryCardRequest
import com.google.gson.Gson
import jakarta.annotation.Resource
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class VipService(
    private val vipCardRepository: VipCardRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Resource
    private lateinit var gson: Gson

    @Resource
    private lateinit var modelMapper: ModelMapper

    fun createVip(request: CreateCardRequest): CreateCardResponse {
        log.info("createCard request: ${gson.toJson(request)}")
        val hasVip = vipCardRepository.findByUin(request.uin)
        //  通过唯一键保证幂等性
        if (hasVip != null) {
            return CreateCardResponse()
        }
        val entity = modelMapper.map(request, VipCardEntity::class.java)
        vipCardRepository.saveAndFlush(entity)
        return modelMapper.map(entity, CreateCardResponse::class.java)
    }

    fun queryVip(request: QueryCardRequest): CreateCardResponse {
        log.info("queryVip request: ${gson.toJson(request)}")
        return CreateCardResponse()
    }
}