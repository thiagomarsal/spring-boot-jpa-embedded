package com.example.service;

import com.example.domain.Image;

import java.util.List;

public interface ImageService {

    public void delete(Long id);

    public Image save(Image image);

    public List<Image> findAll();

    public Image findById(Long id);
}
