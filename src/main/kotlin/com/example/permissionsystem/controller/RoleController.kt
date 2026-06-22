package com.example.permissionsystem.controller

import com.example.permissionsystem.dto.RoleOperationResult
import com.example.permissionsystem.service.Operations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class RoleController(private val operation: Operations) {

    @PatchMapping("/{userId}/roles/{roleName}")
    fun addRoleToUser(@PathVariable userId: Int, @PathVariable roleName: String): ResponseEntity<RoleOperationResult> {
        val addRoleResult = operation.addRole(userId, roleName)
        return ResponseEntity(addRoleResult, HttpStatus.OK)
    }

    @DeleteMapping("/{userId}/roles/{roleName}")
    fun removeRoleFromUser(@PathVariable userId: Int, @PathVariable roleName: String): ResponseEntity<RoleOperationResult> {
        val deleteRoleResult = operation.deleteRole(userId, roleName)
        return ResponseEntity(deleteRoleResult, HttpStatus.OK)
    }

    @GetMapping("/{userId}/roles")
    fun getRolesByUser(@PathVariable userId: Int): ResponseEntity<RoleOperationResult> {
        val getUserRolesResult = operation.getUserRoles(userId)
        return ResponseEntity(getUserRolesResult, HttpStatus.OK)
    }

    @GetMapping("/{userId}/roles/{roleName}")
    fun checkRolesByUser(@PathVariable userId: Int, @PathVariable roleName: String): ResponseEntity<Boolean> {
        val checkRoleResult = operation.checkUserRole(userId, roleName)
        return ResponseEntity(checkRoleResult, HttpStatus.OK)
    }
}