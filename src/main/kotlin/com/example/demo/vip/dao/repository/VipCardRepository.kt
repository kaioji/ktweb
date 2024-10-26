package com.example.demo.vip.dao.repository

import com.example.demo.vip.dao.entity.VipCardEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface VipCardRepository : JpaRepository<VipCardEntity, Long>, JpaSpecificationExecutor<VipCardEntity> {
    fun findByUin(uin: String): VipCardEntity?
}