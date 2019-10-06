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
@RequestMapping(value = "/account", consumes = "application/json", produces = "application/json")
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
        Account acc = new Account();
        acc.setBirthday(input.getBirthday());
        acc.setEmail(input.getEmail());
        acc.setFirstName(input.getFirstName());
        acc.setLastName(input.getLastName());
        acc.setEmail(input.getEmail());
        var account = accountService.createAccount(acc);
        return ResponseEntity.created(URI.create("http://localhost:8080/" + account.getId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeAccount(@PathVariable long id) {
        return ResponseEntity.noContent().build();
    }
}
