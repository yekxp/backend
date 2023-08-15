package com.developers.sosyalapp.service;

import java.util.List;

import com.developers.sosyalapp.dto.request.LoginRequest;
import com.developers.sosyalapp.dto.response.AuthenticationResponse;
import com.developers.sosyalapp.exception.InvalidCredentialsException;
import com.developers.sosyalapp.model.AccountProperties;
import com.developers.sosyalapp.model.VerifyEmail;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.developers.sosyalapp.dto.request.CreateAccountRequest;
import com.developers.sosyalapp.dto.response.ApiResponse;
import com.developers.sosyalapp.dto.response.CreateAccountResponse;
import com.developers.sosyalapp.exception.EmailOrUsernameAlreadyExistsException;
import com.developers.sosyalapp.mapper.AccountMapper;
import com.developers.sosyalapp.model.Account;
import com.developers.sosyalapp.repository.AccountRepository;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MailService mailService;
    private final VerificationService verificationService;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper,
                          PasswordEncoder passwordEncoder, JwtService jwtService, MailService mailService, VerificationService verificationService) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.mailService = mailService;
        this.verificationService = verificationService;
    }

    @Transactional
    public ApiResponse<CreateAccountResponse> createAccount(CreateAccountRequest request) {
        try {
            List<Account> foundAccounts = accountRepository.findByEmailOrUsername(request.getEmail(),
                    request.getUsername());
            if (!foundAccounts.isEmpty()) {
                throw new EmailOrUsernameAlreadyExistsException("Email or username already exists.");
            }

            String ecryptedPassword = passwordEncoder.encrypt(request.getPassword());
            Account newAccount = new Account();
            newAccount.setUsername(request.getUsername());
            newAccount.setEmail(request.getEmail());
            newAccount.setPassword(ecryptedPassword);

            AccountProperties accountProperties = new AccountProperties();
            newAccount.setAccountProperties(accountProperties);

            Account account = accountRepository.save(newAccount);
            VerifyEmail verifyEmail = verificationService.createVerification(account.getEmail());
            mailService.sendVerificationMail(request.getEmail(), verifyEmail.getToken());
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

    public Account findAccountById(String accountId) throws AccountNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found."));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(AccountService.this);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }


}
