package com.multi.restproduct.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
public class FileUploadUtils {

    public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get(uploadDir);
        // 디렉토리가 없으면 생성

        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        // 원본 파일 확장자 유지
        String replaceFileName = fileName + "." + FilenameUtils.getExtension(multipartFile.getResource().getFilename());

        try(InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(replaceFileName); //uploadPath와 replaceFileName을 결합해서 하나의 전체 경로(Path) 객체만듬
            // 업로드된 파일(InputStream)을 서버 디렉토리에 실제로 저장
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ex){
            throw new IOException("Could not save file: " + fileName, ex);
        }

        return replaceFileName;
    }

    public static boolean deleteFile(String uploadDir, String fileName) {

        boolean result = false;
        Path uploadPath = Paths.get(uploadDir);
        // 디렉토리가 없으면 삭제할 파일이 없으므로 true 반환
        if(!Files.exists(uploadPath)) {
            result = true;
        }
        try {
            Path filePath = uploadPath.resolve(fileName);
            Files.delete(filePath);  // 파일 삭제
            result = true;
        }catch (IOException ex){

           log.info("Could not delete file: " + fileName, ex);
        }

        return result;
    }
}
