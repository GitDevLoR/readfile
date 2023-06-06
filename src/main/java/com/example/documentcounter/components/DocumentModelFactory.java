package com.example.documentcounter.components;

import com.example.documentcounter.models.DocumentModel;
import com.example.documentcounter.models.DocxDocumentModel;
import com.example.documentcounter.models.PdfDocumentModel;
import org.springframework.stereotype.Component;

@Component
public class DocumentModelFactory {

    public DocumentModel getParser(String filePath) {
        if (filePath.endsWith(".pdf")) {
            return new PdfDocumentModel();
        } else if (filePath.endsWith(".docx")) {
            return new DocxDocumentModel();
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + filePath);
        }
    }
}
