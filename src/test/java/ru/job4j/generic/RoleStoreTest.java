package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleIsSeller() {
        UserStore store = new UserStore();
        store.add(new User("1", "Seller"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Seller");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Seller"));
        User result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleIsSeller() {
        UserStore store = new UserStore();
        store.add(new User("1", "Seller"));
        store.add(new User("1", "Cleaner"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Seller");
    }

    @Test
    void whenReplaceThenRoleIsCleaner() {
        UserStore store = new UserStore();
        store.add(new User("1", "Seller"));
        store.replace("1", new User("1", "Cleaner"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Cleaner");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRole() {
        UserStore store = new UserStore();
        store.add(new User("1", "Seller"));
        store.replace("10", new User("10", "Cleaner"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Seller");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Seller"));
        store.delete("1");
        User result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleIsSeller() {
        UserStore store = new UserStore();
        store.add(new User("1", "Seller"));
        store.delete("10");
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Seller");
    }

    @Test
    void whenReplaceOkThenTrue() {
        UserStore store = new UserStore();
        store.add(new User("1", "Seller"));
        boolean result = store.replace("1", new User("1", "Cleaner"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        UserStore store = new UserStore();
        store.add(new User("1", "Seller"));
        boolean result = store.replace("10", new User("10", "Cleaner"));
        assertThat(result).isFalse();
    }
}