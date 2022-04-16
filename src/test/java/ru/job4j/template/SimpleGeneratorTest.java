package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.IllegalFormatFlagsException;
import java.util.Map;

import static org.junit.Assert.*;

public class SimpleGeneratorTest {

    @Test
    public void when1KeyOk() {
        Generator generator = new SimpleGenerator();
        Map<String, String> map = Map.of("key1", "ключ 1");
        assertTrue("Это ключ 1!".equals(generator.produce("Это ${key1}!", map)));
    }

    @Test
    public void when2KeyOk() {
        Generator generator = new SimpleGenerator();
        Map<String, String> map = Map.of("key1", "ключ 1", "key2", "ключ 2");
        assertTrue("Второй ключ = ключ 2. Это ключ 1!".equals(
                generator.produce("Второй ключ = ${key2}. Это ${key1}!", map)));
    }

    @Test(expected = IllegalFormatFlagsException.class)
    public void when1KeyMisssing() {
        Generator generator = new SimpleGenerator();
        Map<String, String> map = Map.of("key1", "ключ 1");
        assertTrue("Второй ключ = ключ 2. Это ключ 1!".equals(
                generator.produce("Второй ключ = ${key2}. Это ${key1}!", map)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void when1KeyError() {
        Generator generator = new SimpleGenerator();
        Map<String, String> map = Map.of("key1", "ключ 1", "key2", "ключ 2");
        assertTrue("Второй ключ = ключ 2. Это ключ 1!".equals(
                generator.produce("Второй ключ = ${key2}. Это {key1}!", map)));
    }
}