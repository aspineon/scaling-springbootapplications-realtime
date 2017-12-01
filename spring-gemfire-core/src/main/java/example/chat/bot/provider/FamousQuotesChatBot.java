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

package example.chat.bot.provider;

import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;
import static org.cp.elements.lang.RuntimeExceptionsFactory.newIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.cp.elements.data.struct.KeyValue;
import org.cp.elements.util.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import example.chat.bot.ChatBot;
import example.chat.model.Person;
import example.chat.service.ChatService;

/**
 * The {@link FamousQuotesChatBot} class is a {@link ChatBot} implementation that chats {@link String famous quotes}
 * from various {@link Person people}.
 *
 * @author John Blum
 * @see org.cp.elements.data.struct.KeyValue
 * @see org.springframework.stereotype.Service
 * @see example.chat.bot.ChatBot
 * @see example.chat.model.Person
 * @see example.chat.service.ChatService
 * @since 1.0.0
 */
@Service
@SuppressWarnings("unused")
public class FamousQuotesChatBot implements ChatBot {

	private static final Iterable<Person> people = Arrays.asList(
		Person.newPerson("Aristotle", "(Greek)"),
		Person.newPerson("Benjamin", "Franklin"),
		Person.newPerson("Arthur", "Ashe"),
		Person.newPerson("Bertrand", "Russel"),
		Person.newPerson("Bill", "Waterson"),
		Person.newPerson("Bruce", "Lee"),
		Person.newPerson("Charles", "Dudley Warner"),
		Person.newPerson("Confucius", "(Chinese)"),
		Person.newPerson("David", "Lee Roth"),
		Person.newPerson("Don", "Marquis"),
		Person.newPerson("Eleanor", "Roosevelt"),
		Person.newPerson("George", "Moore"),
		Person.newPerson("Groucho", "Marx"),
		Person.newPerson("H. G.", "Wells"),
		Person.newPerson("Henry", "Youngman"),
		Person.newPerson("Isaac", "Asimov"),
		Person.newPerson("Jack", "Welch"),
		Person.newPerson("Jean-Luc", "Godard"),
		Person.newPerson("Jim", "Rohn"),
		Person.newPerson("Johann", "Wolfgang von Goethe"),
		Person.newPerson("John", "Burroughs"),
		Person.newPerson("Jules", "Renard"),
		Person.newPerson("Katherine", "Hepburn"),
		Person.newPerson("Ken", "Kesey"),
		Person.newPerson("Lana", "Turner"),
		Person.newPerson("Mark", "Twain"),
		Person.newPerson("Martin", "Luther King Jr."),
		Person.newPerson("Maya", "Angelou"),
		Person.newPerson("Milton", "Berle"),
		Person.newPerson("Mitch", "Hedberg"),
		Person.newPerson("Nelson", "Mandela"),
		Person.newPerson("Nikos", "Kazantzakis"),
		Person.newPerson("Oliver", "Herford"),
		Person.newPerson("Reba", "McEntire"),
		Person.newPerson("Reinhold", "Niebuhr"),
		Person.newPerson("Robert", "Benchley"),
		Person.newPerson("Robin", "Williams"),
		Person.newPerson("Samuel", "Beckett"),
		Person.newPerson("Stephan", "Hawking"),
		Person.newPerson("Theodore", "Roosevelt"),
		Person.newPerson("Thomas", "Edison"),
		Person.newPerson("Unknown", "Unknown"),
		Person.newPerson("Virat", "Kohli"),
		Person.newPerson("Walt", "Disney"),
		Person.newPerson("W. C.", "Fields"),
		Person.newPerson("W. H.", "Auden"),
		Person.newPerson("Will", "Rogers"),
		Person.newPerson("William", "Lyon Phelps"),
		Person.newPerson("William", "Shakespear"),
		Person.newPerson("Winston", "Churchill")
	);

