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

package example.chat.server;

import org.apache.geode.cache.GemFireCache;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.PartitionedRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableLocator;
import org.springframework.data.gemfire.config.annotation.EnableManager;
import org.springframework.data.gemfire.config.annotation.EnablePdx;

/**
 * The ChatServerApplication class...
 *
 * @author John Blum
 * @since 1.0.0
 */
@SpringBootApplication
@CacheServerApplication(name = "ChatServerApplication")
@EnableLocator
@EnableManager(start = true)
@EnablePdx
public class ChatServerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ChatServerApplication.class).web(WebApplicationType.NONE).build().run(args);
	}

	@Bean("Chats")
	public PartitionedRegionFactoryBean<Object, Object> chatsRegion(GemFireCache gemfireCache) {

		PartitionedRegionFactoryBean<Object, Object> partitionRegion = new PartitionedRegionFactoryBean<>();

		partitionRegion.setCache(gemfireCache);
		partitionRegion.setClose(false);
		partitionRegion.setPersistent(false);

		return partitionRegion;
	}
}
