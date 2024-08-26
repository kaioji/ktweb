package com.example.demo.book.service

import com.example.demo.book.dao.entity.BookEntity
import com.example.demo.book.dao.entity.ExecutionEntity
import com.example.demo.book.dao.repositroy.BookRepository
import com.example.demo.book.dao.repositroy.CategoryRepository
import com.example.demo.book.model.*
import com.example.demo.common.component.JobComponent
import com.example.demo.common.util.ExecutionStatusEnum
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import jakarta.annotation.PostConstruct
import jakarta.annotation.Resource
import jakarta.persistence.criteria.Predicate
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
class BookServiceImp(
    private val modelMapper: ModelMapper,
    private val gson: Gson,
    private val bookRepository: BookRepository,
) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Resource
    private lateinit var jobComponent: JobComponent

    @Resource
    private lateinit var categoryRepository: CategoryRepository

    @Resource(name = "namedParameterJdbcTemplate") //必须指定name
    private lateinit var jdbcTemplate: NamedParameterJdbcTemplate

    @PostConstruct
    fun init() {
        println("BookServiceImp")
//        fun query(id: Int): BookEntity {
//            val book = bookRepository.findById(id).get()
//            book.name = "hello" + random().toString()
//            jobComponent.createUser()
//            jobComponent.updateBook(book.id!!)
//            return book
//        }
        //        val properties = ans.javaClass.kotlin.memberProperties
//        for (property in properties) {
//            // 获取属性名
//            val propName = property.name
//            // 获取属性值
//            val propValue = property.get(ans)
//            println("$propName = $propValue")
//        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun query(request: BookQueryRequest): Page<BookEntity> {
        val list = bookRepository.findAll(
            { root, _, criteriaBuilder ->
                val predicates = mutableListOf<Predicate>()
                if (!request.categoryName.isNullOrBlank()) {
                    val categoryId = categoryRepository.findByName(request.categoryName!!)
                    predicates.add(criteriaBuilder.equal(root.get<String>("category_id"), categoryId))
                }
                if (!request.title.isNullOrBlank()) {
                    predicates.add(criteriaBuilder.equal(root.get<String>("title"), request.title!!))
                }
                if (!request.author.isNullOrBlank()) {
                    predicates.add(criteriaBuilder.like(root.get<String>("author"), request.author!!))
                }
                if (request.viewNum != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get<Int>("view_num"), request.viewNum!!))
                }
                if (request.likeNum != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get<Int>("like_num"), request.likeNum!!))
                }
                if (request.createDate != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get<LocalDateTime>("gmt_create").`as`(LocalDateTime::class.java), request.createDate!!))
                }
                criteriaBuilder.and(*predicates.toTypedArray())
            },
            PageRequest.of(request.page, request.size)
        )
        return list
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun getById(id: Long) = bookRepository.findById(id).getOrNull()

    fun create(request: BookCreateRequest): BookEntity {
        val entity = modelMapper.map(request, BookEntity::class.java)
        log.info("[BookServiceImp] create:${gson.toJson(entity)}")
        val ret = bookRepository.saveAndFlush(entity)
        return ret
    }

    fun modify(request: BookCreateRequest): BookEntity {
        val entity = modelMapper.map(request, BookEntity::class.java)
        log.info("[BookServiceImp] create:${gson.toJson(entity)}")
        val ret = bookRepository.saveAndFlush(entity)
        return ret
    }

    fun test(requests: List<BookQueryRequest>) {
        val josnStr = "{\n" +
                "        \"job_id\": {\n" +
                "            \"agg_cpu_dict\": {\n" +
                "                \"16\": 0,\n" +
                "                \"32\": 0,\n" +
                "                \"64\": 0,\n" +
                "                \"128\": 0\n" +
                "            },\n" +
                "            \"empty_host\": [],\n" +
                "            \"fragment\": true,\n" +
                "            \"taskType\": \"simulate_host_resource_defragment\",\n" +
                "            \"zoneId\": 160001,\n" +
                "            \"status\": 2\n" +
                "        }\n" +
                "    }"
        val d = "{\n" +
                "    \"version\": \"1.0\",\n" +
                "    \"code\": \"OK\",\n" +
                "    \"msg\": \"OK\",\n" +
                "    \"data\": {\n" +
                "        \"31\": {\n" +
                "            \"taskType\": \"simulate_mix_resource\",\n" +
                "            \"zoneId\": 160002,\n" +
                "            \"status\": 2\n" +
                "        }\n" +
                "    }\n" +
                "}"
        val ans = gson.fromJson<Map<String, Any>>(
            JsonParser.parseString(d).asJsonObject.getAsJsonObject("data"),
            object : TypeToken<Map<String, Any>>() {}.type
        )
        val data = JsonParser.parseString(josnStr).asJsonObject
        val map = gson.fromJson<Map<String, Any>>(data, Map::class.java)
    }

    fun findExecutions() = listOf(
        ExecutionEntity(
            id = 134890,
            createDate = LocalDateTime.parse("2024-08-15T21:13:40"),
            modifiedDate = LocalDateTime.parse("2024-08-16T02:08:18"),
            taskId = "task-66bdfeeeea",
            taskName = "Rubik.HostResourceDefragment",
            traceId = "e5b0f6b0-4c58-405d-ae0d-3ee9e13e0589",
            parameters = "{\"regionAlias\":\"cd\",\"zoneId\":160002.0,\"deviceClass\":\"VSELF_6\",\"hostTypes\":[\"T0-CS62X-100G\",\"T0-CS65X-100GS\",\"X0-CS65X-100GS\",\"X0-CS63X-100GS\",\"X0-CC64X-100GS\"],\"fragment\":true,\"memory\":65536.0,\"load\":30.0,\"cbsCount\":9.0,\"clbCount\":9.0,\"migrationRiskLevel\":0.0,\"strategy\":\"DEFAULT\",\"hostSoldRatio\":100.0,\"dryRun\":\"false\",\"jobId\":\"32\"}",
            expectedExecutionTime = LocalDateTime.parse("2024-08-15T21:13:45"),
            executionTime = LocalDateTime.parse("2024-08-16T02:08:19"),
            executionDuration = 511,
            executionResult = "{\n" +
                    "    \"aggCpuDict\": {\n" +
                    "        \"16\": 1.0,\n" +
                    "        \"32\": 2.0,\n" +
                    "        \"64\": 3.0,\n" +
                    "        \"128\": 4.0\n" +
                    "    },\n" +
                    "    \"zoneId\": 160001,\n" +
                    "    \"region\": \"cd\",\n" +
                    "    \"deviceClass\": \"VSELF_6\",\n" +
                    "    \"taskType\": \"host_resource_defragment\",\n" +
                    "    \"emptyHost\":[\"1\",\"2\",\"3\"]\n" +
                    "}",
            status = ExecutionStatusEnum.SUCCEED,
            errorDetail = null,
            serverIp = "kaioji",
            creator = "创建人"
        ),
        ExecutionEntity(
            id = 134890,
            createDate = LocalDateTime.parse("2024-08-15T21:12:42"),
            modifiedDate = LocalDateTime.parse("2024-08-16T02:08:18"),
            taskId = "task-66bdfeeeea",
            taskName = "Rubik.HostResourceDefragment",
            traceId = "e5b0f6b0-4c58-405d-ae0d-3ee9e13e0589",
            parameters = "{\"regionAlias\":\"cd\",\"zoneId\":160002.0,\"deviceClass\":\"VSELF_6\",\"hostTypes\":[\"T0-CS62X-100G\",\"T0-CS65X-100GS\",\"X0-CS65X-100GS\",\"X0-CS63X-100GS\",\"X0-CC64X-100GS\"],\"fragment\":true,\"memory\":65536.0,\"load\":30.0,\"cbsCount\":9.0,\"clbCount\":9.0,\"migrationRiskLevel\":0.0,\"strategy\":\"DEFAULT\",\"hostSoldRatio\":100.0,\"dryRun\":\"false\",\"jobId\":\"49\"}",
            expectedExecutionTime = LocalDateTime.parse("2024-08-15T21:13:45"),
            executionTime = LocalDateTime.parse("2024-08-16T02:08:19"),
            executionDuration = 511,
            executionResult = "{\n" +
                    "    \"aggCpuDict\": {\n" +
                    "        \"16\": 21.0,\n" +
                    "        \"32\": 22.0,\n" +
                    "        \"64\": 23.0,\n" +
                    "        \"128\": 24.0\n" +
                    "    },\n" +
                    "    \"zoneId\": 160001,\n" +
                    "    \"region\": \"cd\",\n" +
                    "    \"deviceClass\": \"VSELF_6\",\n" +
                    "    \"taskType\": \"host_resource_defragment\",\n" +
                    "    \"emptyHost\":[\"1\",\"2\",\"3\"]\n" +
                    "}",
            status = ExecutionStatusEnum.FAILED,
            errorDetail = null,
            serverIp = "kaioji",
            creator = "创建人"
        ),
        ExecutionEntity(
            id = 134890,
            createDate = LocalDateTime.parse("2024-08-15T21:11:45"),
            modifiedDate = LocalDateTime.parse("2024-08-16T02:08:18"),
            taskId = "task-66bdfeeeea",
            taskName = "Rubik.HostResourceDefragment",
            traceId = "e5b0f6b0-4c58-405d-ae0d-3ee9e13e0589",
            parameters = "{\"regionAlias\":\"cd\",\"zoneId\":160002.0,\"deviceClass\":\"VSELF_6\",\"hostTypes\":[\"T0-CS62X-100G\",\"T0-CS65X-100GS\",\"X0-CS65X-100GS\",\"X0-CS63X-100GS\",\"X0-CC64X-100GS\"],\"fragment\":true,\"memory\":65536.0,\"load\":30.0,\"cbsCount\":9.0,\"clbCount\":9.0,\"migrationRiskLevel\":0.0,\"strategy\":\"DEFAULT\",\"hostSoldRatio\":100.0,\"dryRun\":\"false\",\"jobId\":\"33\"}",
            expectedExecutionTime = LocalDateTime.parse("2024-08-15T21:13:45"),
            executionTime = LocalDateTime.parse("2024-08-16T02:08:19"),
            executionDuration = 511,
            executionResult = "{\n" +
                    "    \"aggCpuDict\": {\n" +
                    "        \"16\": 31.0,\n" +
                    "        \"32\": 2.0,\n" +
                    "        \"64\": 3.0,\n" +
                    "        \"128\": 4.0\n" +
                    "    },\n" +
                    "    \"zoneId\": 100001,\n" +
                    "    \"region\": \"sh\",\n" +
                    "    \"deviceClass\": \"VSELF_6\",\n" +
                    "    \"taskType\": \"host_resource_defragment\",\n" +
                    "    \"emptyHost\":[\"1\",\"2\",\"3\"]\n" +
                    "}",
            status = ExecutionStatusEnum.SUCCEED,
            errorDetail = null,
            serverIp = "kaioji",
            creator = "创建人"
        ),
        ExecutionEntity(
            id = 134890,
            createDate = LocalDateTime.parse("2024-08-15T21:10:48"),
            modifiedDate = LocalDateTime.parse("2024-08-16T02:08:18"),
            taskId = "task-66bdfeeeea",
            taskName = "Rubik.HostResourceDefragment",
            traceId = "e5b0f6b0-4c58-405d-ae0d-3ee9e13e0589",
            parameters = "{\"regionAlias\":\"cd\",\"zoneId\":160002.0,\"deviceClass\":\"VSELF_6\",\"hostTypes\":[\"T0-CS62X-100G\",\"T0-CS65X-100GS\",\"X0-CS65X-100GS\",\"X0-CS63X-100GS\",\"X0-CC64X-100GS\"],\"fragment\":true,\"memory\":65536.0,\"load\":30.0,\"cbsCount\":9.0,\"clbCount\":9.0,\"migrationRiskLevel\":0.0,\"strategy\":\"DEFAULT\",\"hostSoldRatio\":100.0,\"dryRun\":\"false\",\"jobId\":\"43\"}",
            expectedExecutionTime = LocalDateTime.parse("2024-08-15T21:13:45"),
            executionTime = LocalDateTime.parse("2024-08-16T02:08:19"),
            executionDuration = 511,
            executionResult = "{\n" +
                    "    \"aggCpuDict\": {\n" +
                    "        \"16\": 41.0,\n" +
                    "        \"32\": 2.0,\n" +
                    "        \"64\": 3.0,\n" +
                    "        \"128\": 4.0\n" +
                    "    },\n" +
                    "    \"zoneId\": 100001,\n" +
                    "    \"region\": \"sh\",\n" +
                    "    \"deviceClass\": \"VSELF_7\",\n" +
                    "    \"taskType\": \"host_resource_defragment\",\n" +
                    "    \"emptyHost\":[\"1\",\"2\",\"3\"]\n" +
                    "}",
            status = ExecutionStatusEnum.SUCCEED,
            errorDetail = null,
            serverIp = "kaioji",
            creator = "创建人"
        ),
        ExecutionEntity(
            id = 134890,
            createDate = LocalDateTime.parse("2024-08-15T21:13:45"),
            modifiedDate = LocalDateTime.parse("2024-08-16T02:08:18"),
            taskId = "task-66bdfeeeea",
            taskName = "Rubik.MixResource",
            traceId = "e5b0f6b0-4c58-405d-ae0d-3ee9e13e0589",
            parameters = "{\"regionAlias\":\"cd\",\"zoneId\":160002.0,\"deviceClass\":\"VSELF_6\",\"hostTypes\":[\"T0-CS62X-100G\",\"T0-CS65X-100GS\",\"X0-CS65X-100GS\",\"X0-CS63X-100GS\",\"X0-CC64X-100GS\"],\"fragment\":true,\"memory\":65536.0,\"load\":30.0,\"cbsCount\":9.0,\"clbCount\":9.0,\"migrationRiskLevel\":0.0,\"strategy\":\"DEFAULT\",\"hostSoldRatio\":100.0,\"dryRun\":\"false\",\"jobId\":\"49\"}",
            expectedExecutionTime = LocalDateTime.parse("2024-08-15T21:13:45"),
            executionTime = LocalDateTime.parse("2024-08-16T02:08:19"),
            executionDuration = 511,
            executionResult = "{\n" +
                    "    \"migrateInstanceCount\": 6,\n" +
                    "    \"migrateInstanceCpu\": 24,\n" +
                    "    \"taskType\": \"mix_resource\",\n" +
                    "    \"zoneId\": 100002,\n" +
                    "    \"status\": \"2\",\n" +
                    "    \"deviceClass\":\"VSELF_6\"\n" +
                    "}",
            status = ExecutionStatusEnum.SUCCEED,
            errorDetail = null,
            serverIp = "kaioji",
            creator = "创建人"
        ),
        ExecutionEntity(
            id = 134890,
            createDate = LocalDateTime.parse("2024-08-15T21:13:45"),
            modifiedDate = LocalDateTime.parse("2024-08-16T02:08:18"),
            taskId = "task-66bdfeeeea",
            taskName = "Rubik.SeparateResource",
            traceId = "e5b0f6b0-4c58-405d-ae0d-3ee9e13e0589",
            parameters = "{\"regionAlias\":\"cd\",\"zoneId\":160002.0,\"deviceClass\":\"VSELF_6\",\"hostTypes\":[\"T0-CS62X-100G\",\"T0-CS65X-100GS\",\"X0-CS65X-100GS\",\"X0-CS63X-100GS\",\"X0-CC64X-100GS\"],\"fragment\":true,\"memory\":65536.0,\"load\":30.0,\"cbsCount\":9.0,\"clbCount\":9.0,\"migrationRiskLevel\":0.0,\"strategy\":\"DEFAULT\",\"hostSoldRatio\":100.0,\"dryRun\":\"false\",\"jobId\":\"49\"}",
            expectedExecutionTime = LocalDateTime.parse("2024-08-15T21:13:45"),
            executionTime = LocalDateTime.parse("2024-08-16T02:08:19"),
            executionDuration = 511,
            executionResult = "{\n" +
                    "    \"migrateInstanceCount\": 6,\n" +
                    "    \"migrateInstanceCpu\": 24,\n" +
                    "    \"emptyHostCount\": 1,\n" +
                    "    \"emptyHosts\": [\n" +
                    "        \"1.1.1.1\"\n" +
                    "    ],\n" +
                    "    \"taskType\": \"simulate_separate_resource\",\n" +
                    "    \"zoneId\": 100002,\n" +
                    "    \"status\": \"2\",\n" +
                    "    \"deviceClass\": \"VSELF_6\"\n" +
                    "}",
            status = ExecutionStatusEnum.SUCCEED,
            errorDetail = null,
            serverIp = "kaioji",
            creator = "创建人"
        ),

        )

}