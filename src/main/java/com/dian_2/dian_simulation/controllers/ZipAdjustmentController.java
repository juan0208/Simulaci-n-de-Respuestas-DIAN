package com.dian_2.dian_simulation.controllers;

import com.dian_2.dian_simulation.models.responses.DIANResponse;
import com.dian_2.dian_simulation.services.ZipAdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * Controlador REST para procesar archivos ZIP y enviar notas de ajuste simuladas a la DIAN.
 *
 * @since 29 de octubre de 2024
 * @autor Juan Alejandro Londoño Lopez
 */

@RestController
@RequestMapping("/api")
public class ZipAdjustmentController {

    @Autowired
    private ZipAdjustmentService zipAdjustmentService;

    /**
     * Procesa un archivo ZIP que contiene una nota de ajuste y valida el tipo de nota.
     *
     * @param file    El archivo ZIP que contiene la nota de ajuste.
     * @param noteType El tipo de nota (21 o 22) que se enviará a la DIAN.
     * @return Un {@link ResponseEntity} con la respuesta de la DIAN, que indica si el procesamiento fue exitoso.
     *         Retorna un estado BAD_REQUEST si el archivo está vacío.
     *         Retorna un estado INTERNAL_SERVER_ERROR si ocurre una excepción durante el procesamiento.
     */

    @PostMapping("/process-zip-note")
    public ResponseEntity<DIANResponse> processZipFile(
            @RequestParam("file") MultipartFile file, @RequestParam("noteType") int noteType) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(new DIANResponse( false));
        }

        try {
            DIANResponse result = zipAdjustmentService.processZipFile(file, noteType);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new DIANResponse( false));
        }
    }
}