	@SuppressWarnings("unchecked")
	private static final List<KeyValue<Person, String>> chats = asList(
		KeyValue.newKeyValue(findPersonBy("Aristotle (Greek)"), "Quality is not an act, it is a habit."),
		KeyValue.newKeyValue(findPersonBy("Arthur Ashe"), "Start where you are. Use what you have. Do what you can."),
		KeyValue.newKeyValue(findPersonBy("Benjamin Franklin"), "Well done is better than well said."),
		KeyValue.newKeyValue(findPersonBy("Bertrand Russel"), "I would never die for my beliefs because I might be wrong."),
		KeyValue.newKeyValue(findPersonBy("Bill Waterson"), "Reality continues to ruin my life."),
		KeyValue.newKeyValue(findPersonBy("Bruce Lee"), "Mistakes are always forgivable, if one has the courage to admit them."),
		KeyValue.newKeyValue(findPersonBy("Charles Dudley Warner"), "Everybody talks about the weather, but nobody does anything about it."),
		KeyValue.newKeyValue(findPersonBy("Confucius (Chinese)"), "It does not matter how slowly you go as long as you do not stop."),
		KeyValue.newKeyValue(findPersonBy("David Lee Roth"), "I used to jog but the ice cubes kept falling out of my glass."),
		KeyValue.newKeyValue(findPersonBy("Don Marquis"), "Procrastination is the art of keeping up with yesterday."),
		KeyValue.newKeyValue(findPersonBy("Eleanor Roosevelt"), "It is better to light a candle than curse the darkness."),
		KeyValue.newKeyValue(findPersonBy("H. G. Wells"), "If you fell down yesterday, stand up today."),
		KeyValue.newKeyValue(findPersonBy("Henry Youngman"), "If you're going to do something tonight that you'll be sorry for tomorrow morning, sleep late."),
		KeyValue.newKeyValue(findPersonBy("Isaac Asimov"), "People who think they know everything are a great annoyance to those of us who do."),
		KeyValue.newKeyValue(findPersonBy("George Moore"), "A man travels the world over in search of what he needs and returns home to find it."),
		KeyValue.newKeyValue(findPersonBy("Groucho Marx"), "Anyone who says he can see through women is missing a lot."),
		KeyValue.newKeyValue(findPersonBy("Groucho Marx"), "I refuse to join any club that would have me as a member."),
		KeyValue.newKeyValue(findPersonBy("Jack Welch"), "Change before you have to."),
		KeyValue.newKeyValue(findPersonBy("Jean-Luc Godard"), "To be or not to be. That's not really a question."),
		KeyValue.newKeyValue(findPersonBy("Jim Rohn"), "Either you run the day or the day runs you."),
		KeyValue.newKeyValue(findPersonBy("Johann Wolfgang von Goethe"), "Knowing is not enough; we must apply. Willing is not enough; we must do."),
		KeyValue.newKeyValue(findPersonBy("John Burroughs"), "The smallest deed is better than the greatest intention."),
		KeyValue.newKeyValue(findPersonBy("Jules Renard"), "Laziness is nothing more than the habit of resting before you get tired."),
		KeyValue.newKeyValue(findPersonBy("Katherine Hepburn"), "Life is hard. After all, it kills you."),
		KeyValue.newKeyValue(findPersonBy("Ken Kesey"), "You can't really be strong until you see a funny side to things."),
		KeyValue.newKeyValue(findPersonBy("Lana Turner"), "A successful man is one who makes more money than his wife can spend. A successful woman is one who can find such a man."),
		KeyValue.newKeyValue(findPersonBy("Maya Angelou"), "We may encounter many defeats but we must not be defeated."),
		KeyValue.newKeyValue(findPersonBy("Mark Twain"), "All generalizations are false, including this one."),
		KeyValue.newKeyValue(findPersonBy("Mark Twain"), "Don't let schooling interfere with your education."),
		KeyValue.newKeyValue(findPersonBy("Mark Twain"), "Go to Heaven for the climate, Hell for the company."),
		KeyValue.newKeyValue(findPersonBy("Martin Luther King Jr."), "Love is the only force capable of transforming an enemy into a friend."),
		KeyValue.newKeyValue(findPersonBy("Milton Berle"), "A committee is a group that keeps minutes and loses hours."),
		KeyValue.newKeyValue(findPersonBy("Mitch Hedberg"), "My fake plants died because I did not pretend to water them."),
		KeyValue.newKeyValue(findPersonBy("Nelson Mandela"), "It always seems impossible until it's done."),
		KeyValue.newKeyValue(findPersonBy("Nikos Kazantzakis"), "In order to succeed, we must first believe that we can."),
		KeyValue.newKeyValue(findPersonBy("Oliver Herford"), "A woman's mind is cleaner than a man's; She changes it more often."),
		KeyValue.newKeyValue(findPersonBy("Reinhold Niebuhr"), "God grant me the serenity to accept the things I cannot change, the courage to change the things I can, and the wisdom to know the difference."),
		KeyValue.newKeyValue(findPersonBy("Reba McEntire"), "To succeed in life, you need three things: a wishbone, a backbone and a funny bone."),
		KeyValue.newKeyValue(findPersonBy("Robert Benchley"), "Drawing on my fine command of the English language, I said nothing."),
		KeyValue.newKeyValue(findPersonBy("Robin Williams"), "I'm sorry, if you were right, I'd agree with you."),
		KeyValue.newKeyValue(findPersonBy("Samuel Beckett"), "Ever tried. Ever failed. No matter. Try Again. Fail again. Fail better."),
		KeyValue.newKeyValue(findPersonBy("Samuel Beckett"), "We are all born mad. Some remain so."),
		KeyValue.newKeyValue(findPersonBy("Stephan Hawking"), "Life would be tragic if it weren't funny."),
		KeyValue.newKeyValue(findPersonBy("Theodore Roosevelt"), "Keep your eyes on the stars, and your feet on the ground."),
		KeyValue.newKeyValue(findPersonBy("Thomas Edison"), "The chief function of the body is to carry the brain around."),
		KeyValue.newKeyValue(findPersonBy("Unknown Unknown"), "A leader is one who knows the way, goes the way, and shows the way."),
		KeyValue.newKeyValue(findPersonBy("Unknown Unknown"), "A lie gets halfway around the world before the truth has time to get its pants on."),
		KeyValue.newKeyValue(findPersonBy("Unknown Unknown"), "Be happy for this moment. This moment is your life."),
		KeyValue.newKeyValue(findPersonBy("Unknown Unknown"), "Give me a lever long enough and a fulcrum on which to place it and I shall move the world."),
		KeyValue.newKeyValue(findPersonBy("Unknown Unknown"), "The greater danger for most of us lies not in setting our aim too high and falling short, but in setting our aim too low and achieving our mark."),
		KeyValue.newKeyValue(findPersonBy("Unknown Unknown"), "The only thing worse than being blind is having sight but no vision."),
		KeyValue.newKeyValue(findPersonBy("Virat Kohli"), "Self-belief and hard work will always earn you success."),
		KeyValue.newKeyValue(findPersonBy("Walt Disney"), "The way to get started is to quit talking and begin doing."),
		KeyValue.newKeyValue(findPersonBy("W. C. Fields"), "I cook with wine, sometimes I even add it to the food."),
		KeyValue.newKeyValue(findPersonBy("W. H. Auden"), "We are all here on earth to help others; what on earth the others are here for I don't know."),
		KeyValue.newKeyValue(findPersonBy("Will Rogers"), "Be thankful we're not getting all the government we're paying for."),
		KeyValue.newKeyValue(findPersonBy("Will Rogers"), "Everything is funny, as long as it's happening to somebody else."),
		KeyValue.newKeyValue(findPersonBy("William Lyon Phelps"), "If at first you don't succeed, find out if the loser gets anything."),
		KeyValue.newKeyValue(findPersonBy("William Shakespear"), "We know what we are, but know not what we may be."),
		KeyValue.newKeyValue(findPersonBy("Winston Churchill"), "I may be drunk, Miss, but in the morning I will be sober and you will still be ugly.")
	);

