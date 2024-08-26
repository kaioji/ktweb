package com.example.demo.common.util

/**
 * 任务状态
 *
 * @author liangjb@tencent.com
 * @date 2022-03-08
 */
enum class ExecutionStatusEnum {
    /**
     * 待调度执行
     */
    WAITING,

    /**
     * 执行中
     */
    PENDING,

    /**
     * 忽略
     */
    SKIP,

    /**
     * 成功
     */
    SUCCEED,

    /**
     * 失败
     */
    FAILED,

    /**
     * 部分成功
     */
    PARTIAL_SUCCEED
}