package fr.ratp.suivi.services;

import fr.ratp.suivi.repositories.RoleRepository;
import fr.ratp.suivi.services.fileUpload.FileUploadService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class FileUploadServiceTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    FileUploadService fileUploadService;


    @Test
    public void uploadFile() throws IOException, ClassNotFoundException {

        File file = new File("Role.csv");
        //delete if exits
        file.delete();
        Resource stateFile = new ClassPathResource("Role.csv");

        MockMultipartFile mockMultipartFile = new MockMultipartFile("Role.csv", stateFile.getInputStream());

        fileUploadService.uploadFile(mockMultipartFile, "Role");

    }


}
