package com.example.permissionsystem

import com.example.permissionsystem.service.Operations
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PermissionSystemApplication

//fun main(args: Array<String>) {
//    runApplication<PermissionSystemApplication>(*args)
//}



fun main() {
    val operationInstance = Operations()

    try {
        operationInstance.addRole(7, "admin")
    } catch (e: IllegalArgumentException) {
        println("Error occurred : ${e.message}")
    }

    try {
        operationInstance.addRole(3, "pizza")
    } catch (e: IllegalArgumentException) {
        println("Error occurred : ${e.message}")
    }

    try {
        operationInstance.addRole(2, "adMin")
    } catch (e: IllegalArgumentException) {
        println("Error occurred : ${e.message}")
    }

    try {
        operationInstance.addRole(3, "guest")
    } catch (e: IllegalArgumentException) {
        println("Error occurred : ${e.message}")
    }
}
