package com.example.service;

import com.example.domain.Image;
import com.example.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void delete(Long id) {
        imageRepository.delete(id);
    }

    @Override
    public Image save(Image imgage) {
        return imageRepository.saveAndFlush(imgage);
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findById(Long id) {
        return imageRepository.findOne(id);
    }
}
