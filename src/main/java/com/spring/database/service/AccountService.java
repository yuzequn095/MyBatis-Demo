//package com.spring.database.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.spring.database.dao.AccountMapper;
//import com.spring.database.entity.Account;
//
//@Service
//public class AccountService {
//    @Autowired
//    private AccountMapper accountMapper;
//
//    public int add(String name, double money) {
//        return accountMapper.add(name, money);
//    }
//    public int update(String name, double money, int id) {
//        return accountMapper.update(name, money, id);
//    }
//    public int delete(int id) {
//        return accountMapper.delete(id);
//    }
//    public Account findAccount(int id) {
//        return accountMapper.findAccount(id);
//    }
//    public List<Account> findAccountList() {
//        return accountMapper.findAccountList();
//    }
//}