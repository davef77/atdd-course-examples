package com.cd.acceptance.dsl.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.fail;

/**
 * Copyright (c) Continuous Delivery Ltd. 2022
 */
public class Params
{
    private final DslContext context;
    private final String[] args;

    public Params(DslContext context, String[] args)
    {
        this.context = context;
        this.args = args;
    }

    public String Optional(String name, String defaultValue)
    {
        String arg = getParamValue(name);
        if (arg != null) return arg;
        return defaultValue;
    }

    public String Alias(String name)
    {
        String value = getParamValue(name);
        if (value == null) {
            fail("No '" + name + "' supplied for alias");
        }
        return context.alias(value);
    }

    public String OptionalSequence(String name, int start) {
        return Optional(name, context.sequenceNumberForName(name, start));
    }

    private String getParamValue(String name) {
        for (String arg : args)
        {
            int index = arg.indexOf(name + ": ");
            if (index != -1)
            {
                return arg.substring(index + name.length() + 2);
            }
        }
        return null;
    }

    public List<String> OptionalList(String name, String[] items) {
        return null;
    }

    public static class DslContext {
        private static final Map<String, Integer> globalSequenceNumbers = new HashMap<>();
        private final Map<String, Integer> sequenceNumbers = new HashMap<>();
        private final Map<String, String> aliases = new HashMap<>();

        public String sequenceNumberForName(String name, int start) {
            return seqForName(name, start, sequenceNumbers);
        }

        public String alias(String name) {
            if (!aliases.containsKey(name)) {
                String sequenceNo = seqForName(name, 1, globalSequenceNumbers);
                aliases.put(name, name + sequenceNo);
            }
            return aliases.get(name);
        }

        private String seqForName(String name, int start, Map<String, Integer> sequenceNumbers) {
            int retVal = start;
            if (sequenceNumbers.containsKey(name)) {
                retVal = sequenceNumbers.get(name);
            }
            sequenceNumbers.put(name, retVal + 1);

            return String.valueOf(retVal);
        }
    }
}
