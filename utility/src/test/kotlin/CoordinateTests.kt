/*
 * Copyright (c) 2022 xf8b.
 *
 * This file is part of forrest-game-experimental.
 *
 * forrest-game-experimental is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * forrest-game-experimental is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with forrest-game-experimental. If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.xf8b.fge.utility

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CoordinateTests {
    @ParameterizedTest(name = "({1}, {2}) moved left {0} units is ({3}, {4})")
    @CsvSource(
        "1,  25, 0,  24, 0",
        "25,  0, 25,  -25, 25",
        "5,  -5, 0,  -10, 0",
        "1000,  2000, 0,  1000, 0",
    )
    fun left(amount: Int, initialX: Int, initialY: Int, expectedX: Int, expectedY: Int) {
        val initialCoordinate = Coordinate(initialX, initialY)
        val expectedCoordinate = Coordinate(expectedX, expectedY)

        assertEquals(expectedCoordinate.x, initialCoordinate.left(amount).x) {
            "$initialCoordinate moved left $amount units should have been $expectedCoordinate"
        }
    }

    @ParameterizedTest(name = "({1}, {2}) moved right {0} units is ({3}, {4})")
    @CsvSource(
        "1,  25, 0,  26, 0",
        "25,  -25, 25,  0, 25",
        "5,  -5, 0,  0, 0",
        "1000,  0, 0,  1000, 0",
    )
    fun right(amount: Int, initialX: Int, initialY: Int, expectedX: Int, expectedY: Int) {
        val initialCoordinate = Coordinate(initialX, initialY)
        val expectedCoordinate = Coordinate(expectedX, expectedY)

        assertEquals(expectedCoordinate.x, initialCoordinate.right(amount).x) {
            "$initialCoordinate moved right $amount units should have been $expectedCoordinate"
        }
    }

    @ParameterizedTest(name = "({1}, {2}) moved down {0} units is ({3}, {4})")
    @CsvSource(
        "1,  25, 1,  25, 0",
        "25,  25, 25,  25, 0",
        "5,  0, 5,  0, 0",
        "1000,  0, 0,  0, -1000",
    )
    fun down(amount: Int, initialX: Int, initialY: Int, expectedX: Int, expectedY: Int) {
        val initialCoordinate = Coordinate(initialX, initialY)
        val expectedCoordinate = Coordinate(expectedX, expectedY)

        assertEquals(expectedCoordinate.y, initialCoordinate.down(amount).y) {
            "$initialCoordinate moved down $amount units should have been $expectedCoordinate"
        }
    }

    @ParameterizedTest(name = "({1}, {2}) moved up {0} units is ({3}, {4})")
    @CsvSource(
        "1,  25, 1,  25, 2",
        "25,  25, 25,  25, 50",
        "5,  0, -5,  0, 0",
        "1000,  0, 0,  0, 1000",
    )
    fun up(amount: Int, initialX: Int, initialY: Int, expectedX: Int, expectedY: Int) {
        val initialCoordinate = Coordinate(initialX, initialY)
        val expectedCoordinate = Coordinate(expectedX, expectedY)

        assertEquals(expectedCoordinate.y, initialCoordinate.up(amount).y) {
            "$initialCoordinate moved up $amount units should have been $expectedCoordinate"
        }
    }

    @Test
    fun `cannot move by a negative amount or zero`() {
        val testCoordinate = Coordinate(-5, 5)

        assertThrows<IllegalArgumentException> { testCoordinate.left(-5) }
        assertThrows<IllegalArgumentException> { testCoordinate.left(0) }
        assertThrows<IllegalArgumentException> { testCoordinate.right(-5) }
        assertThrows<IllegalArgumentException> { testCoordinate.right(0) }
        assertThrows<IllegalArgumentException> { testCoordinate.down(-5) }
        assertThrows<IllegalArgumentException> { testCoordinate.down(0) }
        assertThrows<IllegalArgumentException> { testCoordinate.up(-5) }
        assertThrows<IllegalArgumentException> { testCoordinate.up(0) }
    }
}