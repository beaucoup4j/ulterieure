/*
 * Copyright the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.beaucoup4j.ulterieure;

import java.util.Optional;

/**
 * Self-documenting unfinished code. This class is marked with the {@link Deprecated} annotation so that uses will be
 * flagged.
 */
@Deprecated
public class Ulterieure {

    /**
     * Describes action taken when a todo call is executed.
     */
    // @formatter:off
    public enum TodoPolicy {
        THROWING_EXCEPTION, RETURNING_NULL;
    }
    // @formatter:on

    private static final String DEFAULT_MESSAGE = "todo";

    /**
     * Basic todo.
     *
     * @return {@code null}
     */
    public static <T> T todo() {
        return todo(TodoPolicy.RETURNING_NULL);
    }

    /**
     * Todo with custom message (to {@link System#err}.
     *
     * @param message
     * @return {@code null}
     */
    public static <T> T todo(String message) {
        return todo(TodoPolicy.RETURNING_NULL, message);
    }

    /**
     * Todo with specified policy.
     *
     * @param policy
     * @return {@code null} if policy is {@link TodoPolicy#RETURNING_NULL}
     * @throws UnsupportedOperationException
     *             if policy is {@link TodoPolicy#THROWING_EXCEPTION}
     */
    public static <T> T todo(TodoPolicy policy) {
        return todo(policy, Optional.empty());
    }

    /**
     * Todo with specified policy and custom message.
     *
     * @param policy
     * @param message
     * @return {@code null} if policy is {@link TodoPolicy#RETURNING_NULL}
     * @throws UnsupportedOperationException
     *             if policy is {@link TodoPolicy#THROWING_EXCEPTION}
     */
    public static <T> T todo(TodoPolicy policy, String message) {
        return todo(policy, Optional.ofNullable(message));
    }

    /**
     * Create an {@link Ulterieure} instance (serves no purpose except to facilitate subclassing).
     */
    protected Ulterieure() {
    }

    private static <T> T todo(TodoPolicy policy, Optional<String> message) {
        if (policy == TodoPolicy.THROWING_EXCEPTION) {
            throw new UnsupportedOperationException(message.orElse(DEFAULT_MESSAGE));
        }
        message.ifPresent(System.err::println);
        return null;
    }
}
