package com.github.rodrigohenriques.picnic.ui.custom;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NetherlandsCurrencyFormatterTest {

    @Test
    public void testFormat() throws Exception {
        CurrencyFormatter currencyFormatter = new NetherlandsCurrencyFormatter();
        String result = currencyFormatter.format(120);
        assertThat(result, is("€ 1,20"));
    }
}