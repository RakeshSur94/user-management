package com.um.service.portal.controller;

import com.um.service.portal.dto.*;
import com.um.service.portal.entity.City;
import com.um.service.portal.entity.State;
import com.um.service.portal.entity.UserEntity;
import com.um.service.portal.service.DashBoardService;
import com.um.service.portal.service.UserService;
import com.um.service.portal.service.impl.AddressServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AddressServiceImpl addressService;
    private final DashBoardService dashBoardService;


    @GetMapping("/")
    public String home(){
        return "index.html";
    }
    @GetMapping("/states/{countryId}")
    @ResponseBody
    public List<State> getStatesByCountry(@PathVariable Integer countryId) {
        return addressService.getStateByCountryId(countryId);
    }

    @ResponseBody
    @GetMapping("/cities/{stateId}")
    public List<City> getCitiesByState(@PathVariable Integer stateId) {
        return addressService.getCityByStateId(stateId);
    }
    @GetMapping("/registration")
    public String createUserForm(Model model){
        RegisterFormDTO registerFormDTO = null;
        registerFormDTO = new RegisterFormDTO();
        model.addAttribute("countries",this.addressService.getAllCountry());
        model.addAttribute("register",registerFormDTO);
        return "register";
    }
    @PostMapping("/registration")
    public String userFormProcessing(RegisterFormDTO registerFormDTO, Model model, HttpServletRequest request){
        RegisterFormDTO registered = this.userService.createUser(registerFormDTO);

        if(registered != null){
            model.addAttribute("smessage","User added Successfully, check your mail and reset your password");
        }
        else{
            model.addAttribute("fmessage","No no-user added");
        }
        model.addAttribute("register",registered);
        return "register";


    }
    @GetMapping("/update-page")
    public String updatePasswordFormPage(@RequestParam String email, Model model, HttpServletRequest request){

        ResetPasswordFormDTO resetPassword = null;
        resetPassword = new ResetPasswordFormDTO();
        resetPassword.setEmail(email);

        model.addAttribute("resetpassword",resetPassword);
        return "update";
    }
    @PostMapping("/update-page")
    public String updatePasswordFormPageProcessing(ResetPasswordFormDTO resetPasswordFormDTO,Model model){
        Boolean result = this.userService.resetPassword(resetPasswordFormDTO);
        if(result){

            return "redirect:dashboard";
        }
        else{
            return "update";
        }

    }
    @GetMapping("/login-page")
    public String loginFormPage(Model model){
        LoginFormDTO loginFormDTO = new LoginFormDTO();
        model.addAttribute("loginform",loginFormDTO);
        return "login";
    }
    @PostMapping("/login-page")
    public String loginFormProcessing(LoginFormDTO loginFormDTO,Model model){
        UserDTO login = this.userService.login(loginFormDTO);
        if(login == null){
            model.addAttribute("fmessage","invalid credentials");
        }
        else{
            String passwordResetInfo = login.getPasswordResetInfo();
            if("YES".equals(passwordResetInfo)){
                return "redirect:dashboard";
            }
            else{
                return "redirect:update-page?email="+login.getEmail();
            }


        }

        return "login";
    }
    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        DashboardResponse dashboardResponse = this.dashBoardService.getQuote();
        model.addAttribute("quotes",dashboardResponse);
        return "dashboard";

    }




}
