package com.example.demo.user.dao.entity

import jakarta.persistence.*


@Entity
@Table(name = "user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "uin", unique = true)
    var uin: String,

    @Column(name = "username")
    var username: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "encrypt_password")
    var encryptPassword: String,

    @Column(name = "avatar")
    var avatar: String,

    @Column(name = "status")
    var status: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "mobile")
    var mobile: String,

    @Column(name = "info")
    var info: String,

    @Column(name = "id_card_type")
    var idCardType: String,

    @Column(name = "id_number")
    var idNumber: String,

    @Column(name = "sex")
    var sex: String,
)
