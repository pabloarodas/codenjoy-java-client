package com.codenjoy.dojo.services.generator;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2012 - 2022 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.codenjoy.dojo.utils.SmokeUtils;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.io.File;
import java.util.Arrays;

import static java.util.Locale.ENGLISH;

@Ignore
public class ElementGeneratorTest {

    @Rule
    public TestName test = new TestName();

    @Test
    public void shouldGenerate_testGame_goLanguage() {
        assertGenerate("test", "go");
    }

    @Test
    public void shouldGenerate_testGame_cppLanguage() {
        assertGenerate("test", "cpp");
    }

    @Test
    public void shouldGenerate_testGame_jsLanguage() {
        assertGenerate("test", "js");
    }

    @Test
    public void shouldGenerate_testGame_phpLanguage() {
        assertGenerate("test", "php");
    }

    @Test
    public void shouldGenerate_testGame_javaLanguage() {
        assertGenerate("test", "java");
    }

    @Test
    public void shouldGenerate_testGame_pythonLanguage() {
        assertGenerate("test", "python");
    }

    @Test
    public void shouldGenerate_testGame_markdownLanguage() {
        assertGenerate("test", "md");
    }

    @Test
    public void shouldGenerate_testAnotherGame_markdownLanguage() {
        assertGenerate("test-another", "md");
    }

    @Test
    public void shouldGenerate_testGame_markdownHeaderLanguage() {
        assertGenerate("test", "md_header");
    }

    @Test
    public void shouldGenerate_testGame_markdownFooterLanguage() {
        assertGenerate("test", "md_footer");
    }

    @Test
    public void shouldGenerate_testGame_csharpLanguage() {
        assertGenerate("test", "csharp");
    }

    @Test
    public void shouldGenerate_sampleGame_goLanguage() {
        assertGenerate("sample", "go");
    }

    @Test
    public void shouldGenerate_sampleGame_cppLanguage() {
        assertGenerate("sample", "cpp");
    }

    @Test
    public void shouldGenerate_sampleGame_jsLanguage() {
        assertGenerate("sample", "js");
    }

    @Test
    public void shouldGenerate_sampleGame_phpLanguage() {
        assertGenerate("sample", "php");
    }

    @Test
    public void shouldGenerate_sampleGame_javaLanguage() {
        assertGenerate("sample", "java");
    }

    @Test
    public void shouldGenerate_sampleGame_pythonLanguage() {
        assertGenerate("sample", "python");
    }

    @Test
    public void shouldGenerate_sampleGame_markdownLanguage() {
        assertGenerate("sample", "md");
    }

    @Test
    public void shouldGenerate_mollymageGame_markdownLanguage() {
        assertGenerate("mollymage", "md");
    }

    @Test
    public void shouldGenerate_mollymageGame_javaLanguage() {
        assertGenerate("mollymage", "java");
    }

    @Test
    public void shouldGenerate_cliffordGame_markdownLanguage() {
        assertGenerate("clifford", "md");
    }

    @Test
    public void shouldGenerate_cliffordGame_javaLanguage() {
        assertGenerate("clifford", "java");
    }

    private void assertEquals(String actual) {
        assertSmokeEquals(actual, getClass(), test);
    }

    public static void assertSmokeEquals(String actual, Class owner, TestName test) {
        SmokeUtils.assertSmokeFile(owner.getSimpleName()
                + "/" + test.getMethodName() +  ".data",
                Arrays.asList(actual
                        .replace("\r\n", "\n")
                        .split("\n")));
    }

    private void assertGenerate(String game, String language) {
        assertEquals(new ElementGenerator(game, language, ENGLISH, base(game)).generate());
    }

    // TODO если получится избавиться от этого чуда, будет здорово
    //      надо еще помнить что в prod этот генератор запускают в
    //      двух режимах: из запущенного приложения - тогда файлы properties
    //      ищем в classpath и батником для генерации elements - там мы
    //      ищем properties в сырцах.
    private String base(String game) {
        switch (game) {
            case "test":
            case "test-another":
                return "";
            default:
                return new File(".").getAbsolutePath();
        }
    }
}