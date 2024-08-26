package com.example.demo.themis.dao.entity

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "role")
@EntityListeners(AuditingEntityListener::class)
class PolicyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "role")
    val role: String? = null

    val description: String? = null
}