//package com.academy.cakeshop.service;
//
//import com.academy.cakeshop.enumeration.BankAccountStatus;
//import com.academy.cakeshop.persistance.entity.*;
//import com.academy.cakeshop.persistance.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//@Transactional
//public class PaymentSupplierService {
//    @Autowired
//    private SaleRepository saleRepository;
//    private PurchaseOrderRepository purchaseOrderRepository;
//private PaymentRepository paymentRepository;
//private AccountHistoryRepository accountHistoryRepository;
//private BankAccountRepository bankAccountRepository;
//
//    public void processDailyPayments(LocalDate date) {
//        List<Sale> sales = saleRepository.findByDate(date);
//        for (Sale sale : sales) {
//            double amountToPay = sale.getAmount() * 0.3;
//        }
//
//    }
//
//
//}
