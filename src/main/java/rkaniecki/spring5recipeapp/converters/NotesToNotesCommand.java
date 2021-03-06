package rkaniecki.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rkaniecki.spring5recipeapp.commands.NotesCommand;
import rkaniecki.spring5recipeapp.domain.Notes;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Nullable
    @Override
    public NotesCommand convert(Notes source) {

        if (source != null) {
            final NotesCommand notesCommand = new NotesCommand();
            notesCommand.setId(source.getId());
            notesCommand.setRecipeNotes(source.getRecipeNotes());

            return notesCommand;
        }

        return null;
    }
}
