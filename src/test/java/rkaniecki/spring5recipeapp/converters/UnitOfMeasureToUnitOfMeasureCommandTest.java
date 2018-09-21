package rkaniecki.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import rkaniecki.spring5recipeapp.commands.UnitOfMeasureCommand;
import rkaniecki.spring5recipeapp.domain.UnitOfMeasure;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "test";
    private UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        this.converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void assertNotNullObject() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID_VALUE);
        uom.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand command = converter.convert(uom);

        //then
        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}