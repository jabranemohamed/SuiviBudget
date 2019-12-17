package fr.ratp.suivi.web.controllers;

import fr.ratp.suivi.services.fileUpload.FileUploadService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Api(value = "FileUpload", description = "Point d'entrer pour uploader un fichier ")
public class FileUploadController extends BaseController {

    @Autowired
    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity singleFileUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            fileUploadService.uploadFile(file, "className");

        } catch (IOException | ClassNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
