package com.academy.cakeshop.service;

import com.academy.cakeshop.dto.ContractRequest;
import com.academy.cakeshop.dto.ContractResponse;
import com.academy.cakeshop.enumeration.ContractPeriod;
import com.academy.cakeshop.enumeration.ContractStatus;
import com.academy.cakeshop.enumeration.Currency;
import com.academy.cakeshop.errorHandling.BusinessNotFound;
import com.academy.cakeshop.persistance.entity.Contract;
import com.academy.cakeshop.persistance.entity.User;
import com.academy.cakeshop.persistance.repository.ContractRepository;
import com.academy.cakeshop.persistance.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final UserRepository userRepository;

    public ContractService(ContractRepository contractRepository, UserRepository userRepository) {
        this.contractRepository = contractRepository;
        this.userRepository = userRepository;
    }

    public ContractResponse getByID(Long id) {
        Contract contract = contractRepository.getReferenceById(id);
        // TODO - ask Nadeto if it is always true
        if (contract != null) {
            String contractorName = contract.getUser().getFirstName() + " " + contract.getUser().getLastName();
            return new ContractResponse(contract.getContractSum(), contract.getCurrency().toString(),
                    contract.getContractPeriod().toString(), contractorName);
        } else {
            throw new BusinessNotFound("No contract with id: " + id + "found!");
        }
    }

//    public List<Contract> getAll() {
//        return contractRepository.findAll();
//    }

    public List<ContractResponse> getByUserID(long userId) {
        List<Contract> contracts = contractRepository.findAllByUserId(userId);
        List<ContractResponse> contractResponseList = new ArrayList<>();
        if (contracts != null) {
            for (Contract contract : contracts) {
                String contractorName = contract.getUser().getFirstName() + " " + contract.getUser().getLastName();
                ContractResponse contractResponse = new ContractResponse(contract.getContractSum(),
                        contract.getCurrency().toString(), contract.getContractPeriod().toString(), contractorName);
                contractResponseList.add(contractResponse);
            }
            return contractResponseList;
        } else {
            throw new BusinessNotFound("No contracts with userId: " + userId + "found!");
        }
    }

    public boolean existsByID(Long id) {
        return contractRepository.existsById(id);
    }

    public void create(ContractRequest contractRequest, String userName) {
        Contract contract = new Contract();
        contract.setContractSum(contractRequest.contractSum());
        contract.setCurrency(Currency.getCurrencyFromString(contractRequest.currency()));
        contract.setContractPeriod(ContractPeriod.getContractPeriodFromString(contractRequest.contractPeriod()));
        contract.setContractStatus(ContractStatus.NEW);

        User user = userRepository.findByUserName(userName);
        contract.setUser(user);
        contractRepository.saveAndFlush(contract);
    }

    public int updateContractStatus(String status, Long contractId) {
        ContractStatus contractStatus = ContractStatus.getContractStatusFromString(status);
        return contractRepository.updateContractStatusById(contractStatus, contractId);
    }

    public void deleteById(Long id) {
        if (existsByID(id)) {
            contractRepository.deleteById(id);
        } else {
            throw new BusinessNotFound("No contract with id: " + id + "found!");
        }
    }
}