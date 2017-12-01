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

package example.chat.client.service;

import org.apache.geode.cache.query.CqEvent;
import org.cp.elements.lang.IdentifierSequence;
import org.cp.elements.lang.support.UUIDIdentifierSequence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.gemfire.listener.annotation.ContinuousQuery;
import org.springframework.stereotype.Service;

import example.chat.client.model.Chat;
import example.chat.client.repo.ChatRepository;
import example.chat.event.ChatEvent;
import example.chat.event.ChatEventPublisher;
import example.chat.model.Person;
import example.chat.service.ChatService;

/**
 * The {@link SimpleChatService} class is a Spring {@link Service} class implementing a chat service to send chats.
 *
 * @author John Blum
 * @see org.apache.geode.cache.query.CqEvent
 * @see org.cp.elements.lang.IdentifierSequence
 * @see org.springframework.data.gemfire.listener.annotation.ContinuousQuery
 * @see org.springframework.stereotype.Service
 * @see example.chat.client.model.Chat
 * @see example.chat.client.repo.ChatRepository
 * @see example.chat.event.ChatEvent
 * @see example.chat.event.ChatEventPublisher
 * @see example.chat.service.ChatService
 * @since 1.0.0
 */
@Service
@SuppressWarnings("unused")
public class SimpleChatService extends ChatEventPublisher implements ChatService {

	private final ChatRepository chatRepository;

	private IdentifierSequence<String> identifierSequence = new UUIDIdentifierSequence();

	@Value("${example.chat.client.processId:ChatClient}")
	private Object processId;

	public SimpleChatService(ChatRepository chatRepository) {
		this.chatRepository = chatRepository;
	}

	@Override
	public void send(Person person, String message) {
		send(Chat.newChat(person, message).withProcessId(this.processId));
	}

	public Chat send(Chat chat) {
		return this.chatRepository.save(chat.identifiedBy(this.identifierSequence.nextId()));
	}

	@ContinuousQuery(name = "ChatReceiver", query = "SELECT * FROM /Chats")
	@SuppressWarnings("unchecked")
	public void receive(CqEvent cqEvent) {
		fire(ChatEvent.newChatEvent(this).with(cqEvent.getNewValue()));
	}
}
