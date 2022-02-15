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

@file:JvmName("Main")

package io.github.xf8b.fge.libgdx

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

fun main() {
    val configuration = LwjglApplicationConfiguration().apply {
        title = "forrest-game-experimental"
        height = 710
        width = 710
    }
}