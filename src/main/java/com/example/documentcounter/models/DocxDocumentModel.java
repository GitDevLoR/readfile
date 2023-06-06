package com.example.documentcounter.models;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class DocxDocumentModel implements DocumentModel {
    @Override
    public int countPages(Path filePath) {
        try (XWPFDocument document = new XWPFDocument(new FileInputStream(filePath.toString()))) {
            return document.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