	private static List<String> findChatsBy(Person person) {

		return chats.stream()
			.filter(it -> it.getKey().equals(person))
			.map(it -> it.getValue("What?"))
			.collect(Collectors.toList());
	}

	private static Person findPersonBy(String name) {

		return stream(people.spliterator(), false)
			.filter(person -> person.getName().equals(name))
			.findFirst()
			.orElse(null);
	}

	private final ChatService chatService;

	private final Random randomIndex;

	public FamousQuotesChatBot(ChatService chatService) {

		this.chatService = Optional.ofNullable(chatService)
			.orElseThrow(() -> newIllegalArgumentException("ChatService is required"));

		this.randomIndex = new Random(System.currentTimeMillis());
	}

	private ChatService getChatService() {
		return this.chatService;
	}

	@Override
	public String chat(Person person) {

		List<String> personChats = findChatsBy(person);

		return personChats.isEmpty() ? "What?" : personChats.get(randomIndex(personChats.size()));
	}


	private Person random() {

		List<Person> personList = CollectionUtils.asList(people);

		return personList.get(randomIndex(personList.size()));
	}

	private int randomIndex(int bound) {
		return this.randomIndex.nextInt(bound);
	}

	@Scheduled(initialDelay = 5000L, fixedRateString = "${example.chat.bot.schedule.rate:3000}")
	public void sendChat() {

		Person person = random();

		getChatService().send(person, chat(person));
	}
}
