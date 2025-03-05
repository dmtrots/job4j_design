package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array)
                .hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "third", "fourth", "fifth");
        assertThat(list)
                .hasSize(5)
                .contains("fifth")
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
        assertThat(list).filteredOn(e -> e.length() > 5).first().isEqualTo("second");
        assertThat(list).filteredOnAssertions(e -> assertThat(e.length()).isLessThan(6))
                .hasSize(3)
                .last().isEqualTo("fifth");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "third", "fourth", "fifth", "third", "second");
        assertThat(set)
                .hasSize(5)
                .containsOnly("first", "second", "third", "fourth", "fifth")
                .containsExactlyInAnyOrder("second", "third", "fifth", "first", "fourth")
                .doesNotContain("seventh")
                .startsWith("third", "fifth")
                .endsWith("first", "second");
        assertThat(set).isNotNull()
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(10);
                    assertThat(e.length()).isGreaterThan(0);
                });
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap( "first", "second", "third", "fourth", "fifth");
        assertThat(map)
                .hasSize(5)
                .containsKeys("second", "fourth")
                .containsValues(3, 0, 4)
                .doesNotContainKey("sixth")
                .doesNotContainValue(5)
                .containsEntry("first", 0);
    }
}