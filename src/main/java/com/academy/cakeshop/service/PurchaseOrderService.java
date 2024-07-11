package com.academy.cakeshop.service;


import com.academy.cakeshop.dto.ArticleRequestDTO;
import com.academy.cakeshop.dto.PurchaseOrderRequestDTO;
import com.academy.cakeshop.enumeration.BankAccountStatus;
import com.academy.cakeshop.persistance.entity.*;
import com.academy.cakeshop.persistance.repository.ContractRepository;
import com.academy.cakeshop.persistance.repository.ProductRepository;
import com.academy.cakeshop.persistance.repository.PurchaseOrderRepository;
import com.academy.cakeshop.persistance.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;

    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;
    private final ContractRepository contractRepository;

    public PurchaseOrder createPurchaseOrder(PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setDate(purchaseOrderRequestDTO.getDate());
        purchaseOrder.setPrice(purchaseOrderRequestDTO.getPrice());
        purchaseOrder.setQuantity(purchaseOrderRequestDTO.getQuantity());
        purchaseOrder.setBankAccountStatus(BankAccountStatus.valueOf(purchaseOrderRequestDTO.getStatus()));
        Optional<Product> productOptional = productRepository.findById(purchaseOrderRequestDTO.getProductId());
        if (productOptional.isPresent()) {
            purchaseOrder.setProduct(productOptional.get());
        } else {
            throw new RuntimeException("Product not found");
        }

        Optional<Unit> unitOptional = unitRepository.findById(purchaseOrderRequestDTO.getUnitId());
        if (unitOptional.isPresent()) {
            purchaseOrder.setUnit(unitOptional.get());
        } else {
            throw new RuntimeException("Unit not found");
        }

        Optional<Contract> contractOptional = contractRepository.findById(purchaseOrderRequestDTO.getContractId());
        if (contractOptional.isPresent()) {
            purchaseOrder.setContract(contractOptional.get());
        } else {
            throw new RuntimeException("Contract not found");
        }

        return purchaseOrderRepository.save(purchaseOrder);
    }
    public List<PurchaseOrder> getAllPurchaseOrder() {
        return purchaseOrderRepository.findAll();
    }

    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id);
    }
    public List<PurchaseOrderRequestDTO> getPurchaseOrdersByDate(LocalDate date) {
        return purchaseOrderRepository.findByOrderDate(date);
    }
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
        Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepository.findById(id);
        if (purchaseOrderOptional.isPresent()) {
            PurchaseOrder purchaseOrder = purchaseOrderOptional.get();
            purchaseOrder.setDate(purchaseOrderRequestDTO.getDate());
            purchaseOrder.setPrice(purchaseOrderRequestDTO.getPrice());
            purchaseOrder.setQuantity(purchaseOrderRequestDTO.getQuantity());
            purchaseOrder.setBankAccountStatus(BankAccountStatus.valueOf(purchaseOrderRequestDTO.getStatus()));
            Optional<Product> productOptional = productRepository.findById(purchaseOrderRequestDTO.getProductId());
            productOptional.ifPresent(purchaseOrder::setProduct);
            Optional<Unit> unitOptional = unitRepository.findById(purchaseOrderRequestDTO.getUnitId());
            unitOptional.ifPresent(purchaseOrder::setUnit);
            Optional<Contract> contractOptional = contractRepository.findById(purchaseOrderRequestDTO.getContractId());
            contractOptional.ifPresent(purchaseOrder::setContract);

            return purchaseOrderRepository.save(purchaseOrder);
        } else {
            throw new RuntimeException("Purchase order not found");
        }
    }
            public void deletePurchaseOrder(Long id) {
        Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepository.findById(id);
        if (purchaseOrderOptional.isPresent()) {
            purchaseOrderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Purchase order not found");
        }
    }
}