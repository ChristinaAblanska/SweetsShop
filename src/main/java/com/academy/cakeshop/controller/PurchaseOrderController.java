//package com.academy.cakeshop.controller;
//
//import com.academy.cakeshop.persistance.entity.PurchaseOrder;
//import com.academy.cakeshop.service.PurchaseOrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/purchase-orders")
//public class PurchaseOrderController {
//
//    private final PurchaseOrderService purchaseOrderService;
//
//    @Autowired
//    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
//        this.purchaseOrderService = purchaseOrderService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
//        List<PurchaseOrder> purchaseOrders = purchaseOrderService.getAllPurchaseOrders();
//        return new ResponseEntity<>(purchaseOrders, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable Long id) {
//        Optional<PurchaseOrder> purchaseOrder = purchaseOrderService.getPurchaseOrderById(id);
//        return purchaseOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
//        PurchaseOrder createdPurchaseOrder = purchaseOrderService.createPurchaseOrder(purchaseOrder);
//        return new ResponseEntity<>(createdPurchaseOrder, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@PathVariable Long id,
//                                                             @RequestBody PurchaseOrder updatedPurchaseOrder) {
//        PurchaseOrder purchaseOrder = purchaseOrderService.updatePurchaseOrder(id, updatedPurchaseOrder);
//        return purchaseOrder != null ? ResponseEntity.ok(purchaseOrder) : ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {
//        purchaseOrderService.deletePurchaseOrder(id);
//        return ResponseEntity.noContent().build();
//    }
//}