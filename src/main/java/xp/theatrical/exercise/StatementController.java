package xp.theatrical.exercise;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

@Controller
class StatementController {

    @Autowired
    private JdbcTemplate template;

    @RequestMapping(value = "/statement",
            method = RequestMethod.GET)
    @ResponseBody
    public String plaintext(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        var totalAmount = 0;
        var volumeCredits = 0;
        String customer = "BigCo";

        File file = new ClassPathResource("performanceCheapExcel.txt").getFile();
        String fileContent = Files.toString(file, Charsets.UTF_8);

        List<Performance> performances = fileContent.lines().dropWhile(l -> !l.startsWith(customer)).takeWhile(l -> l.startsWith(customer)).map(l -> l.split(", "))
                .map(a -> Performance.builder().playID(a[1]).audience(Integer.parseInt(a[2])).build()).collect(Collectors.toList());
        Invoice invoice = new Invoice(customer, performances);
        var result = String.format("Statement for %s\n", invoice.customer);

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (var perf : invoice.performances) {
            var play = Maps.uniqueIndex(template.query("SELECT * FROM plays", (rs, rowNum) -> new Play(rs.getString("name"), rs.getString("type"))), this::getPlayStringFunction).get(perf.playID);
            var thisAmount = 0;

            switch (play.type) {
                case "tragedy":
                    thisAmount = 40000;
                    if (perf.audience > 30) {
                        thisAmount += 1000 * (perf.audience - 30);
                    }
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (perf.audience > 20) {
                        thisAmount += 10000 + 500 * (perf.audience - 20);
                    }
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
            result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience);
            totalAmount += thisAmount;
        }

        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);

        response.setCharacterEncoding("UTF-8");
        return result;
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
