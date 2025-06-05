package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenFileHasCommentsAndEmptyLines() {
        String path = "./data/with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Dmitriy Trotsenko");
        assertThat(config.value("age")).isEqualTo("26");
    }

    @Test
    void whenLineWithoutKeyThenException() {
        String path = "./data/without_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Key is missing");
    }

    @Test
    void whenLineWithoutValueThenException() {
        String path = "./data/without_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Value is missing");
    }

    @Test
    void whenLineWithoutEqualsThenException() {
        String path = "./data/without_equals.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Line doesn't contain '='");
    }

    @Test
    void whenKeyWithExtraEqualsHandledCorrectly() {
        String path = "./data/extra_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isEqualTo("value=123");
        assertThat(config.value("Key")).isEqualTo("Value=");
    }
}