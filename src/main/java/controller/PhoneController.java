package controller;

import model.PhoneNumber;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PhoneController {

    private static Logger logger = LogManager.getLogger(PhoneController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView loadForm() {
        logger.trace("Go into loadForm()");
        ModelAndView form = null;
        PhoneNumber phoneNumber;
        try {
            phoneNumber = new PhoneNumber();
            form = new ModelAndView("index");
            form.addObject("phoneNumber", phoneNumber);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit loadForm()");
        return form;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView result(@Valid @ModelAttribute("phoneNumber") PhoneNumber number, BindingResult bindingResult) {
        logger.trace("Go into result()");
        logger.info(number.getNumber());
        ModelAndView result = null;
        PhoneNumber phoneNumber;
        try {
            phoneNumber = new PhoneNumber();
            phoneNumber.validate(number, bindingResult);
            if (bindingResult.hasFieldErrors()) {
                result = new ModelAndView("index");
            } else {
                result = new ModelAndView("result");
                phoneNumber.setNumber(number.getNumber());
                result.addObject("phoneNumber", phoneNumber);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.trace("Exit result()");
        return result;
    }
}
