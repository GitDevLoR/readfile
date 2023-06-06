package com.example.documentcounter.models;

import java.nio.file.Path;

public interface DocumentModel {
    int countPages(Path filePath);
}
