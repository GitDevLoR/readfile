package com.example.documentcounter.services;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class DocumentService {

    @Operation(summary = "Count pages in all documents within specified directory",
            description = "Counts the total number of pages in all documents within the specified directory")
    @ApiResponse(responseCode = "200", description = "Number of pages counted",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    public int countPagesInDirectory(
            @Parameter(description = "The directory path where the documents are located", required = true)
            String directoryPath) {
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            return paths
                    .filter(Files::isRegularFile)
                    .mapToInt(this::countPagesInFile)
                    .sum();
        } catch (IOException e) {
            throw new RuntimeException("Error reading directory", e);
        }
    }

    private int countPagesInFile(Path path) {
        String fileName = path.toString().toLowerCase();
        try {
            if (fileName.endsWith(".pdf")) {
                try (PDDocument document = PDDocument.load(new File(fileName))) {
                    return document.getNumberOfPages();
                }
            } else if (fileName.endsWith(".docx")) {
                try (XWPFDocument document = new XWPFDocument(new FileInputStream(fileName))) {
                    return document.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
        return 0;
    }
}
