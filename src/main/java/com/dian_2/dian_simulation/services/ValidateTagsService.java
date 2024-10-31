package com.dian_2.dian_simulation.services;
import com.dian_2.dian_simulation.utils.enums.RequiredTags;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Servicio encargado de validar si un documento XML contiene todos los tags requeridos.
 *
 * @since 29 de octubre de 2024
 * @autor Juan Alejandro Londoño Lopez
 */

@Service
public class ValidateTagsService {


    /**
     * Valida si un archivo XML contiene todos los tags definidos en {@link RequiredTags}.
     *
     * @param xmlInputStream Flujo de entrada del archivo XML.
     * @return {@code true} si el archivo XML contiene todos los tags requeridos, {@code false} en caso contrario.
     */

    public boolean validateXML(InputStream xmlInputStream) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();



            Document document = builder.parse(xmlInputStream);


            NodeList allNodes = document.getElementsByTagName("*");


            Set<String> requiredTags = Arrays.stream(RequiredTags.values())
                    .map(RequiredTags::getTagName) // Use getTagName() for the prefix
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());


            for (int i = 0; i < allNodes.getLength(); i++) {
                Node node = allNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String nodeName = node.getNodeName().toLowerCase();
                    requiredTags.remove(nodeName);
                }
            }


            return requiredTags.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}