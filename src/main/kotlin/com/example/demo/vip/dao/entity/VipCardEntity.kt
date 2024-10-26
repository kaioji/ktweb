package com.example.demo.vip.dao.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "ms_vip_card")
@EntityListeners(AuditingEntityListener::class)
data class VipCardEntity(
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

    //  冗余：相当于外建
    @Column(name = "user_id")
    var userId: Long,

    @Column(name = "username")
    var username: String,

    @Column(name = "card_no")
    @Comment("会员卡编号")
    var cardNo: Long,

    @Column(name = "card_type")
    @Comment("会员类型")
    var cardType: String,

    @Column(name = "card_code")
    @Comment("会员卡内码")
    var cardCode: String,

    @Column(name = "card_status")
    @Comment("会员卡状态")
    var cardStatus: String,

    @Column(name = "card_balance")
    @Comment("卡余额")
    var cardBalance: Double,

    @Column(name = "card_password")
    @Comment("卡密码")
    var cardPassword: String,

    @Column(name = "card_encrypt_password")
    @Comment("卡密码")
    var cardEncryptPassword: String,

    @Column(name = "discount")
    @Comment("卡折扣")
    var discount: Double,

    @Column(name = "validate_date")
    @Comment("有效期")
    var validateDate: LocalDateTime,

    @Column(name = "card_point")
    @Comment("累计积分")
    var totalPoint: Double,

    @Column(name = "card_consumption")
    @Comment("累计消费")
    var totalConsumption: Double,

    @Column(name = "total_recharge")
    @Comment("累计充值")
    var totalRecharge: Long,

    @Column(name = "max_limit_count")
    @Comment("限制最大使用次数")
    var maxLimitCount: Int,

    @Column(name = "last_active_time")
    var lastActiveTime: LocalDateTime,

)

//data class不用初始值，普通class 需要初始值除非使用lateinit
