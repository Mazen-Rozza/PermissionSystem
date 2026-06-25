package com.example.permissionsystem.service

import com.example.permissionsystem.dto.RoleOperationResult
import com.example.permissionsystem.exceptions.AppExceptions
import com.example.permissionsystem.models.User
import com.example.permissionsystem.models.UserRole
import org.springframework.stereotype.Service

@Service
class Operations {

    val users = mapOf(
        1 to User(1, 0L),
        2 to User(2, UserRole.ADMIN.rolesMask),
        3 to User(3, UserRole.ADMIN.rolesMask or UserRole.MODERATOR.rolesMask),
        4 to User(4, UserRole.GUEST.rolesMask or UserRole.SUPPORT.rolesMask or UserRole.MANAGER.rolesMask),
        5 to User(5, UserRole.SUPER_ADMIN.rolesMask)
    )

    fun addRole(userId: Int, roleName: String): RoleOperationResult {
        val matchedUser = validateUserExists(userId)
        val matchedRole = validateRoleExists(roleName)

        val userRolesList = matchedRole.rolesMask or matchedUser.assignedRole
        val stringRolesList = UserRole.entries.filter { it.rolesMask and userRolesList != 0L }. map { it.name }

        return RoleOperationResult(stringRolesList)


//        if (matchedRole in rolesList) {
//            val stringRolesList: List<String> = rolesList.map { it.name }
//            return RoleOperationResult("User with id $userId already has the role ${matchedRole.name}", stringRolesList)
//        } else {
//            val newMask = matchedUser.assignedRole or matchedRole.rolesMask
//            val roleUpdate = updateUserRole(matchedUser, newMask)
//
//            return RoleOperationResult(
//                "Role $matchedRole was added to the user with id $userId successfully",
//                roleUpdate
//            )
//        }
    }

    fun deleteRole(userId: Int, roleName: String): RoleOperationResult {
        val matchedUser = validateUserExists(userId)
        val matchedRole = validateRoleExists(roleName)

        val userRolesList = matchedRole.rolesMask or matchedUser.assignedRole
        val stringRolesList = UserRole.entries.filter { it.rolesMask and userRolesList != 0L }. map { it.name }
        return RoleOperationResult(stringRolesList)

//        if (matchedRole in rolesList) {
//            val deleteMask = matchedRole.rolesMask.inv()
//            val newMask = deleteMask and matchedUser.assignedRole
//            val roleUpdate = updateUserRole(matchedUser, newMask)
//
//            return RoleOperationResult(
//                "Role $matchedRole was deleted successfully from the user with id $userId",
//                roleUpdate
//            )
//        } else {
//            val stringRolesList: List<String> = rolesList.map { it.name }
//            return RoleOperationResult(
//                "User with id $userId doesn't have the role ${matchedRole.name}",
//                stringRolesList
//            )
//        }
    }

    fun getUserRoles(userId: Int): RoleOperationResult {
        val matchedUser = validateUserExists(userId)

        val rolesList = UserRole.entries.filter { (it.rolesMask and matchedUser.assignedRole) != 0L }.map { it.name }

        return RoleOperationResult (rolesList)
    }

    fun checkUserRole(userId: Int, roleName: String): Boolean {
        val matchedUser = validateUserExists(userId)
        val matchedRole = validateRoleExists(roleName)

        val hasRole = UserRole.entries.filter { (it.rolesMask and matchedUser.assignedRole) != 0L }
        return matchedRole in hasRole
    }

    fun updateUserRole(matchedUser: User, newMask: Long): List<String> {
        val finalRolesList = UserRole.entries.filter { (it.rolesMask and newMask) != 0L }
        val stringFinalRolesList: List<String> = finalRolesList.map { it.name }
        users[matchedUser.useId]?.assignedRole = newMask

        return stringFinalRolesList
    }

    fun validateUserExists(userId: Int): User {
        return users[userId] ?: throw AppExceptions.UserNotFoundException(userId)
    }

    fun validateRoleExists(roleName: String): UserRole {
        val matchedRole = UserRole.entries.find { it.name == roleName.uppercase() }
        return matchedRole ?: throw AppExceptions.RoleNotFoundException(roleName)
    }
}
