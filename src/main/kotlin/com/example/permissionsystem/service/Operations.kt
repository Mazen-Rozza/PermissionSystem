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

        val matchedRole = UserRole.entries.find {
            it.name == roleName.uppercase()
        }
        matchedRole ?: throw IllegalArgumentException("Role $roleName not found")

        val rolesList = UserRole.entries.filter { (it.rolesMask and matchedUser.assignedRole) != 0L }

        if (matchedRole in rolesList) {
            val stringRolesList : List<String> = rolesList.map { it.name }
            return RoleOperationResult("User with id $userId already has the role ${matchedRole.name}", stringRolesList)
        } else {
            val newMask = matchedUser.assignedRole or matchedRole.rolesMask
            val finalRolesList = UserRole.entries.filter { (it.rolesMask and newMask) != 0L }
            val stringFinalRolesList : List<String> = finalRolesList.map { it.name }

            val newUserRoles = matchedUser.copy(assignedRole = newMask)
            users[userId] = newUserRoles

            return RoleOperationResult("Role $matchedRole was added to the user with $userId successfully", stringFinalRolesList)
        }
    }
}
