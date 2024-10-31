package com.dian_2.dian_simulation.services;

import com.dian_2.dian_simulation.models.responses.DIANResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Servicio encargado de procesar archivos ZIP, extrayendo y validando los archivos XML en su interior
 * para asegurar que cumplan con los requisitos de tags y tipo de nota.
 *
 * @since 29 de octubre de 2024
 * @autor Juan Alejandro Londoño Lopez
 */

@Service
public class ZipAdjustmentService {

    @Autowired
    ValidateTagsService validateTags;
    @Autowired
    NoteTypeService noteTypeService;

    /**
     * Procesa un archivo ZIP para encontrar y validar un archivo XML.
     * Valida que el XML contenga los tags requeridos y sea del tipo de nota correcto.
     *
     * @param file     Archivo ZIP que contiene el archivo XML a procesar.
     * @param noteType Tipo de nota que debe tener el archivo XML.
     * @return {@link DIANResponse} indicando si la validación fue exitosa o no.
     * @throws IOException Si ocurre un error al leer el archivo ZIP.
     */

    public DIANResponse processZipFile(MultipartFile file, int noteType) throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream())) {
            ZipEntry zipEntry;
            ArrayList<Object> fileNames = new ArrayList<>();


            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                fileNames.add(zipEntry.getName());


                if (!zipEntry.isDirectory() && zipEntry.getName().toLowerCase().endsWith(".xml")) {
                    StringBuilder xmlContent = new StringBuilder();
                    int bytesRead;
                    byte[] buffer = new byte[1024];
                    while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                        xmlContent.append(new String(buffer, 0, bytesRead));
                    }


                    boolean isValid = validateTags.validateXML(new ByteArrayInputStream(xmlContent.toString().getBytes()));
                    boolean isCorrectType = noteTypeService.validateNoteType(new ByteArrayInputStream(xmlContent.toString().getBytes()),noteType);

                    if(isValid & isCorrectType){
                        return new DIANResponse( true);
                    }
                    return new DIANResponse( false);
                }
            }


            return new DIANResponse( false);
        } catch (Exception e) {
            e.printStackTrace();
            return new DIANResponse(false);
        }
    }
}