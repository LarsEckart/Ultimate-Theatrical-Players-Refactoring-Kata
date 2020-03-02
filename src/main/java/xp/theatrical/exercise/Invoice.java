package xp.theatrical.exercise;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class Invoice {

    private String customer;
    private List<Performance> performances;

    public Invoice(String customer) throws IOException {
        this.customer = customer;
        File file = new ClassPathResource("CheapExcel.txt").getFile();
        // no input streams, guava rulezzzz
        // deprecated, what now? javadoc says "This method is scheduled to be removed in October 2019"!
        String fileContent = Files.toString(file, Charsets.UTF_8);

        var performances = fileContent.lines().dropWhile(l -> !l.startsWith(customer)).takeWhile(l -> l.startsWith(customer)).map(l -> l.split(", "))
                .map(a -> Performance.builder().playID(a[1]).audience(Integer.parseInt(a[2])).build()).collect(Collectors.toList());
        this.performances = performances;
    }
}
