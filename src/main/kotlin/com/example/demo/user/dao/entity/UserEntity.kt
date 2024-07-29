package com.example.demo.user.dao.entity

import jakarta.persistence.*


@Entity
@Table(name = "user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? =null ,

    @Column(name = "username")
    var username: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "gender")
    var gender:Boolean,
)
