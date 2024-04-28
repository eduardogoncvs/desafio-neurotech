package gateway.api.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.Calendar;

@Data
@Builder
public class ErrorResponse {

    private Integer status;
    private String error;
    private String message;
}
