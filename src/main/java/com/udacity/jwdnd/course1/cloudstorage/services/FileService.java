package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> getFileNames(int userId){
         return fileMapper.getFileNames(userId);
    }

    public File getFile(Integer fileId){
        return fileMapper.getFile(fileId);
    }

    public int addFile(File file){
        File newFile = new File();
        newFile.setFileSize(file.getFileSize());
        newFile.setContentType(file.getContentType());
        newFile.setFileName(file.getFileName());
        newFile.setFileData(file.getFileData());
        newFile.setUserId(file.getUserId());
        return fileMapper.addFile(newFile);
    }

    public int deleteFile(Integer fileId){
        return fileMapper.delete(fileId);
    }
}
