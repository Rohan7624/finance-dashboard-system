package net.javaguides.banking.service;

import net.javaguides.banking.dto.AccountDto;
import org.hibernate.mapping.List;


public interface AccountService{


    AccountDto createAccount(AccountDto accountDto);

    AccountDto GetAccountById(Long id);

    AccountDto deposit(Long id , Double amount);

    AccountDto withdraw(Long id , Double amount);

    List<AccountDto> getAllAccounts();



}
