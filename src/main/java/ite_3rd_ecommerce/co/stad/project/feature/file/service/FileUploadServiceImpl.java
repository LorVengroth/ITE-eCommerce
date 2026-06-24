package ite_3rd_ecommerce.co.stad.project.feature.file.service;


import ite_3rd_ecommerce.co.stad.project.feature.file.dto.FileUploadResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@NoArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${file.storage-location}")
    private String storageLocation;

    @Value("${file.base-uri}")
    private String baseUri ;

    @Override
    public FileUploadResponse upload(MultipartFile file) {




        // prepare file information
        // File name
        String fileName = UUID.randomUUID().toString();


        // extension
        String ext = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));

        fileName += "." + ext ; // new-unique-fileName.ext


        // create absolute path to store file
        Path path = Paths.get( storageLocation + fileName);

        try {
            Files.copy(file.getInputStream() , path);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "file has been failed to upload");
        }


        return FileUploadResponse.builder()
                .name(fileName)
                .size(file.getSize())
                .mediaType(file.getContentType())
                .uri(baseUri + "/" + fileName)
                .build();
    }


    @Override
    public List<FileUploadResponse> uploadMultiple(MultipartFile[] files) {
        List<FileUploadResponse> responses = new ArrayList<>();


        for (MultipartFile file : files) {

            FileUploadResponse response = upload(file);
            responses.add(response);
        }

        return responses;
    }


    @Override
    public void deleteFileByName(String name) {

            Path path = Paths.get(storageLocation + name);

            try {

                if (Files.exists(path)) {
                    Files.delete(path);
                } else {

                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found with name: " + name);
                }
            } catch (IOException e) {

                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete the file");
            }
        }

}
