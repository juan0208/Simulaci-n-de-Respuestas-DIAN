package com.dian_2.dian_simulation.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase que representa la respuesta de la DIAN al procesar una nota de ajuste.
 *
 * @since 29 de octubre de 2024
 * @autor Juan Alejandro Londoño Lopez
 */

@Data
@AllArgsConstructor
public class DIANResponse {
    private boolean dianResponse;
}
