/*
 *  Copyright 2017 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 *  or implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 */

package example.chat.client.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.cp.elements.lang.Identifiable;
import org.cp.elements.util.ComparatorResultBuilder;
import org.springframework.data.gemfire.mapping.annotation.Region;

import example.chat.model.Person;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * The {@link Chat} class is a Abstract Data Type (ADT) modeling a chat in a Chat application.
 *
 * @author John Blum
 * @see java.lang.Comparable
 * @see java.time.LocalDateTime
 * @see org.cp.elements.lang.Identifiable
 * @see org.springframework.data.gemfire.mapping.annotation.Region
 * @see example.chat.model.Person
 * @since 1.0.0
 */
@Data
@Region("Chats")
@RequiredArgsConstructor(staticName = "newChat")
@SuppressWarnings("unused")
public class Chat implements Comparable<Chat>, Identifiable<String>, Serializable {

	private String id;

	private LocalDateTime timestamp = LocalDateTime.now();

	private Object processId;

	@NonNull
	private Person person;

	@NonNull
	private String message;

	@Override
	@SuppressWarnings("unchecked")
	public int compareTo(Chat that) {

		return ComparatorResultBuilder.<Comparable>create()
			.doCompare(this.getTimestamp(), that.getTimestamp())
			.build();
	}

	public Chat withProcessId(Object processId) {
		setProcessId(processId);
		return this;
	}

	@Override
	public String toString() {
		return String.format("[%1$s] %2$s@%3$s: %4$s",
			getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")),
				getPerson(), getProcessId(), getMessage());
	}
}
