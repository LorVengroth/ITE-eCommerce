package ite_3rd_ecommerce.co.stad.project.feature.file;

import ite_3rd_ecommerce.co.stad.project.feature.file.dto.FileUploadResponse;
import ite_3rd_ecommerce.co.stad.project.feature.file.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadService fileUploadService ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FileUploadResponse uploadFile(@RequestPart MultipartFile file){
        return fileUploadService.upload(file);
    }

    @PostMapping("/multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FileUploadResponse> uploadMultiple(@RequestPart("file") List<MultipartFile> files){
        return fileUploadService.uploadMultiple(files);
    }

    @GetMapping("/{name}")
    public FileUploadResponse findByName(@PathVariable String name) {
        return fileUploadService.findByName(name);
    }


    @GetMapping
    public Page<FileUploadResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return fileUploadService.findAll(pageNumber, pageSize);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByName(@PathVariable String name){
        fileUploadService.deleteFileByName(name);
    }



}
