package com.hu.vbm672;

import com.hu.vbm672.appuser.AppUserRole;
import com.hu.vbm672.appuser.AppUserService;
import com.hu.vbm672.appuser.role.request.RoleRequest;
import com.hu.vbm672.appuser.role.request.RoleRequestService;
import com.hu.vbm672.estate.*;
import com.hu.vbm672.registration.RegistrationRequest;
import com.hu.vbm672.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller // TODO RestController
@AllArgsConstructor
public class AppController {

    private final RegistrationService registrationService;
    private final RealEstateService realEstateService;
    private final AppUserService appUserService;
    private final RoleRequestService roleRequestService;

    @GetMapping("/")
    public String indexPage() {
        return "index.xhtml";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup.html";
    }


    @PostMapping("/signup")
    public String register(@ModelAttribute RegistrationRequest request) {
        registrationService.register(request);
        return "redirect:/login";
    }

    @GetMapping("/welcome")
    public ModelAndView welcomePage(@AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = commonForWelcome(userDetails);
        return modelAndView;
    }

    @PostMapping("/welcome/estate/post")
    public ModelAndView addEstate(@ModelAttribute RealEstate realEstate, @AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = commonForWelcome(userDetails);
        realEstateService.add(realEstate);
        return modelAndView;
    }

    @GetMapping("/welcome/estate")
    public ModelAndView searchEstate(@RequestParam("realEstateAddress") RealEstateAddress address, @RequestParam("realEstateType") RealEstateType type, @AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = commonForWelcome(userDetails);
        modelAndView.addObject("SearchList",realEstateService.search(address, type));
        return modelAndView;
    }

    @PostMapping("/welcome/estate/delete")
    public ModelAndView deleteEstate(@RequestParam("id") long id, @AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = commonForWelcome(userDetails);
        realEstateService.delete(id);
        return modelAndView;
    }

    @PostMapping("/welcome/role/add")
    public ModelAndView addRoleRequest(@RequestParam AppUserRole appUserRole, @AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = commonForWelcome(userDetails);
        RoleRequest roleRequest = new RoleRequest(userDetails.getUsername(),appUserRole);
        roleRequestService.add(roleRequest);
        return modelAndView;
    }

    @GetMapping("/welcome/role")
    public ModelAndView searchRoleRequest(@AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = commonForWelcome(userDetails);
        modelAndView.addObject("RoleList",roleRequestService.search());
        return modelAndView;
    }

    @PostMapping("/welcome/role/approve")
    public ModelAndView approveRoleRequest(@RequestParam("requestId") long id, @AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = commonForWelcome(userDetails);
        RoleRequest roleRequest = roleRequestService.fetch(id);
        if(userDetails.getAuthorities().iterator().next().getAuthority() != roleRequest.getAppUserRole().toString()) {
            appUserService.updaterole(roleRequest);
            roleRequestService.delete(roleRequest);
        }
        return modelAndView;

    }

    public ModelAndView commonForWelcome(UserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("SearchList", null);
        modelAndView.addObject("RoleList", null);
        modelAndView.setViewName("welcome");
        modelAndView.addObject("AppUser", userDetails);
        modelAndView.addObject("role", userDetails.getAuthorities().iterator().next().getAuthority());
        return modelAndView;
    }

    /*
    // TODO mail verification
    @GetMapping(path = "register/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
     */
}
