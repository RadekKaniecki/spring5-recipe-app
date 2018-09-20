package rkaniecki.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rkaniecki.spring5recipeapp.commands.NotesCommand;
import rkaniecki.spring5recipeapp.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Nullable
    @Override
    public Notes convert(NotesCommand source) {

        if (source != null) {
            final Notes notes = new Notes();
            notes.setId(source.getId());
            notes.setRecipeNotes(source.getRecipeNotes());

            return notes;
        }

        return null;
    }
}
