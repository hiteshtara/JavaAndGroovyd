package com.nick
import org.groovykoans.koan01.UserService;
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import com.edu.service.Service
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.groovykoans.koan01.User
import org.groovykoans.koan01.UserService
import org.groovykoans.koan01.Address
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.MockitoAnnotations.initMocks
import static org.junit.Assert.assertThat
import static org.hamcrest.CoreMatchers.*
import static org.junit.matchers.JUnitMatchers.*
import static org.junit.Assert.assertEquals

class GroovyDeveloperTest {

	private Developer groovyDeveloper

	@Mock PrintStream output

	@Before void beforeTest() {
		initMocks(this)
		groovyDeveloper = new GroovyDeveloper(output)
	}

	@Test void shouldDoSomethingGroovy() {
		groovyDeveloper.doStuff()
		verify(output, times(1)).println("I am developing with Java.")
		verify(output, times(1)).println("I'm adding some groovy goodness.")
	}
	@Test void test01_AssertionsAndSomeSyntax() {
		// Groovy introduces the GroovyTestCase for testing. We are using it as the base class for our unit tests.
		// Docs for GroovyTestCase can be found at: http://groovy.codehaus.org/api/groovy/util/GroovyTestCase.html

		// Groovy can be used solely with Java syntax. You have full control over what facet of Groovy you want.

		// Let's start. First - Groovy requires much less boiletplate code. For example, in Groovy we don't
		// have to use semicolons at all. As a matter of fact, we don't always need to define the type of the variable!
		boolean assertion = false
		def hello = "Hola"

		// Assign our variables the required values to continue...
		// ------------ START EDITING HERE ----------------------
		hello = "Hello"
		assertion = true
		// ------------ STOP EDITING HERE  ----------------------

		assert assertion, 'Assign "true" to the "assertion" variable to proceed'
		assert hello == "Hello", 'Modify "Hola" to "Hello" to proceed'
	}
	@Test void testInterceptedMethodCallonPOJO() {
		def val = new Integer(3) 
		Integer.metaClass.toString = {-> 'intercepted' }
		assert "intercepted", val.toString() }


   @Test void test02_GStrings() {
		// Groovy allows you to use either regular quotes (') or double-quotes (") for String declarations.
		// The difference is that double-quotes create a GString, which is a super-powered String.
		// For docs about GStrings, see http://groovy.codehaus.org/Strings+and+GString#StringsandGString-GStrings

		// GStrings allow you to use the ${} syntax within them. The ${} can contain any valid Groovy expression.
		def name = 'George'
		def greeting = "Hello ${name}, how are you?" // 'Hello George, how are you?
		def math = "The result of 4 + 4 is ${4 + 4}" // 'The result of 4 + 4 is 8'

		// Create the target string with the ${} mechanism. Remember that ${} can contain method calls too!
		String result
		// ------------ START EDITING HERE ----------------------
		result = "The size of the string '${greeting}' is ${greeting.size()}"
		// ------------ STOP EDITING HERE  ----------------------

		assert result == "The size of the string 'Hello George, how are you?' is 26"
	}

