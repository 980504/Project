package project.chatbotApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
public class UploadController {

    private final Path uploadDir = Path.of("C:/Users/youss/Desktop/chatbotApp/chatbotApp/src/main/resources/pdfs");

    @PostMapping("/upload")
    public ResponseEntity<String> uploadSimple(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Fichier vide.");
        }

        Path destination = uploadDir.resolve(file.getOriginalFilename());

        try {
            file.transferTo(destination.toFile());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur enregistrement : " + e.getMessage());
        }

        return ResponseEntity.ok("Fichier uploadé avec succès");

    }

}
