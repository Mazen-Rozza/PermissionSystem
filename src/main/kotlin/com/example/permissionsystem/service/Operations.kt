package com.example.permissionsystem.service

import com.example.permissionsystem.dto.RoleOperationResult
import com.example.permissionsystem.exceptions.AppExceptions
import com.example.permissionsystem.models.User
import com.example.permissionsystem.models.userRole
import org.springframework.stereotype.Service

@Service
class Operations {

    val users = mapOf(
        1 to User(1, 0L),
        2 to User(2, getRoleToUser("ADMIN")),
        3 to User(3, getRoleToUser("ADMIN") or getRoleToUser("MODERATOR")),
        4 to User(4, getRoleToUser("GUEST") or getRoleToUser("SUPPORT") or getRoleToUser("MANAGER")),
        5 to User(5, getRoleToUser("SUPER_ADMIN"))
    )

    fun addRole(userId: Int, roleName: String): RoleOperationResult {
        val matchedUser = validateUserExists(userId)

        val newAssignedRoles = getRoleToUser(roleName) or matchedUser.assignedRole
        users[matchedUser.useId]?.assignedRole = newAssignedRoles

        val stringRolesList = userRole.filter { it.value and newAssignedRoles != 0L }.map { it.key }
        return RoleOperationResult(stringRolesList)
    }

    fun deleteRole(userId: Int, roleName: String): RoleOperationResult {
        val matchedUser = validateUserExists(userId)
        validateRoleExists(roleName)

        val newAssignedRoles = getRoleToUser(roleName).inv() and matchedUser.assignedRole
        users[matchedUser.useId]?.assignedRole = newAssignedRoles

        val stringRolesList = userRole.filter { it.value and newAssignedRoles != 0L }.map { it.key }
        return RoleOperationResult(stringRolesList)
    }

    fun getUserRoles(userId: Int): RoleOperationResult {
        val matchedUser = validateUserExists(userId)

        val rolesList = userRole.filter { it.value and matchedUser.assignedRole != 0L }.map { it.key }
        return RoleOperationResult(rolesList)
    }

    fun checkUserRole(userId: Int, roleName: String): Boolean {
        val matchedUser = validateUserExists(userId)
        val matchedRole = validateRoleExists(roleName)

        val hasRole = userRole.filter { (it.value and matchedUser.assignedRole) != 0L }.map { it.value }
        return matchedRole in hasRole
    }

    fun getRoleToUser(roleName: String): Long {
        validateRoleExists(roleName)
        return userRole[roleName]!!
    }

    fun validateUserExists(userId: Int): User {
        return users[userId] ?: throw AppExceptions.UserNotFoundException(userId)
    }

    fun validateRoleExists(roleName: String): Long {
        return userRole[roleName.uppercase()] ?: throw AppExceptions.RoleNotFoundException(roleName)
    }
}
