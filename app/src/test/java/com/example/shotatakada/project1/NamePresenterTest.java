package com.example.shotatakada.project1;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class NamePresenterTest {
    private final String str = "test";
    private MainActivity mainActivity = new MainActivity();

    @Test
    public void validUserInput(){
        assertThat(mainActivity.checkInput(str),is(true));
    }

    @Test
    public void invalidUserInput(){
        assertThat(mainActivity.checkInput(""),is(false));
    }



}
