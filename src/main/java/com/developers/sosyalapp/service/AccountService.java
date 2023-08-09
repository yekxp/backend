package com.developers.sosyalapp.service;

import java.util.List;

import com.developers.sosyalapp.dto.request.LoginRequest;
import com.developers.sosyalapp.dto.response.AuthenticationResponse;
import com.developers.sosyalapp.exception.InvalidCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.developers.sosyalapp.dto.request.CreateAccountRequest;
import com.developers.sosyalapp.dto.response.ApiResponse;
import com.developers.sosyalapp.dto.response.CreateAccountResponse;
import com.developers.sosyalapp.exception.EmailOrUsernameAlreadyExistsException;
import com.developers.sosyalapp.mapper.AccountMapper;
import com.developers.sosyalapp.model.Account;
import com.developers.sosyalapp.repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final MailService mailService;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper,
                          PasswordEncoder passwordEncoder, JwtService jwtService, MailService mailService) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.mailService = mailService;
    }

    public ApiResponse<CreateAccountResponse> createAccount(CreateAccountRequest request) {
        try {
            List<Account> foundAccounts = accountRepository.findByEmailOrUsername(request.getAccount().getEmail(),
                    request.getAccount().getUsername());
            if (!foundAccounts.isEmpty()) {
                throw new EmailOrUsernameAlreadyExistsException("Email or username already exists.");
            }

            String ecryptedPassword = passwordEncoder.encrypt(request.getAccount().getPassword());
            Account newAccount = accountMapper.toEntity(request.getAccount());
            newAccount.setPassword(ecryptedPassword);

            Account account = accountRepository.save(newAccount);
            mailService.sendMail(request.getAccount().getEmail());
            return new ApiResponse<>(true, new CreateAccountResponse(accountMapper.toDto(account)),
                    "Account created successfully.");
        } catch(EmailOrUsernameAlreadyExistsException e) {
            return new ApiResponse<>(false, e.getMessage());
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Account could not be created.");
        }
    }

    public ApiResponse<AuthenticationResponse> login(LoginRequest loginRequest) throws Exception {
        try {
            Account account = accountRepository.findByEmail(loginRequest.getEmail());
            if(!passwordEncoder.matches(loginRequest.getPassword(), account.getPassword())) {
                throw new InvalidCredentialsException("Email ya da şifre yanlış.");
            }
            AuthenticationResponse authResponse = jwtService.generateToken(account);
            return new ApiResponse<>(true, authResponse ,"Login successful.");
        } catch (InvalidCredentialsException e) {
            throw new InvalidCredentialsException("Invalid credentials.");
        } catch (Exception e) {
            throw new Exception("Login failed: " + e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("Account not found.");
        }

        return account;
    }
}
