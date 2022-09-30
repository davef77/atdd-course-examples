package com.cd.acceptance.dsl.utils;

import com.cd.acceptance.dsl.utils.Params;
import junit.framework.AssertionFailedError;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParamsTest {
    private Params.DslContext context;

    @Before
    public void setUp(){
        context = new Params.DslContext();
    }

    @Test
    public void shouldReturnOptionValueOfParam() {
        Params params = new Params(context, new String[]{"One: 1", "Two: 2"});

        assertEquals("3", params.Optional("Three", "3"));
    }

    @Test
    public void shouldReturnDefinedValueOverridingDefault() {
        Params params = new Params(context, new String[]{"One: 1", "Two: 2"});

        assertEquals("1", params.Optional("One", "3"));
    }

    @Test
    public void shouldReturnValueOverridingOptionalSequenceValue() {
        Params params = new Params(context, new String[]{"One: 1", "Two: 2"});

        assertEquals("1", params.OptionalSequence("One", 3));
    }

    @Test
    public void shouldReturnStartValueOfOptionalSequence() {
        Params params = new Params(context, new String[]{"One: 1", "Two: 2"});

        assertEquals("3", params.OptionalSequence("Three", 3));
    }

    @Test
    public void shouldReturnNextValueOfOptionalSequence() {
        Params params = new Params(context, new String[]{"One: 1", "Two: 2"});

        params.OptionalSequence("Three", 5);
        assertEquals("6", params.OptionalSequence("Three", 5));
    }

    @Test
    public void shouldSupplyIncrementedAliasAcrossContexts() {
        Params params = new Params(context, new String[]{"name: nameTest"});
        Params params2 = new Params(new Params.DslContext(), new String[]{"name: nameTest"});

        assertNotEquals(params.Alias("name"), params2.Alias("name"));
    }

    @Test
    public void shouldSupplyConsistentAliasWithinContext() {
        Params params = new Params(context, new String[]{"name: nameTest"});
        assertEquals(params.Alias("name"), params.Alias("name"));
    }

    @Test(expected = AssertionFailedError.class)
    public void shouldFailAliasIfValueNotPresent() {
        Params params = new Params(context, new String[]{"name: nameTest"});
        params.Alias("name2");
    }
}
