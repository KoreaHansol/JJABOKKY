package board.website.errorHandler;

import board.website.customStatus.CustomStatusCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@ControllerAdvice
public class ControllerErrorHandler {
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> constraintHandler(SQLIntegrityConstraintViolationException e, HttpServletRequest request) {
        log.error("error = {}", e.getMessage());
        return ResponseEntity.status(CustomStatusCode.DUPLICATION).body("중복된 내용이 있습니다.");
    }
}
