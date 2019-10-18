package microbnb.accountingms.service;

import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import microbnb.accountingms.model.entity.Account;
import microbnb.accountingms.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {

  private final AccountRepository accountRepository;

  @Transactional
  public Account saveAccount(Account account) {
    return accountRepository.save(account);
  }

  @Transactional(readOnly = true)
  public Optional<Account> getAccount(long id) {
    return accountRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Account> getAccounts() {
    return List.ofAll(accountRepository.findAll());
  }

  @Transactional
  public void remove(long id) {
    accountRepository.deleteById(id);
  }

}
