package markciurea.ps_server.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"cause", "stack_trace", "suppressed", "localized_message", "stackTrace", "localizedMessage"})
public class CustomError extends RuntimeException {

    private HttpStatus code;

    private String message;

}
