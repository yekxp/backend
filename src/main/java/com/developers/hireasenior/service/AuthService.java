package com.developers.hireasenior.service;

import com.developers.hireasenior.dto.request.LoginRequest;
import com.developers.hireasenior.dto.response.AuthenticationResponse;
import com.developers.hireasenior.exception.InvalidCredentialsException;
import com.developers.hireasenior.exception.TitleNotAllowedException;
import com.developers.hireasenior.model.Role;
import com.developers.hireasenior.model.Title;
import com.developers.hireasenior.model.VerifyEmail;
import com.developers.hireasenior.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.developers.hireasenior.dto.request.RegistrationRequest;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.RegistrationResponse;
import com.developers.hireasenior.exception.EmailAlreadyExistsException;
import com.developers.hireasenior.mapper.AccountMapper;
import com.developers.hireasenior.model.Account;

import javax.security.auth.login.AccountNotFoundException;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MailService mailService;
    private final VerificationService verificationService;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Transactional
    public ApiResponse<RegistrationResponse> register(RegistrationRequest request) {
        try {
            Account foundAccount = accountRepository.findByEmail(request.getEmail());
            if (foundAccount != null) {
                throw new EmailAlreadyExistsException("Email already exists.");
            }

            String ecryptedPassword = passwordEncoder.encrypt(request.getPassword());
            Account newAccount = new Account();
            newAccount.setFirstName(request.getFirstName());
            newAccount.setTitle(request.getTitle());
            newAccount.setEmail(request.getEmail());
            newAccount.setPassword(ecryptedPassword);
            newAccount.setRole(Role.USER);
            newAccount.setCurrency(request.getCurrency());
            newAccount.setHourlyPrice(request.getHourlyPrice());
            newAccount.setTechnologies(request.getTechnologies());
            newAccount.setLanguagesSpoken(request.getLanguagesSpoken());
            newAccount.setAvailablePeriod(request.getAvailablePeriod());
            newAccount.setDateOfBirth(request.getDateOfBirth());

            Account account = accountRepository.save(newAccount);
            logger.info("Account registered successfully: {}", account.getFirstName());
            VerifyEmail verifyEmail = verificationService.createVerification(account.getEmail());
            mailService.sendVerificationMail(request.getEmail(), verifyEmail.getToken());
            return new ApiResponse<>(true, new RegistrationResponse(accountMapper.toDto(account)),
                    "Account created successfully.");
        } catch(EmailAlreadyExistsException e) {
            logger.error("Email already exists: " + request.getEmail());
            return new ApiResponse<>(false, e.getMessage());
        } catch (Exception e) {
            logger.error("An error occurred while registering: " + e.getMessage());
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
            logger.error("Login failed, invalid credentials: " + loginRequest.getEmail());
            throw new InvalidCredentialsException("Invalid credentials.");
        } catch (Exception e) {
            logger.error("Login failed with an unknown reason: " + e.getMessage());
            throw new Exception("Login failed: " + e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);
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
        authenticationProvider.setUserDetailsService(AuthService.this);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    public Account doesTokenHaveTitleOf(String token, Title title) throws AccountNotFoundException, TitleNotAllowedException {
        String requesterId = jwtService.extractAccountId(token.replace("Bearer ", ""));
        Account account = findAccountById(requesterId);
        if(!(account.getTitle() == title)) {
            throw new TitleNotAllowedException("Only " + title + " can execute this operation.");
        }
        return account;
    }


}
