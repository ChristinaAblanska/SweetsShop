package com.academy.cakeshop.service;

import com.academy.cakeshop.dto.ContractResponse;
import com.academy.cakeshop.dto.PaymentRequest;
import com.academy.cakeshop.dto.PaymentResponse;
import com.academy.cakeshop.enumeration.Currency;
import com.academy.cakeshop.errorHandling.BusinessNotFound;
import com.academy.cakeshop.persistance.entity.BankAccount;
import com.academy.cakeshop.persistance.entity.Contract;
import com.academy.cakeshop.persistance.entity.Payment;
import com.academy.cakeshop.persistance.repository.BankAccountRepository;
import com.academy.cakeshop.persistance.repository.ContractRepository;
import com.academy.cakeshop.persistance.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ContractRepository contractRepository;

    public PaymentService(PaymentRepository paymentRepository, BankAccountRepository bankAccountRepository, ContractRepository contractRepository) {
        this.paymentRepository = paymentRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.contractRepository = contractRepository;
    }

    public PaymentResponse getByID(Long id) {
        if (existsByID(id)) {
            Payment payment = paymentRepository.getReferenceById(id);
            return new PaymentResponse(payment.getFromBankAccount().getIban(), payment.getToBankAccount().getIban(),
                    payment.getAmount(), payment.getCurrency().toString());
        } else {
            throw new BusinessNotFound("No payment with id: " + id + " found!");
        }
    }

    public List<PaymentResponse> getAllByContractID(Long id) {
        List<Payment> payments = paymentRepository.findByContractID(id);
        if (payments != null) {
            List<PaymentResponse> paymentResponseList = new ArrayList<>();
            for (Payment payment : payments) {
                PaymentResponse paymentResponse = new PaymentResponse(payment.getFromBankAccount().getIban(), payment.getToBankAccount().getIban(),
                        payment.getAmount(), payment.getCurrency().toString());
                paymentResponseList.add(paymentResponse);
            }
            return paymentResponseList;
        } else {
            throw new BusinessNotFound("No payment with contractId: " + id + " found!");
        }
    }


    public boolean existsByID(Long id) {
        return paymentRepository.existsById(id);
    }
    public Payment create(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setDate(LocalDate.now());
        payment.setFromBankAccount(bankAccountRepository.findByIBAN(paymentRequest.fromIBAN()));
        payment.setToBankAccount(bankAccountRepository.findByIBAN(paymentRequest.toIBAN()));

        Contract contract = contractRepository.getReferenceById(paymentRequest.contractID());
        payment.setContract(contract);
        payment.setAmount(paymentRequest.amount());
        payment.setCurrency(Currency.getCurrencyFromString(paymentRequest.currency()));

        return paymentRepository.saveAndFlush(payment);
    }

    public void deleteByID(Long id) {
        if (existsByID(id)) {
            paymentRepository.deleteById(id);
        } else {
            throw new BusinessNotFound("No payment with id: " + id + " found!");
        }
    }
}