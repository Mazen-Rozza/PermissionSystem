package com.example.permissionsystem

import com.example.permissionsystem.service.Operations
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PermissionSystemApplication

fun main(args: Array<String>) {
    runApplication<PermissionSystemApplication>(*args)
}



//fun main() {
//    val operationInstance = Operations()
//
//    println("\naddRole operation test")
//
//    try {
//        operationInstance.addRole(7, "admin")
//    } catch (e: IllegalArgumentException) {
//        println("Error occurred : ${e.message}")
//    }
//
//    try {
//        operationInstance.addRole(3, "pizza")
//    } catch (e: IllegalArgumentException) {
//        println("Error occurred : ${e.message}")
//    }
//
//    val duplicatedRole = operationInstance.addRole(2, "adMin")
//    println("${duplicatedRole.msg}, Roles: ${duplicatedRole.roles}")
//
//    val addRoleResult = operationInstance.addRole(3, "guest")
//    println("${addRoleResult.msg}, Roles: ${addRoleResult.roles}")
//
//    println("\ndeleteRole operation test")
//
//    try {
//        operationInstance.deleteRole(7, "admin")
//    } catch (e: IllegalArgumentException) {
//        println("Error occurred : ${e.message}")
//    }
//
//    try {
//        operationInstance.deleteRole(3, "pizza")
//    } catch (e: IllegalArgumentException) {
//        println("Error occurred : ${e.message}")
//    }
//
//    val deleteRoleOperation = operationInstance.deleteRole(4, "guest")
//    println("${deleteRoleOperation.msg}, Roles: ${deleteRoleOperation.roles}")
//
//    val userNoRole = operationInstance.deleteRole(3, "support")
//    println("${userNoRole.msg}, Roles: ${userNoRole.roles}")
//
//    println("\ngetUSerRoles operation test")
//
//    try {
//        operationInstance.getUserRoles(7)
//    } catch (e: IllegalArgumentException) {
//        println("Error occurred : ${e.message}")
//    }
//
//    val getUserRoles = operationInstance.getUserRoles(5)
//    println("${getUserRoles.msg}, Roles: ${getUserRoles.roles}")
//
//    println("\ncheckUserRole operation test")
//
//    try {
//        operationInstance.checkUserRole(7, "admin")
//    } catch (e: IllegalArgumentException) {
//        println("Error occurred : ${e.message}")
//    }
//
//    try {
//        operationInstance.checkUserRole(2, "pizza")
//    } catch (e: IllegalArgumentException) {
//        println("Error occurred : ${e.message}")
//    }
//
//    val hasRole = operationInstance.checkUserRole(2, "admin")
//    println("User 2 has ADMIN role: $hasRole")
//
//    val doesntHaveRole = operationInstance.checkUserRole(2, "guest")
//    println("User 2 has GUEST role: $doesntHaveRole")
//}
