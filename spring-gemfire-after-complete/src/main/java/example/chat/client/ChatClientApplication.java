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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.data.gemfire.config.annotation.EnableContinuousQueries;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import example.chat.bot.config.EnableChatBot;
import example.chat.client.model.Chat;
import example.chat.client.repo.ChatRepository;
import example.chat.client.service.SimpleChatService;

/**
 * The {@link ChatClientApplication} class is a {@link SpringBootApplication} implementing a {@link Chat} application.
 *
 * @author John Blum
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.data.gemfire.config.annotation.ClientCacheApplication
 * @see org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration
 * @see org.springframework.data.gemfire.config.annotation.EnableContinuousQueries
 * @see org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions
 * @see org.springframework.data.gemfire.config.annotation.EnablePdx
 * @see org.springframework.data.gemfire.repository.config.EnableGemfireRepositories
 * @see example.chat.client.model.Chat
 * @see example.chat.client.repo.ChatRepository
 * @see example.chat.client.service.SimpleChatService
 * @since 1.0.0
 */
@SpringBootApplication
@ClientCacheApplication(name = "ChatClientApplication",
	locators = @ClientCacheApplication.Locator, subscriptionEnabled = true)
@EnableClusterConfiguration(useHttp = true)
@EnableContinuousQueries
@EnableEntityDefinedRegions(basePackageClasses = Chat.class)
@EnableGemfireRepositories(basePackageClasses = ChatRepository.class)
@EnablePdx
@EnableChatBot
@SuppressWarnings("all")
public class ChatClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatClientApplication.class, args);
	}

	@Autowired
	private SimpleChatService chatService;

	@PostConstruct
	public void postConstruct() {
		this.chatService.register(chatEvent -> chatEvent.getChat().ifPresent(System.err::println));
	}
}
