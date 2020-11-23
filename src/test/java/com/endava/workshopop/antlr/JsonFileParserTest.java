package com.endava.workshopop.antlr;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.endava.workshop.antlr.JsonFileParser;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A series of tests that check if JSON is correctly parse")
class JsonFileParserTest {

  @Test
  @DisplayName("An empty object can be parsed")
  void givenEmptyJson_whenParse_thenResultIsEmptyMap() {
    //  given
    JsonFileParser parser = new JsonFileParser("/empty.json");
    //  when
    Map<String, Object> result = parser.parse();
    //  then
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("A string field can be parsed")
  void givenStringField_whenParse_thenResultContainsString() {
    //  given
    JsonFileParser parser = new JsonFileParser("/string.json");
    //  when
    Map<String, Object> result = parser.parse();
    //  then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertThat(result, hasKey("name"));
    assertThat(result.get("name"), is("first"));
  }

  @Test
  @DisplayName("A numeric field can be parsed")
  void givenNumericField_whenParse_thenResultContainsNumber() {
    //  given
    JsonFileParser parser = new JsonFileParser("/number.json");
    //  when
    Map<String, Object> result = parser.parse();
    //  then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertThat(result, hasKey("age"));
    assertThat(result.get("age"), is(87));
  }

  @Test
  @DisplayName("A boolean field can be parsed")
  void givenBooleanField_whenParse_thenResultContainsBoolean() {
    //  given
    JsonFileParser parser = new JsonFileParser("/boolean.json");
    //  when
    Map<String, Object> result = parser.parse();
    //  then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertThat(result, hasKey("assigned"));
    assertThat(result.get("assigned"), is(false));
  }

  @Test
  @SuppressWarnings("unchecked")
  @DisplayName("An object field can be parsed")
  void givenObjectField_whenParse_thenResultContainsObject() {
    //  given
    JsonFileParser parser = new JsonFileParser("/object.json");
    //  when
    Map<String, Object> result = parser.parse();
    //  then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertThat(result, hasKey("address"));
    assertThat(result.get("address"), instanceOf(Map.class));
    Map<String, Object> address = (Map<String, Object>) result.get("address");
    assertNotNull(address);
    assertTrue(address.isEmpty());
  }

  @Test
  @SuppressWarnings("unchecked")
  @DisplayName("All types of fields can be parsed together")
  void givenAllTypesOfFields_whenParse_thenResultContainsAllFieldTypes() {
    //  given
    JsonFileParser parser = new JsonFileParser("/all.json");
    //  when
    Map<String, Object> result = parser.parse();
    //  then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertThat(result, hasKey("name"));
    assertThat(result.get("name"), is("second"));
    assertThat(result, hasKey("age"));
    assertThat(result.get("age"), is(33));
    assertThat(result, hasKey("assigned"));
    assertThat(result.get("assigned"), is(true));
    assertThat(result, hasKey("address"));
    assertThat(result.get("address"), instanceOf(Map.class));
    Map<String, Object> address = (Map<String, Object>) result.get("address");
    assertNotNull(address);
    assertTrue(address.isEmpty());
  }


  @Test
  @SuppressWarnings("unchecked")
  @DisplayName("Nested object can be parsed together")
  void givenNestedObject_whenParse_thenResultContainsNestedObjects() {
    //  given
    JsonFileParser parser = new JsonFileParser("/nested.json");
    //  when
    Map<String, Object> result = parser.parse();
    //  then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertThat(result, hasKey("name"));
    assertThat(result, hasKey("age"));
    assertThat(result, hasKey("assigned"));
    assertThat(result, hasKey("address"));
    assertThat(result.get("address"), instanceOf(Map.class));
    Map<String, Object> address = (Map<String, Object>) result.get("address");
    assertNotNull(address);
    assertFalse(address.isEmpty());
    assertThat(address, hasKey("city"));
    assertThat(address.get("city"), is("Sidney"));
    assertThat(address, hasKey("street"));
    assertThat(address.get("street"), is("Paris"));
    assertThat(address, hasKey("number"));
    assertThat(address.get("number"), is(67));
  }

}
