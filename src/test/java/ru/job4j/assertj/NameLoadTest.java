package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void checkArrayContainsMessage() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void checkArrayContainsWrongArgument() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key:value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("key:val");
    }

    @Test
    void checkArrayDoesNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("a key")
                .hasMessageContaining("=value");
    }

    @Test
    void checkArrayDoesNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("a value")
                .hasMessageContaining("key=");
    }

    @Test
    void checkArrayContainsVariable() {
        NameLoad nameLoad = new NameLoad();
        String key = "first key";
        assertThatThrownBy(() -> nameLoad.parse(key))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(key);
    }
}