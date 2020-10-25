/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboards")
public class DashboardsController {
    
	@RequestMapping(value= {"","/","/index"})
    public String index(Model model) {
        model.addAttribute("attribute", "value");
        return "dashboards/index";
    }
    

    
}
