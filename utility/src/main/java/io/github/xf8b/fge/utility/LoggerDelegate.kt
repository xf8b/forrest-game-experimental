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

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KProperty

/** Acts as a delegate for getting [Logger]s. Looks better. */
class LoggerDelegate<in T : Any> {
    private lateinit var logger: Logger

    /** Provides a [Logger] based on [thisReference]'s class name. Companion objects use their parent's name. */
    operator fun getValue(thisReference: T, property: KProperty<*>): Logger {
        if (!::logger.isInitialized) {
            // if the logger hasn't been set, set it
            logger = LoggerFactory.getLogger(
                // if thisReference is a companion object, use the enclosing class instead
                if (thisReference::class.isCompanion) thisReference.javaClass.enclosingClass
                // else just use thisReference
                else thisReference.javaClass
            )
        }

        return logger
    }
}