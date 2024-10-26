package com.example.demo.vip.dao.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

// 续约记录

@Entity
@Table(name = "ms_recharge_record")
@EntityListeners(AuditingEntityListener::class)
data class RechargeRecordEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @CreatedDate
    @Column(name = "create_time")
    var createTime: LocalDateTime,

    @LastModifiedDate
    @Column(name = "update_time")
    var updateTime: LocalDateTime,

    @Column(name = "user_id")
    var userId: Long,

    @Column(name = "user_name")
    var userName: String,

    @Column(name = "recharge_type")
    var rechargeType: Long,

    @Column(name = "recharge_amount")
    var rechargeAmount: Long,

    @Column(name = "fee_type")
    var feeType: Long,

    @Column(name = "remark")
    var remark: Long,

    @Column(name = "status")
    var status: Long,

    @Column(name = "year")
    var year: Long,

    @Column(name = "month")
    var month: Long,

    @Column(name = "day")
    var day: Long,

    @Column(name = "check_time")
    var checkTime: LocalDateTime,
)