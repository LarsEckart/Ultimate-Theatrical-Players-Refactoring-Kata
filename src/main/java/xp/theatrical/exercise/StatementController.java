package xp.theatrical.exercise;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
class StatementController {

    @Autowired
    private JdbcTemplate template;

    @RequestMapping(value = "/statement/{cust}", produces={"text/plain"},
            method = RequestMethod.GET)
    @ResponseBody
    public String plaintext(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        var totalAmount = 0;
        var volumeCredits = 0;

        Invoice invoice = new Invoice(request.getServletPath().substring(11));
        var st = String.format("Statement for %s\n", invoice.getCustomer());

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        ArrayList<Performance> performances = invoice.getPerformances();
        int i = 0;
        while (i < performances.size()) {
            Performance perf = performances.get(i);
            var play = Maps.uniqueIndex(template.query("SELECT name, type FROM plays", (rs, rowNum) -> new Play(rs.getString("name"), rs.getString("type"))),
                    this::getPlayStringFunction).get(perf.playID);
            var thisAmount = 0;
            i++;
            switch (play.type) {
                case "tragedy":
                    thisAmount = 40000;
                    if (perf.audience > 30)
                        thisAmount += 1000 * (perf.audience - 30);
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (perf.audience > 20)
                        thisAmount += 10000 + 500 * (perf.audience - 20);
                    thisAmount += 300 * perf.audience;
                    break;
                default:
                    throw new Error("unknown type: ${play.type}");
            }
            // add volume credits
            volumeCredits += Math.max(perf.audience - 30, 0);
            // add extra credit for every ten comedy attendees
            if ("comedy".equals(play.type))
                volumeCredits += Math.floor(perf.audience / 5);
            // print line for this order
            st += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience);
            totalAmount += thisAmount;
        }
        st += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        st += String.format("You earned %s credits\n", volumeCredits);
        response.setCharacterEncoding("UTF-8");
        return st;
    }

    private String getPlayStringFunction(Play p) {
        if (p.name == "Hamlet") {
            return "hamlet";
        } else if (p.name.equals("As You Like It")) {
            return "as-like";
        } else {
            return p.name.toLowerCase();
        }
    }

}
