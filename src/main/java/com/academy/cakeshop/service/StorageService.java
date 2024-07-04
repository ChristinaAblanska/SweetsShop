package com.academy.cakeshop.service;

import com.academy.cakeshop.persistance.entity.Storage;
import com.academy.cakeshop.persistance.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
    }

    public Storage createStorage(Storage storage) {

        return storageRepository.save(storage);
    }

    public Optional<Storage> getStorageById(Long id) {

        return storageRepository.findById(id);
    }

    public List<Storage> getAllStorages() {

        return storageRepository.findAll();
    }

    public Storage updateStorage(Long id, Storage storageDetails) {
        Storage storage = storageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Storage not found"));
        storage.setQuantity(storageDetails.getQuantity());
        storage.setProduct(storageDetails.getProduct());
        return storageRepository.save(storage);
    }

    public void deleteStorage(Long id) {
        storageRepository.deleteById(id);
    }
}
