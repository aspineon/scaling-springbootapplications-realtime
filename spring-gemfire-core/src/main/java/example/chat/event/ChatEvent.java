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

package example.chat.event;

import static org.cp.elements.lang.RuntimeExceptionsFactory.newIllegalArgumentException;
import static org.cp.elements.lang.RuntimeExceptionsFactory.newIllegalStateException;

import java.util.EventObject;
import java.util.Optional;

/**
 * The {@link ChatEvent} class is an {@link EventObject} encapsulating the details of a chat event.
 *
 * @author John Blum
 * @see java.util.EventObject
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class ChatEvent<T> extends EventObject {

	private T chat;

	public static ChatEvent newChatEvent(Object source) {
		return new ChatEvent(source);
	}

	protected ChatEvent(Object source) {
		super(source);
	}

	public Optional<T> getChat() {
		return Optional.ofNullable(this.chat);
	}

	public T requireChat() {
		return getChat().orElseThrow(() -> newIllegalStateException("Chat was not set"));
	}

	public ChatEvent<T> with(T chat) {

		this.chat = Optional.ofNullable(chat).
			orElseThrow(() -> newIllegalArgumentException("Chat [%s] is required", chat));

		return this;
	}
}
