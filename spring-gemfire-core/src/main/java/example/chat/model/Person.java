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

package example.chat.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.cp.elements.lang.Identifiable;
import org.cp.elements.util.ComparatorResultBuilder;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * The {@link Person} class is an Abstract Data Type (ADT) modeling a person.
 *
 * @author John Blum
 * @see java.lang.Comparable
 * @see java.io.Serializable
 * @see java.time.LocalDate
 * @see org.cp.elements.lang.Identifiable
 * @see lombok
 * @since 1.0.0
 */
@Data
@RequiredArgsConstructor(staticName = "newPerson")
@SuppressWarnings("unused")
public class Person implements Comparable<Person>, Identifiable<Long>, Serializable {

	private static final long serialVersionUID = -9009417088665004887L;

	private Long id;

	private Gender gender;

	private LocalDate birthDate;

	@NonNull
	private String firstName;

	@NonNull
	private String lastName;

	public String getName() {
		return String.format("%1$s %2$s", getFirstName(), getLastName());
	}

	public Person as(Gender gender) {
		setGender(gender);
		return this;
	}

	public Person born(LocalDate birthDate) {
		setBirthDate(birthDate);
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int compareTo(Person that) {

		return ComparatorResultBuilder.<Comparable>create()
			.doCompare(this.getLastName(), that.getLastName())
			.doCompare(this.getFirstName(), that.getFirstName())
			.doCompare(this.getBirthDate(), that.getBirthDate())
			.build();
	}

	@Override
	public String toString() {
		return getName();
	}

	enum Gender {
		FEMALE,
		MALE
	}
}
