package com.example.demo.vip.dao.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

// 积分增/减记录

@Entity
@Table(name = "ms_point_record")
@EntityListeners(AuditingEntityListener::class)
data class PointRecordEntity(
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

    @Column(name = "card_id")
    var cardId: Long,

    @Column(name = "card_no")
    var cardNo: Long,

    @Column(name = "operation_creator")
    var operationCreator: String,

    @Column(name = "operation_type")
    var operationType: String,
)