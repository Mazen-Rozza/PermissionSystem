package com.example.permissionsystem

import com.example.permissionsystem.dto.RoleOperationResult
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

    println("addRole operation test")

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

    val duplicatedRole = operationInstance.addRole(2, "adMin")
    println("${duplicatedRole.msg}, Roles: ${duplicatedRole.roles}")

    val addRoleResult = operationInstance.addRole(3, "guest")
    println("${addRoleResult.msg}, Roles: ${addRoleResult.roles}")

    println("\n")

    println("deleteRole operation test")

    try {
        operationInstance.deleteRole(7, "admin")
    } catch (e: IllegalArgumentException) {
        println("Error occurred : ${e.message}")
    }

    try {
        operationInstance.deleteRole(3, "pizza")
    } catch (e: IllegalArgumentException) {
        println("Error occurred : ${e.message}")
    }

    val deleteRoleOperation = operationInstance.deleteRole(4, "guest")
    println("${deleteRoleOperation.msg}, Roles: ${deleteRoleOperation.roles}")

    val userNoRole = operationInstance.deleteRole(3, "support")
    println("${userNoRole.msg}, Roles: ${userNoRole.roles}")
}
