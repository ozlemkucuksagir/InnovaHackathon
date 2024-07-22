package com.example.hackathon.Controller;

import com.example.hackathon.Entity.Transaction;
import com.example.hackathon.Entity.MyUser;
import com.example.hackathon.Service.TransactionService;
import com.example.hackathon.Service.MyUserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    MyUserService myUserService;

    TransactionService transactionService;

    public TransactionController(MyUserService myUserService, TransactionService transactionService) {
        this.myUserService = myUserService;
        this.transactionService = transactionService;
    }

    @GetMapping("/getAllTransactions")
    public List<Transaction> getAllTransactions(){

        return transactionService.getAllTransaction();
    }

    @GetMapping("/getTransactionByID/{transactionId}")
    public Transaction getTransactionByID(@PathVariable Long transactionId){


        return transactionService.getTransactionByID(transactionId);
    }

    @PutMapping("/updateTransaction/{transactionId}")
    public void updateTransation(@RequestBody Transaction updateTransation, @PathVariable Long transactionId){
        transactionService.updateTransaction(updateTransation,transactionId);
    }

    @PostMapping("/createTransaction")
    public void createTransaction(@RequestBody Transaction newTransaction){
        MyUser myUser = myUserService.getUserByID(newTransaction.getMyUser().getId());
        newTransaction.setMyUser(myUser);

        transactionService.createTransaction(newTransaction);

    }

        @DeleteMapping("/deleteTransaction/{transactionId}")
    public void deleteTransation(@PathVariable Long transactionId){

        transactionService.deleteTransaction(transactionId);

    }


    @GetMapping("/user/{userId}")
    public Map<String, Object> getTransactionsAndTotal(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.getAllTransactionsByUserId(userId);
        Double totalExpenses = transactionService.getTotalExpenses(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("transactions", transactions);
        response.put("total", totalExpenses);

        return response;
    }

}
