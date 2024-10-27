package com.example.demo.vip.service

import com.example.demo.vip.dao.entity.VipCardEntity
import com.example.demo.vip.dao.repository.VipCardRepository
import com.example.demo.vip.model.CreateCardRequest
import com.example.demo.vip.model.CreateCardResponse
import com.example.demo.vip.model.QueryCardRequest
import com.google.gson.Gson
import jakarta.annotation.Resource
import org.hibernate.exception.ConstraintViolationException
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

/*
* 幂等性实现方案：
* 1.数据库唯一索引;2.乐观锁；3.分布式锁；4.防重表；5.token令牌；6.状态机控制；7.来源+序列号。
* */

@Service
@Transactional(propagation = Propagation.REQUIRED)
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

        //  通过唯一键保证幂等性
        var entity: VipCardEntity
        try {
            entity = modelMapper.map(request, VipCardEntity::class.java)
            vipCardRepository.saveAndFlush(entity)
        } catch (e: ConstraintViolationException) {
            entity = vipCardRepository.findByUin(request.uin)!!
        } catch (e: Exception) {
            log.error(e.message)
            throw e
        }
        return modelMapper.map(entity, CreateCardResponse::class.java)
    }

    fun queryVip(request: QueryCardRequest): CreateCardResponse {
        log.info("queryVip request: ${gson.toJson(request)}")
        return CreateCardResponse()
    }
}


