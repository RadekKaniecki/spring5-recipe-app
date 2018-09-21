package rkaniecki.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import rkaniecki.spring5recipeapp.commands.UnitOfMeasureCommand;
import rkaniecki.spring5recipeapp.domain.UnitOfMeasure;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "test";
    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        this.converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testNotNullObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(command);

        //then
        assertEquals(ID_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}