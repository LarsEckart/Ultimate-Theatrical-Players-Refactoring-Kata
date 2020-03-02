package xp.theatrical.exercise;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    public String customer;
    public List<Performance> performances;

    public Invoice(String customer) throws IOException {
        this.customer = customer;
        File file = new ClassPathResource("performanceCheapExcel.txt").getFile();
        // no input streams, guava rulezzzz
        String fileContent = Files.toString(file, Charsets.UTF_8);

        List<Performance> performances = fileContent.lines().dropWhile(l -> !l.startsWith(customer)).takeWhile(l -> l.startsWith(customer)).map(l -> l.split(", "))
                .map(a -> Performance.builder().playID(a[1]).audience(Integer.parseInt(a[2])).build()).collect(Collectors.toList());
        this.performances = performances;
    }
}
