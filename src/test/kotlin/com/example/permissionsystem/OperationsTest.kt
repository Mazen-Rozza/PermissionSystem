package com.example.permissionsystem

import com.example.permissionsystem.exceptions.AppExceptions
import com.example.permissionsystem.service.Operations
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class OperationsTest {
    private lateinit var operationsTest: Operations

    @BeforeEach
    fun setUp() {
        operationsTest = Operations()
    }

    @Test
    fun `should throw exception when user id is invalid`() {
        assertThrows<AppExceptions.UserNotFoundException> {
            operationsTest.validateUserExists(7)
        }
    }

    @Test
    fun `should throw exception when role name is invalid`() {
        assertThrows<AppExceptions.RoleNotFoundException> {
            operationsTest.validateRoleExists("Pizza")
        }
    }

    @Test
    fun `should return roles list upon successful role addition`() {
        val userId = 3
        val roleName = "GUEST"

        val addRoleResult = operationsTest.addRole(userId, roleName)
        assertEquals(listOf("ADMIN", "GUEST", "MODERATOR"), addRoleResult.roles)
    }

    @Test
    fun `should return roles list upon successfully deleting a user role`() {
        val userId = 4
        val roleName = "SUPPORT"

        val deleteRoleResult = operationsTest.deleteRole(userId, roleName)
        assertEquals(listOf("GUEST", "MANAGER"), deleteRoleResult.roles)
    }

    @Test
    fun `should return list of role objects upon successfully fetching user roles`() {
        val checkUserRoles = operationsTest.getUserRoles(5)
        assertEquals(listOf("SUPER_ADMIN"), checkUserRoles.roles)
    }

    @Test
    fun `should return true when inquiring about an existing user role`() {
        val userId = 2
        val roleName = "ADMIN"

        val userHasRole = operationsTest.checkUserRole(userId, roleName)
        assertEquals(true, userHasRole)
    }

    @Test
    fun `should return false when inquiring about user role that doesn't exist`() {
        val userId = 1
        val roleName = "ADMIN"

        val userHasRole = operationsTest.checkUserRole(userId, roleName)
        assertEquals(false, userHasRole)
    }
}
