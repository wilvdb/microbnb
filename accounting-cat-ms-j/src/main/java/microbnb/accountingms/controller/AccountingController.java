package microbnb.accountingms.controller;

import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import microbnb.accountingms.exception.NotFoundException;
import microbnb.accountingms.model.dto.AccountInput;
import microbnb.accountingms.model.dto.ResponseData;
import microbnb.accountingms.model.entity.Account;
import microbnb.accountingms.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(value = "/v1/accounts", produces = "application/json")
public class AccountingController {

  private final AccountService accountService;

  @GetMapping
  public ResponseEntity getAccounts() {
    List<Account> accounts = accountService.getAccounts();
      if (accounts.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(new ResponseData<>(accounts));
  }

  @GetMapping("/{id}")
  public ResponseEntity getAccount(@PathVariable long id) {
    Optional<Account> account = accountService.getAccount(id);

      ResponseData<Account> data = new ResponseData<>(account.orElseThrow(NotFoundException::new));
      return ResponseEntity.ok(data);
  }

  @PostMapping(consumes = "application/json")
  public ResponseEntity createAccount(AccountInput input) {
      Account acc = new Account();
    acc.setBirthday(input.getBirthday());
    acc.setEmail(input.getEmail());
    acc.setFirstName(input.getFirstName());
    acc.setLastName(input.getLastName());
    acc.setEmail(input.getEmail());
      Account account = accountService.saveAccount(acc);
    return ResponseEntity.created(URI.create("http://localhost:8080/" + account.getId())).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity removeAccount(@PathVariable long id) {
    accountService.remove(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}", consumes = "application/json")
  public ResponseEntity updateAccount(@PathVariable long id, AccountInput input) {
      Optional<Account> accOpt = accountService.getAccount(id);
      Account acc = accOpt.orElseThrow(NotFoundException::new);

      acc.setBirthday(input.getBirthday());
      acc.setEmail(input.getEmail());
      acc.setFirstName(input.getFirstName());
      acc.setLastName(input.getLastName());
      acc.setEmail(input.getEmail());
      accountService.saveAccount(acc);

      return ResponseEntity.accepted().build();
  }
}
