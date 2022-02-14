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

/** An immutable (x, y) coordinate pair. */
data class Coordinate(val x: Int, val y: Int) {
    private fun validateAmount(amount: Int) =
        if (amount <= 0) throw IllegalArgumentException("The amount must be greater than 0!")
        else amount

    private fun horizontal(amount: Int) = copy(
        x = this.x + amount,
        y = this.y
    )

    private fun vertical(amount: Int) = copy(
        x = this.x,
        y = this.y + amount
    )

    /** Returns a new [Coordinate] with the [x] moved left by [amount]. */
    fun left(amount: Int = 1) = horizontal(-validateAmount(amount))

    /** Returns a new [Coordinate] with the [x] moved right by [amount]. */
    fun right(amount: Int = 1) = horizontal(+validateAmount(amount))

    /** Returns a new [Coordinate] with the [y] moved down by [amount]. */
    fun down(amount: Int = 1) = vertical(-validateAmount(amount))

    /** Returns a new [Coordinate] with the [y] moved up by [amount]. */
    fun up(amount: Int = 1) = vertical(+validateAmount(amount))
}