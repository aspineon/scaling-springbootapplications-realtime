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

package example.chat.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

import example.chat.bot.config.EnableChatBot;
import example.chat.client.service.SimpleChatService;

/**
 * The ChatClientApplication class...
 *
 * @author John Blum
 * @since 1.0.0
 */
@SpringBootApplication
@EnableChatBot
@ImportResource("chat-client-context.xml")
@SuppressWarnings("unused")
public class ChatClientApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(ChatClientApplication.class)
			.web(WebApplicationType.NONE)
			.build()
			.run(args);
	}

	@Autowired
	private SimpleChatService chatService;

	@PostConstruct
	public void postConstruct() {
		this.chatService.register(chatEvent -> chatEvent.getChat().ifPresent(System.err::println));
	}
}
