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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.codenjoy.dojo.services.generator.ElementGeneratorTest.assertSmokeEquals;

public class ElementsGeneratorRunnerTest {

    @Rule
    public TestName test = new TestName();

    @Test
    public void shouldGenerate_allGames_andLanguages() {
        // given
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));

        ElementGenerator.READONLY = true;

        // when
        ElementGeneratorRunner.main(new String[0]);

        // then
        assertEquals(stream.toString());
    }

    private void assertEquals(String actual) {
        assertSmokeEquals(actual, getClass(), test);
    }
}
