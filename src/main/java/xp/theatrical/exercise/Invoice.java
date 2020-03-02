package xp.theatrical.exercise;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.list.UnmodifiableList;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
/*
 * originally created by Lars, September 2019 ¯\_(ツ)_/¯
 */
public class Invoice {

    private String customer;
    private List<Performance> performances;

    @SuppressWarnings("unchecked")
    public Invoice(String customer) throws IOException {
        this.customer = customer;
        File file = new ClassPathResource("CheapExcel.txt").getFile();
        // cool, no input streams necessary, guava rulezzzz
        String fileContent = Files.toString(file, Charsets.UTF_8);

        var performances = fileContent.lines().dropWhile(l -> !l.startsWith(customer)).takeWhile(l -> l.startsWith(customer)).map(l -> l.split(", "))
                .map(a -> Performance.builder().playID(a[1]).audience(Integer.parseInt(a[2])).build()).collect(Collectors.toList());
        if ( CollectionUtils.isEmpty(performances)) {
            this.performances = UnmodifiableList.decorate(new ArrayList<>());
        } else {
            this.performances = new ArrayList<Performance>(performances);
        }
    }
}
