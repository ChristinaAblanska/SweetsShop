//package com.academy.cakeshop.service;
//
//import com.academy.cakeshop.persistance.entity.PurchaseOrder;
//import com.academy.cakeshop.persistance.repository.PurchaseOrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PurchaseOrderService {
//
//    private final PurchaseOrderRepository purchaseOrderRepository;
//
//    @Autowired
//    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository) {
//        this.purchaseOrderRepository = purchaseOrderRepository;
//    }
//
//    public List<PurchaseOrder> getAllPurchaseOrders() {
//        return purchaseOrderRepository.findAll();
//    }
//
//    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
//        return purchaseOrderRepository.findById(id);
//    }
//
//    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
//        return purchaseOrderRepository.save(purchaseOrder);
//    }
//
//    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder updatedPurchaseOrder) {
//        Optional<PurchaseOrder> existingPurchaseOrderOptional = purchaseOrderRepository.findById(id);
//        if (existingPurchaseOrderOptional.isPresent()) {
//            updatedPurchaseOrder.setId(id);
//            return purchaseOrderRepository.save(updatedPurchaseOrder);
//        }
//        return null;
//    }
//
//    public void deletePurchaseOrder(Long id) {
//        purchaseOrderRepository.deleteById(id);
//    }
//}