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
//        4 to User(4, UserRole.GUEST.rolesMask or UserRole.SUPPORT.rolesMask or UserRole.MANAGER.rolesMask),
//        5 to User(5, UserRole.SUPER_ADMIN.rolesMask)
    )

    fun addRole(userId: Int, roleName: String): RoleOperationResult {
        val matchedUser = validateUserExists(userId)
        validateRoleExists(roleName)

        val newAssignedRoles = getRoleToUser(roleName) or matchedUser.assignedRole
        users[matchedUser.useId]?.assignedRole = newAssignedRoles

        val stringRolesList = userRole.filter { it.value and newAssignedRoles != 0L }.map { it.key }

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

//    fun deleteRole(userId: Int, roleName: String): RoleOperationResult {
//        val matchedUser = validateUserExists(userId)
//        val matchedRole = validateRoleExists(roleName)
//
//        val newAssignedRoles = matchedRole.rolesMask.inv() and matchedUser.assignedRole
//        users[matchedUser.useId]?.assignedRole = newAssignedRoles
//
//        val stringRolesList = UserRole.entries.filter { it.rolesMask and newAssignedRoles != 0L }.map { it.name }
//        return RoleOperationResult(stringRolesList)
//
////        if (matchedRole in rolesList) {
////            val deleteMask = matchedRole.rolesMask.inv()
////            val newMask = deleteMask and matchedUser.assignedRole
////            val roleUpdate = updateUserRole(matchedUser, newMask)
////
////            return RoleOperationResult(
////                "Role $matchedRole was deleted successfully from the user with id $userId",
////                roleUpdate
////            )
////        } else {
////            val stringRolesList: List<String> = rolesList.map { it.name }
////            return RoleOperationResult(
////                "User with id $userId doesn't have the role ${matchedRole.name}",
////                stringRolesList
////            )
////        }
//    }

//    fun getUserRoles(userId: Int): RoleOperationResult {
//        val matchedUser = validateUserExists(userId)
//
//        val rolesList = UserRole.entries.filter { (it.rolesMask and matchedUser.assignedRole) != 0L }.map { it.name }
//        users[matchedUser.useId]?.assignedRole = userRolesList
//
//        return RoleOperationResult(rolesList)
//    }
//
//    fun checkUserRole(userId: Int, roleName: String): Boolean {
//        val matchedUser = validateUserExists(userId)
//        val matchedRole = validateRoleExists(roleName)
//
//        val hasRole = UserRole.entries.filter { (it.rolesMask and matchedUser.assignedRole) != 0L }
//        return matchedRole in hasRole
//    }

//    fun updateUserRole(matchedUser: User, newMask: Long): List<String> {
//        val finalRolesList = UserRole.entries.filter { (it.rolesMask and newMask) != 0L }
//        val stringFinalRolesList: List<String> = finalRolesList.map { it.name }
//        users[matchedUser.useId]?.assignedRole = newMask
//
//        return stringFinalRolesList
//    }

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
