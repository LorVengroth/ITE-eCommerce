package ite_3rd_ecommerce.co.stad.project.feature.file;

import ite_3rd_ecommerce.co.stad.project.feature.file.dto.FileUploadResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUpload , Long> {
    Optional<FileUpload> findByName(String name);

}
