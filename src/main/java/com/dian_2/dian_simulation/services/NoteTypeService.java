package com.dian_2.dian_simulation.services;

import com.dian_2.dian_simulation.utils.enums.NoteType;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * Servicio encargado de validar y obtener el tipo de nota en base a un documento XML y un tipo de nota especificado.
 *
 * @since 29 de octubre de 2024
 * @autor Juan Alejandro Londoño Lopez
 */

@Service
public class NoteTypeService {

    /**
     * Valida que el tipo de nota en el archivo XML coincida con el tipo de nota esperado.
     *
     * @param xmlInputStream Flujo de entrada del archivo XML.
     * @param noteType       Tipo de nota esperado (21 para nota de crédito, 22 para nota de débito).
     * @return {@code true} si el tipo de nota en el XML coincide con el tipo esperado, {@code false} en caso contrario.
     */

    public boolean validateNoteType(InputStream xmlInputStream, int noteType) {
        String type = getLineType(noteType);

        try {
            // Crear un factory para construir el parser
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parsear el documento XML
            Document doc = dBuilder.parse(xmlInputStream);
            doc.getDocumentElement().normalize();

            // Obtener el elemento raíz
            Element root = doc.getDocumentElement();

            // Comparar el nombre del elemento raíz con el tipo esperado
            return root.getNodeName().equals(type);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene el nombre del tipo de nota en base al tipo de nota especificado.
     *
     * @param noteType Tipo de nota (21 para nota de crédito, 22 para nota de débito).
     * @return Nombre del tipo de nota como cadena.
     * @throws IllegalArgumentException Si el tipo de nota especificado no es válido.
     */

    public String getLineType(int noteType) {
        NoteType lineType = NoteType.forNoteType(noteType);
        if (lineType != null) {
            return lineType.name();
        } else {
            throw new IllegalArgumentException("Tipo de nota no válido: " + noteType);
        }
    }
}
