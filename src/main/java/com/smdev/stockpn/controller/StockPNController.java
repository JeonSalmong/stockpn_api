package com.smdev.stockpn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StockPNController", urlPatterns = "/stockpn")
public class StockPNController extends HttpServlet {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StockPNController.service");

        String sClPn = request.getParameter("pn");
        String sKey = request.getParameter("key");
        System.out.println("sClPn = " + sClPn);
        System.out.println("sKey = " + sKey);

        String query = "";

        query = "select * "
                + "from (select a.date_,            "
                + "        a.code_     as code,     "
                + "        a.name_     as name,     "
                + "        max(pn_)       pn,       "
                + "        max(ratio_)    ratio,    "
                + "        count(*)       cnt,      "
                + "    max(a.key_) as key_code      "
                + " from prediction_pn a            "
                + " where 1 = 1                     "
                + " and pn_ in ('P', 'C')           "
                + " and date_ = to_char(sysdate, 'YYYY-MM-DD')  "
                + " group by a.date_, code_, name_  "
                + " order by date_ desc, ratio desc "
                + " )  where rownum <= 10           ";

        List resultList = jdbcTemplate.queryForList(query);


        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Connection", "close");

        //response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(resultList.toString());
    }
}
