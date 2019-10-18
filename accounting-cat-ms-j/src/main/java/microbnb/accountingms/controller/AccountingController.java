package microbnb.accountingms.controller;

import lombok.RequiredArgsConstructor;
import microbnb.accountingms.model.dto.AccountInput;
import microbnb.accountingms.model.entity.Account;
import microbnb.accountingms.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(
    value = "/v1/accounts",
    consumes = "application/json",
    produces = "application/json")
public class AccountingController {

  private final AccountService accountService;

  @GetMapping
  public ResponseEntity getAccounts() {
    return ResponseEntity.ok(accountService.getAccounts());
  }

  @GetMapping("/{id}")
  public ResponseEntity getAccount(@PathVariable long id) {
    return ResponseEntity.of(accountService.getAccount(id));
  }

  @PostMapping
  public ResponseEntity createAccount(AccountInput input) {
    var acc = new Account();
    acc.setBirthday(input.getBirthday());
    acc.setEmail(input.getEmail());
    acc.setFirstName(input.getFirstName());
    acc.setLastName(input.getLastName());
    acc.setEmail(input.getEmail());
    var account = accountService.saveAccount(acc);
    return ResponseEntity.created(URI.create("http://localhost:8080/" + account.getId())).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity removeAccount(@PathVariable long id) {
    accountService.remove(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity updateAccount(@PathVariable long id, AccountInput input) {
    var accOpt = accountService.getAccount(id);
    if (accOpt.isPresent()) {
      var acc = accOpt.get();

      acc.setBirthday(input.getBirthday());
      acc.setEmail(input.getEmail());
      acc.setFirstName(input.getFirstName());
      acc.setLastName(input.getLastName());
      acc.setEmail(input.getEmail());
      accountService.saveAccount(acc);

      return ResponseEntity.accepted().build();
    }

    return ResponseEntity.notFound().build();
  }
}
