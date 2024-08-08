package com.example.demo.common.util

import java.io.File

// 文件处理
object FileHandler {
    private var counter: Int = 0
    fun upload(filename: String?, content: File) {
        //  函数参数是不可修改，val
        var fileName: String? = filename
        if (filename.isNullOrBlank()) {
            fileName = generateName()
        }
    }

    fun download() {

    }

    fun generateName(): String {
        return "file-${counter++}"
    }
}