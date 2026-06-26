package ite_3rd_ecommerce.co.stad.project.feature.file.service;


import ite_3rd_ecommerce.co.stad.project.feature.file.FileUpload;
import ite_3rd_ecommerce.co.stad.project.feature.file.FileUploadMapper;
import ite_3rd_ecommerce.co.stad.project.feature.file.FileUploadRepository;
import ite_3rd_ecommerce.co.stad.project.feature.file.dto.FileUploadResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${file.storage-location}")
    private String storageLocation;

    @Value("${file.base-uri}")
    private String baseUri ;

    private final FileUploadMapper fileUploadMapper ;
    private final FileUploadRepository fileUploadRepository ;



    @Override
    public FileUploadResponse upload(MultipartFile file) {
        return saveFile(file);
    }



    @Override
    public List<FileUploadResponse> uploadMultiple(List<MultipartFile> files) {
        return files.stream()
                .map(this::saveFile)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteFileByName(String name) {


        FileUpload fileUpload = fileUploadRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File has not been found"));
        fileUploadRepository.delete(fileUpload);

        // Create absolute path to store file
        Path path = Paths.get(storageLocation + fileUpload.getName() + "." + fileUpload.getExtension());
        try {
            boolean isExisted = Files.deleteIfExists(path);
            if (!isExisted)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File has not been found");
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File has been failed to delete");
        }

        }


    private FileUploadResponse saveFile(MultipartFile file){


        // prepare file information
        // File name
        String fileName = UUID.randomUUID().toString();


        // extension
        String ext = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));

//        fileName += "." + ext ;
        // new-unique-fileName.ext


        // create absolute path to store file
        Path path = Paths.get( storageLocation + fileName + "." +  ext);

        try {
            Files.copy(file.getInputStream() , path);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "file has been failed to upload");
        }

        // save information to database
        FileUpload fileUpload = new FileUpload();
        fileUpload.setName(fileName);
        fileUpload.setExtension(ext);
        fileUpload.setCaption("captain");
        fileUpload.setSize(file.getSize());
        fileUpload.setMediaType(file.getContentType());
        fileUploadRepository.save(fileUpload);


        return fileUploadMapper.mapFileUploadToFileUploadResponse(fileUpload);
    }


    @Override
    public Page<FileUploadResponse> findAll(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC , "id");
        PageRequest pageRequest = PageRequest.of(pageNumber , pageSize , sortById);
        Page<FileUpload> fileUploadsResponse = fileUploadRepository.findAll(pageRequest);
        return fileUploadsResponse.map(fileUploadMapper::mapFileUploadToFileUploadResponse);
    }

    @Override
    public FileUploadResponse findByName(String name) {
        return fileUploadRepository.findByName(name)
                .map(fileUploadMapper::mapFileUploadToFileUploadResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File has not been found"));

    }
}
