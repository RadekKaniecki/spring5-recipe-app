package rkaniecki.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import rkaniecki.spring5recipeapp.commands.NotesCommand;
import rkaniecki.spring5recipeapp.domain.Notes;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTES = "test";
    private NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        this.converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNullObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand command = converter.convert(notes);

        //then
        assertEquals(ID_VALUE, command.getId());
        assertEquals(RECIPE_NOTES, command.getRecipeNotes());
    }
}