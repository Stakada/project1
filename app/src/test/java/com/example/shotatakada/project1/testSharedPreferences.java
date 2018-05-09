package com.example.shotatakada.project1;


import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.Context;
import android.content.SharedPreferences;


@RunWith(MockitoJUnitRunner.class)
public class testSharedPreferences {

    @Mock
    SharedPreferences mockSharedPreferences;

    @Mock
    SharedPreferences.Editor mockEditor ;


    private SharedPreferencesHelper mockSharedPreferencesHelper;

    private String text_entry = "test";

    @Before
    public void initMocks() {

        // Create a mocked SharedPreferences.
        mockSharedPreferencesHelper = createMockSharedPreference();

    }

    @Test
    public void sharedPreferences_SaveAndReadEntry() {

        // Save the personal information to SharedPreferences
        boolean success = mockSharedPreferencesHelper.saveEntry(text_entry);

        assertThat("SharedPreferenceEntry.save... returns true",
                success, is(true));

        assertEquals(text_entry, mockSharedPreferencesHelper.getEntry());

    }

    /**
     * Creates a mocked SharedPreferences object for successful read/write
     */
    private SharedPreferencesHelper createMockSharedPreference() {

        // Mocking reading the SharedPreferences as if mMockSharedPreferences was previously written
        // correctly.
        when(mockSharedPreferences.getString(eq("input"), anyString()))
                .thenReturn(text_entry);

        // Mocking a successful commit.
        when(mockEditor.commit()).thenReturn(true);

        // Return the MockEditor when requesting it.
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);

        return new SharedPreferencesHelper(mockSharedPreferences);
    }
}