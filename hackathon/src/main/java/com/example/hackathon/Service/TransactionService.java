package com.example.hackathon.Service;

import com.example.hackathon.Entity.Transaction;
import com.example.hackathon.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransaction(){
        return transactionRepository.findAll();
    }

    public Double getTotalExpenses(Long userId) {
        return transactionRepository.findTotalExpensesByUserId(userId);
    }
    public List<Transaction> getAllTransactionsByUserId(Long userId) {
        return transactionRepository.findAllByUserId(userId);
    }

    public Transaction getTransactionByID(Long transactionId){
        return transactionRepository.findById(transactionId).orElse(null);

    }
    @Transactional
    public void deleteTransaction(Long transactionId){
        transactionRepository.deleteById(transactionId);
    }

    public void createTransaction(Transaction newTransaction){
        transactionRepository.save(newTransaction);
    }

    public void updateTransaction(Transaction updatedTransaction,Long transactionId){


        Transaction transaction=transactionRepository.findById(transactionId).orElse(null);
        if(transaction!=null) {

            transaction.setAmount(updatedTransaction.getAmount());
            transaction.setDate(updatedTransaction.getDate());
            transaction.setDescription(updatedTransaction.getDescription());
            transaction.setUser(updatedTransaction.getUser());

            transactionRepository.save(transaction);
        }

    }

    public Double getTotalExpensesa(Long userId) {
        return transactionRepository.findTotalExpensesByUserId(userId);
    }

}
