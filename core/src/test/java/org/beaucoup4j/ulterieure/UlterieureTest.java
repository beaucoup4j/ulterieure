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

import static org.beaucoup4j.ulterieure.Ulterieure.todo;
import static org.beaucoup4j.ulterieure.Ulterieure.TodoPolicy.THROWING_EXCEPTION;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.Serializable;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class UlterieureTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private PrintStream errStream;

	@Before
	public void setup() {
		System.setErr(errStream);
	}

	@Test
	public void testString() {
		assertNull(todoString());
	}

	@Test
	public void testVoid() {
		todoVoid();
	}

	@Test
	public void testStream() {
		assertNull(todoStream());
	}

	@Test
	public void testVoidThrowingException() {
		thrown.expect(UnsupportedOperationException.class);
		thrown.expectMessage("todo");
		todoVoidThrowingException();
	}

	@Test
	public void testIntegerThrowingException() {
		thrown.expect(UnsupportedOperationException.class);
		thrown.expectMessage("todo");
		todoIntegerThrowingException();
	}

	@Test
	public void testCustomMessage() {
		assertNull(todoMessage());
		Mockito.verify(errStream).println("implement #todoMessage()");
	}

	@Test
	public void testThrowingExceptionWithCustomMessage() {
		thrown.expect(UnsupportedOperationException.class);
		thrown.expectMessage("serializable outstanding");
		todoThrowingExceptionWithCustomMessage();
	}

	public String todoString() {
		return todo();
	}

	public void todoVoid() {
		todo();
	}

	public InputStream todoStream() {
		return todo();
	}

	public Object todoMessage() {
		return todo("implement #todoMessage()");
	}

	public void todoVoidThrowingException() {
		todo(THROWING_EXCEPTION);
	}

	public Integer todoIntegerThrowingException() {
		return todo(THROWING_EXCEPTION);
	}

	public Serializable todoThrowingExceptionWithCustomMessage() {
		return todo(THROWING_EXCEPTION, "serializable outstanding");
	}
}
