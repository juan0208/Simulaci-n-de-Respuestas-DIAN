package com.dian_2.dian_simulation.utils.enums;

/**
 * Enumeración que representa los tags XML requeridos en los documentos procesados.
 * Cada valor enum tiene asociado el nombre completo del tag, que incluye su prefijo y nombre de etiqueta.
 *
 * @since 29 de octubre de 2024
 * @autor Juan Alejandro Londoño Lopez
 */

public enum RequiredTags {
    AccountingSupplierParty("cac:AccountingSupplierParty"),
    AccountingCustomerParty("cac:AccountingCustomerParty"),
    PaymentMeans("cac:PaymentMeans"),
    TaxTotal("cac:TaxTotal"),
    LegalMonetaryTotal("cac:LegalMonetaryTotal");

    private final String tagName;

    RequiredTags(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}