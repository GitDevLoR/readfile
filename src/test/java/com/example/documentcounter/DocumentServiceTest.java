package com.example.documentcounter;

import com.example.documentcounter.services.DocumentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DocumentServiceTest {

    @MockBean
    private DocumentService documentService;

    @Test
    public void testCountPages() {
        String testDirectoryPath = "/path/to/your/test/directory";
        int expectedPagesCount = 10;  // Ожидаемое количество страниц документа в тестовой директории

        when(documentService.countPagesInDirectory(testDirectoryPath)).thenReturn(expectedPagesCount);

        int actualPagesCount = documentService.countPagesInDirectory(testDirectoryPath);

        assertEquals(expectedPagesCount, actualPagesCount);
    }
}
