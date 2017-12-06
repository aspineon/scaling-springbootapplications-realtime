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

package example.chat.client.repo;

import org.springframework.data.repository.CrudRepository;

import example.chat.client.model.Chat;

/**
 * The {@link ChatRepository} interface defines a Data Access Object (DAO) containing basic CRUD
 * and simple Query data access operations for {@link Chat} objects.
 *
 * @author John Blum
 * @see org.springframework.data.repository.CrudRepository
 * @see example.chat.client.model.Chat
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface ChatRepository extends CrudRepository<Chat, String> {

}
