package com.app.group15.UserManagement.ForgetPassword;

import com.app.group15.PasswordPolicyManagement.IPasswordPolicyService;
import com.app.group15.PasswordPolicyManagement.PasswordPolicyValidationResult;
import com.app.group15.Utility.GroupFormationToolLogger;
import com.app.group15.Utility.ServiceUtility;
import com.app.group15.config.ServiceConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.logging.Level;

@Controller
@RequestMapping("/")
public class ForgetPasswordController {


    private IPasswordPolicyService passwordPolicyService;
    private IForgetPasswordService forgetPasswordService = ServiceConfig.getInstance().getForgetPasswordService();
    private String invalidInput = "Invalid invalid";

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    public ModelAndView returnModel() {
        ModelAndView modelAndViewResponse = new ModelAndView();
        modelAndViewResponse.addObject("sent", false);
        modelAndViewResponse.setViewName("forgetPassword");
        return modelAndViewResponse;
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public ModelAndView getUserAndGenerateToken(@RequestParam(required = true, value = "bannerId") String bannerId, HttpServletRequest request) throws UnsupportedEncodingException {

        if (ServiceUtility.isNotNull(bannerId) && ServiceUtility.isNotNull(request)) {
            forgetPasswordService.generateToken(bannerId, request);

            ModelAndView modelAndViewResponse = new ModelAndView();
            modelAndViewResponse.setViewName("forgetPassword");
            modelAndViewResponse.addObject("sent", true);
            modelAndViewResponse.addObject("completed", false);
            modelAndViewResponse.addObject("bannerId_error", "Please enter BannerId");

            return modelAndViewResponse;
        } else {
            GroupFormationToolLogger.log(Level.SEVERE, invalidInput);
            return null;
        }
    }


    @RequestMapping("/auth/validateToken")
    public ModelAndView verifyToken(@RequestParam("to") String token) {
        if (ServiceUtility.isNotNull(token)) {
            boolean validated = false;
            validated = forgetPasswordService.verifyToken(token);

            if (validated) {
                ModelAndView modelAndViewResponse = new ModelAndView();
                modelAndViewResponse.addObject("error", false);
                modelAndViewResponse.addObject("completed", false);
                modelAndViewResponse.setViewName("resetPassword");
                return modelAndViewResponse;
            } else {
                return null;
            }
        } else {
            GroupFormationToolLogger.log(Level.SEVERE, invalidInput);
            return null;
        }

    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ModelAndView changePassword(@RequestParam(required = false, value = "token") String token,
                                       @RequestParam(required = true, value = "password") String password,
          
                                       @RequestParam(required = true, value = "cPassword") String newPassword) {

        if (ServiceUtility.isNotNull(token) && ServiceUtility.isNotNull(password) && ServiceUtility.isNotNull(newPassword)) {

            passwordPolicyService = ServiceConfig.getInstance().getPasswordPolicy();
            Map<String, String> user = forgetPasswordService.getUserFromToken(token);
            PasswordPolicyValidationResult result = passwordPolicyService.validatePassword(password, Integer.parseInt(user.get("id")));

            if (!newPassword.equals(password)) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("resetPassword");
                modelAndView.addObject("error", true);
                modelAndView.addObject("completed", false);
                modelAndView.addObject("token", token);

                modelAndView.addObject("password_error", "Password did not match!");
                return modelAndView;
            } else if (!result.getResult()) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("resetPassword");
                modelAndView.addObject("error", true);
                modelAndView.addObject("completed", false);
                modelAndView.addObject("token", token);
                modelAndView.addObject("password_error", result.isMessage());
                return modelAndView;
            }

            boolean passed = false;
            if (forgetPasswordService.updateUserPassword(Integer.parseInt(user.get("id")), newPassword)) {
                passed = forgetPasswordService.deleteForgotPasswordToken(Integer.parseInt(user.get("id")));
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("resetPassword");
                modelAndView.addObject("error", false);
                modelAndView.addObject("completed", true);
                return modelAndView;
            }
        } else {
            GroupFormationToolLogger.log(Level.SEVERE, invalidInput);
            return null;
        }
        return null;
    }
}
