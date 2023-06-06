package com.example.documentcounter.services;

import com.example.documentcounter.components.DocumentModelFactory;
import com.example.documentcounter.models.DocumentModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class DocumentService {

    private final DocumentModelFactory documentModelFactory;

    public DocumentService(DocumentModelFactory documentModelFactory) {
        this.documentModelFactory = documentModelFactory;
    }

    @Operation(summary = "Count pages in all documents within specified directory",
            description = "Counts the total number of pages in all documents within the specified directory")
    @ApiResponse(responseCode = "200", description = "Number of pages counted",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    public int countPagesInDirectory(
            @Parameter(description = "The directory path where the documents are located", required = true)
            String directoryPath) {
        try {
            Path realPath = resolveSymbolicLink(Paths.get(directoryPath));
            try (Stream<Path> paths = Files.walk(realPath)) {
                return paths
                        .filter(Files::isRegularFile)
                        .mapToInt(this::countPagesInFile)
                        .sum();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private Path resolveSymbolicLink(Path path) {
        try {
            return path.toRealPath();
        } catch (IOException e) {
            return path;
        }
    }

    private int countPagesInFile(Path filePath) {
        DocumentModel documentModel = documentModelFactory.getParser(filePath.toString());
        return documentModel.countPages(filePath);
    }
}
