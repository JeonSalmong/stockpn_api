package com.smdev.stockpn.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.smdev.stockpn.service.StockPNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StockPNController {
    @Autowired
    StockPNService service;

    @RequestMapping("/stockpn")
    public ModelAndView stockpn(HttpServletRequest request) throws JsonProcessingException {
        String sClPn = request.getParameter("pn");
        String sKey = request.getParameter("key");
        Map<String, Object> params = new HashMap<>();

        List<Map<String, Object>> resultList = null;

        if ("P".equals(sClPn)) {
            resultList = service.getPos(params);
        } else if ("N".equals(sClPn)) {
            resultList = service.getNeg(params);
        } else if ("UP".equals(sClPn)) {
            params.put("PN", "P");
            resultList = service.getUSA(params);
        } else if ("UN".equals(sClPn)) {
            params.put("PN", "N");
            resultList = service.getUSA(params);
        } else if ("DEKO".equals(sClPn)) {
            params.put("KEY", sKey);
            resultList = service.getDetail(params);
        }
        else {
            resultList = null;
        }

        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        mv.addObject("StockPN", resultList);

        return mv;
    }

}
