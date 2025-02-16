package guru.springframework.spring6restmvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor // Crucial: Add this for Jackson
@AllArgsConstructor // Useful for other purposes
@Builder
@Jacksonized // Important for correct deserialization with Builder
public class CustomerDTO {
    private String name;
    private UUID id;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
