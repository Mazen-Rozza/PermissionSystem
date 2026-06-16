package com.example.permissionsystem.service

import com.example.permissionsystem.dto.RoleOperationResult
import com.example.permissionsystem.models.User
import com.example.permissionsystem.models.UserRole
import org.springframework.stereotype.Service

@Service
class Operations {

    val users = mutableMapOf<Int, User>()

    fun addRole(userId: Int, roleName: String): RoleOperationResult {

        val matchedUser = users[userId] ?: throw IllegalArgumentException("User with id $userId not found")

        val matchedRole = UserRole.entries.find { it.name == roleName.uppercase() }
        matchedRole ?: throw IllegalArgumentException("Role $roleName not found")

        val rolesList = UserRole.entries.filter { (it.rolesMask and matchedUser.assignedRole) != 0L }

    }
}
