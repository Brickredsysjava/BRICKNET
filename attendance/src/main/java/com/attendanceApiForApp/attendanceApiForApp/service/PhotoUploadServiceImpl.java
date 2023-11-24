package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.PhotoUpload;
import com.attendanceApiForApp.attendanceApiForApp.repository.PhotoUloadRepository;
import org.springframework.stereotype.Service;

@Service
public class PhotoUploadServiceImpl implements PhotoUploadService {
    private final PhotoUloadRepository photoUloadRepository;

    public PhotoUploadServiceImpl(PhotoUloadRepository photoUloadRepository) {
        this.photoUloadRepository = photoUloadRepository;
    }

    @Override
    public PhotoUpload saveFileName(PhotoUpload photoUpload) {
        return photoUloadRepository.save(photoUpload);
    }
}
