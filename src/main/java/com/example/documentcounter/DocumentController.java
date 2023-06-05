package com.example.documentcounter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/count")
    @Operation(summary = "Count number of pages in all documents within specified directory",
            description = "Returns the total number of pages in all documents within the specified directory")
    @ApiResponse(responseCode = "200", description = "Number of pages counted",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    public int countPages(
            @Parameter(description = "The directory path where the documents are located", required = true)
            @RequestParam String directoryPath) {
        return documentService.countPagesInDirectory(directoryPath);
    }
}
