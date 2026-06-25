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
    fun `should include message, ID and roles list upon successful role addition`() {
        val userId = 3
        val roleName = "GUEST"

        val addRoleResult = operationsTest.addRole(userId, roleName)

        assertEquals("Role GUEST was added to the user with id 3 successfully", addRoleResult.msg)
        assertEquals(listOf("ADMIN", "GUEST", "MODERATOR"), addRoleResult.roles)
    }

    @Test
    fun `should include message, ID and typed roles list when deleting user role`() {
        val userId = 4
        val roleName = "SUPPORT"

        val deleteRoleResult = operationsTest.deleteRole(userId, roleName)

        assertEquals("Role SUPPORT was deleted successfully from the user with id 4", deleteRoleResult.msg)
        assertEquals(listOf("GUEST", "MANAGER"), deleteRoleResult.roles)
    }

    @Test
    fun `should return operation success with list of role objects when user roles are fetched`() {
        val checkUserRoles = operationsTest.getUserRoles(5)

        assertEquals("Roles retrieved successfully for user with id 5", checkUserRoles.msg)
        assertEquals(listOf("SUPER_ADMIN"), checkUserRoles.roles)
    }

    @Test
    fun `should return true when inquiring about existing user role`() {
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
