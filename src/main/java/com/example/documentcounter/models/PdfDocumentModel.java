package com.example.documentcounter.models;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class PdfDocumentModel implements DocumentModel {
    @Override
    public int countPages(Path filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath.toString()))) {
            return document.getNumberOfPages();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
