package xp.theatrical.exercise;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Performance {

    public String playID;
    private int audience;
    private LocalDate date;

}
