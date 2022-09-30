package com.cd.acceptance.dsl.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.cd.acceptance.dsl.utils.Channels.Amazon;
import static com.cd.acceptance.dsl.utils.Channels.BookDepository;

public class ChannelFinderTest {
    @Channel({Amazon, BookDepository})
    @Test
    public void shouldReportChannelList() throws Exception
    {
        Assert.assertEquals(Arrays.asList("Amazon", "BookDepository"), ChannelFinder.listChannels());
    }
}
