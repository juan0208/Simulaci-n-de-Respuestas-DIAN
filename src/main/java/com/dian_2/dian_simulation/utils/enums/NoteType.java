package com.dian_2.dian_simulation.utils.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeración que representa los tipos de nota, como Nota de Crédito y Nota de Débito.
 * Proporciona un método para obtener el tipo de nota en función de su valor entero asociado.
 *
 * @since 29 de octubre de 2024
 * @autor Juan Alejandro Londoño Lopez
 */

public enum NoteType {
    CreditNote(21),
    DebitNote(22);

    private final int noteType;

    NoteType(int noteType) {
        this.noteType = noteType;
    }

    private static final Map<Integer, NoteType> BY_NOTE_TYPE = new HashMap<>();

    static {
        for (NoteType noteType : values()) {
            BY_NOTE_TYPE.put(noteType.noteType, noteType);
        }
    }

    /**
     * Obtiene el tipo de nota correspondiente al valor entero proporcionado.
     *
     * @param noteType Valor entero que representa el tipo de nota.
     * @return El tipo de nota correspondiente si existe, o null si no se encuentra.
     */

    public static NoteType forNoteType(int noteType) {
        return BY_NOTE_TYPE.getOrDefault(noteType, null);
    }
}
