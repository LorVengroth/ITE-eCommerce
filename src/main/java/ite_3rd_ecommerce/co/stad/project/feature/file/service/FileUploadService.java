package ite_3rd_ecommerce.co.stad.project.feature.file.service;

import ite_3rd_ecommerce.co.stad.project.feature.file.dto.FileUploadResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {


    FileUploadResponse upload(MultipartFile file);
    List<FileUploadResponse> uploadMultiple(List<MultipartFile> file);
    void deleteFileByName(String name);
    Page<FileUploadResponse> findAll(int pageNumber , int pageSize);
    FileUploadResponse findByName(String name);

}
