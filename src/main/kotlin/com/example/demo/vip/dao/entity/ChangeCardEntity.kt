package com.example.demo.vip.dao.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

// 变更卡记录

@Entity
@Table(name = "ms_change_card")
@EntityListeners(AuditingEntityListener::class)
data class ChangeCardEntity(
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

    @Column(name = "old_card_no")
    var oldCardNo: Long,

    @Column(name = "new_card_no")
    var newCardNo: Long,

    @Column(name = "remark")
    var remark: Long,

    @Column(name = "creator")
    var creator: Long,

    @Column(name = "creator_name")
    var creatorName: String,
)