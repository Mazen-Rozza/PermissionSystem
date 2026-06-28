package com.example.permissionsystem.exceptions

sealed class AppExceptions(msg: String) : RuntimeException(msg) {

    class UserNotFoundException(userId: Int): AppExceptions("User with id $userId not found")

    class RoleNotFoundException(roleName: String): AppExceptions("Role $roleName not found")
}