   @Test void test03_MapsInGroovy() {
		// Maps are also special citizens in Groovyland.
		// Docs can be found here: http://groovy.codehaus.org/JN1035-Maps
		def map = [right: 'derecha', left: 'izquierda']

		// Concatenate the two values of 'right' and 'left' into result to proceed using Groovy syntax
		def result
		// ------------ START EDITING HERE ----------------------
		result = map['right'] + map['left']
		// ------------ STOP EDITING HERE  ----------------------

		assert result.toCharArray().size() == 16
	}
   //String.metaClass.shout = {->
	  // return delegate.toUpperCase()
/*he no-argument shout() closure is added to the String's ExpandoMetaClass (EMC). 
 * Every class — both Java and Groovy — is surrounded by an EMC that 
 * intercepts method calls to it. This means that even though the String is final, 
 * methods can be added to its EMC. 
 * As a result, it now looks to the casual observer as if String has a shout() method.
Because this kind of relationship doesn't exist in the Java language, 
Groovy had to introduce a new concept: delegates. 
The delegate is the class that the EMC surrounds.
Knowing that method calls hit the EMC first and the delegate second, you can do all sorts of interesting things. For example, notice how Listing 9 actually redefines the toUpperCase() method on String:*/	
  @Test void testStringShout(){
	   def shout= {src->src.toUpperCase()
		   assert HELLO==shout("hello")
	   }
   }



	
   @Test void test04_Lists() {
		// In Java, list creation can be somewhat cumbersome:
		List<String> javaList = new ArrayList<String>();
		javaList.add("King");
		javaList.add("Queen");
		javaList.add("Prince");

		// In Groovy, this is simplified to:
		// (See http://groovy.codehaus.org/JN1015-Collections
		// and http://groovy.codehaus.org/groovy-jdk/java/util/List.html)
		def groovyList = ['King', 'Prince']

		// Add the missing item to the Groovy list. Pay attention to the order of the items.
		// Hint: you can use either Java's add(int, String) or Groovy's plus() method.
		// ------------ START EDITING HERE ----------------------
		groovyList = groovyList.plus(1, 'Queen')
		// ------------ STOP EDITING HERE  ----------------------

		// Note how Groovy allows you to compare the *content* of the lists
		assert groovyList == javaList
	}

   @Test void test05_ElvisAndSafeNavigation() {
		// Preparation code for the examples that follow. We'll get to this code in later Koans.
		User player = new User('Ronaldo', 'Nazário de Lima', 'ron', null)
		UserService userServiceWithUserLoggedIn = [getLoggedInUser: {player}] as UserService
		UserService userServiceWithoutLoggedInUser = [getLoggedInUser: {null}] as UserService

		// Groovy introduces two convenient operators for dealing with nulls: elvis (?:) and safe navigation (?.)
		// Read all about it at http://groovy.codehaus.org/Operators#Operators-ElvisOperator

		// Assume we have a User object that may or may not contain a first name and an address.
		// In Java, we could end up with the following code:

		String firstName = player.getFirstName();
		String javaDisplayName = firstName == null ? player.getUsername() : firstName;
		String javaCity = "";
		if (player.getAddress() != null && player.getAddress().getCity() != null) { // Be careful of NullPointerExceptions
			javaCity = player.getAddress().getCity();
		}

		// With Groovy's new operators, this becomes much simpler:
		def groovyDisplayName = player.firstName ?: player.username
		def groovyCity = player?.address?.city

		// Using your newly acquired knowledge, fix the createMessageForUser method below
		// so that anonymous users get 'Hello Anonymous!' and logged in users get 'Hello <first name>'
		// You should use userService.getLoggedInUser() as well.
		assert createMessageForUser(userServiceWithUserLoggedIn) == 'Hello Ronaldo!'
		assert createMessageForUser(userServiceWithoutLoggedInUser) == 'Hello Anonymous!'
	}
  @Test void test01_Expando() {
	   // So far we haven't dealt with the dynamic nature of Groovy. What does dynamic mean? It means
	   // that you can change classes' behavior during runtime. Let's take the Groovy Expando class as an example.
	   // http://mrhaki.blogspot.com/2009/10/groovy-goodness-expando-as-dynamic-bean.html

	   // Create an Expando class and dynamically create a 'firstName' property set with some value. Also
	   // add a sayHello() method that returns "Hello from ${firstName}"
	   def expando = new Expando()
	   // ------------ START EDITING HERE ----------------------
	   expando.firstName = 'Frank'
	   expando.sayHello = {->
		   "Hello from ${firstName}"
	   }
	   // ------------ STOP EDITING HERE  ----------------------

	   assert expando?.firstName != null, 'firstName property was not found'
	   assert expando.sayHello() == "Hello from ${expando.firstName}"
   }


    private String createMessageForUser(UserService userService) {
		def message
		// ------------ START EDITING HERE ----------------------
		message = "Hello ${userService.loggedInUser?.firstName ?: 'Anonymous'}!"
		// ------------ STOP EDITING HERE  ----------------------

		// Note how Groovy doesn't require the 'return' keyword! It will simply return the last expression.
		message
	}

}
