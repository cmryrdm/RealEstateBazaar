package com.hu.vbm672.appuser;

import com.hu.vbm672.appuser.role.request.RoleRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //private final ConfirmationTokenService confirmationTokenService; // TODO token

    //load user by email (UserDetailsService is implemented therefore email corresponds to username.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(
                String.format(USER_NOT_FOUND_MSG, email)));
    }

    // Get all users
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    //sign up user
    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if(userExists) {
            //TODO check of attributes are the same and
            //TODO if email not confirmed send confirmation mail.
            throw new IllegalStateException("email already taken!");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
        /*
        //TODO: Send confirmation token -> DONE
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        //TODO: send email
        return token;
         */
        return "you have signed up successfully!";
    }

    public AppUser fetch(RoleRequest roleRequest) {
        return appUserRepository.findByEmail(roleRequest.getEmail()).orElseThrow(()->new IllegalStateException(
                String.format("Role request not found!")));
    }

    public void updaterole(RoleRequest roleRequest) {
        AppUser appUser = fetch(roleRequest);
        appUser.setAppUserRole(roleRequest.getAppUserRole());
    }

    /*
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
     */
}
