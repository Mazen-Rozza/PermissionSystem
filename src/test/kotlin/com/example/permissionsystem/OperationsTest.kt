package com.example.permissionsystem

import com.example.permissionsystem.service.Operations
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
        assertThrows<IllegalArgumentException> {
            operationsTest.validateUserExists(7)
        }
    }

    @Test
    fun `should throw exception when role name is invalid`() {
        assertThrows<IllegalArgumentException> {
            operationsTest.validateRoleExists("Pizza")
        }
    }
}
