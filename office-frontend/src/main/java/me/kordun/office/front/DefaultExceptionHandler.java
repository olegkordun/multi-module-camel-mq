package me.kordun.office.front;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class DefaultExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger("DefaultExceptionHandler");

    @ExceptionHandler({MissingServletRequestParameterException.class,
            UnsatisfiedServletRequestParameterException.class,
            HttpRequestMethodNotSupportedException.class,
            ServletRequestBindingException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Map<String, Object> handleRequestException(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Request Error");
        map.put("cause", ex.getMessage());
        return map;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Map<String, Object> handleValidationException(ConstraintViolationException ex) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Validation Failure");
        map.put("violations", convertConstraintViolation(ex.getConstraintViolations()));
        return map;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Validation Failure");
        map.put("violations", convertConstraintViolation(ex));
        return map;
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Map<String, Object> handleTypeException(TypeMismatchException ex) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Type Mismatch ");
        map.put("value", ex.getValue());
        if (ex.getRequiredType().isEnum()) {
            map.put("requiredType", ex.getRequiredType().getEnumConstants());
        } else {
            map.put("requiredType", ex.getRequiredType());
        }

        return map;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Map<String, Object> handleTypeException(HttpMessageNotReadableException ex) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Type Mismatch ");
            map.put("cause", ex.getCause().getLocalizedMessage());

        return map;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public
    @ResponseBody
    Map<String, Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Data Integrity Error");
        map.put("cause", ex.getCause().getCause().getLocalizedMessage());
        return map;
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    Map<String, Object> handleDataAccessException(DataAccessException ex) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Data Error");
        map.put("cause", ex.getCause().getMessage());
        return map;
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public
    @ResponseBody
    Map<String, Object> handleUnsupportedMediaTypeException(HttpMediaTypeNotSupportedException ex) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Unsupported Media Type");
        map.put("cause", ex.getLocalizedMessage());
        map.put("supported", ex.getSupportedMediaTypes());
        return map;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    Map<String, Object> handleUncaughtException(Exception ex) throws IOException {
        log.error("Exception", ex);
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Unknown Error");
        if (ex.getCause() != null) {
            map.put("cause", ex.getCause().getMessage());
        } else {
            map.put("cause", ex.getMessage());
        }
        return map;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public
    @ResponseBody
    Map<String, Object> handleUncaughtException(AccessDeniedException ex) throws IOException {
        log.error("AccessDeniedException", ex);
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Access is denied");
        return map;
    }

    private Map<String, Map<String, Object>> convertConstraintViolation(Set<ConstraintViolation<?>> constraintViolations) {
        Map<String, Map<String, Object>> result = new HashMap<>();
        for (ConstraintViolation constraintViolation : constraintViolations) {
            Map<String, Object> violationMap = new HashMap<>();
            violationMap.put("value", constraintViolation.getInvalidValue());
            violationMap.put("type", constraintViolation.getRootBeanClass());
            violationMap.put("message", constraintViolation.getMessage());
            result.put(constraintViolation.getPropertyPath().toString(), violationMap);
        }
        return result;
    }

    private Map<String, Map<String, Object>> convertConstraintViolation(MethodArgumentNotValidException ex) {
        Map<String, Map<String, Object>> result = new HashMap<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            Map<String, Object> violationMap = new HashMap<>();
            violationMap.put("target", ex.getBindingResult().getTarget());
            violationMap.put("type", ex.getBindingResult().getTarget().getClass());
            violationMap.put("message", error.getDefaultMessage());
            violationMap.put("field", error.getClass().isAssignableFrom(FieldError.class)
                    ? ((FieldError) error).getField() : null);
            result.put(error.getObjectName(), violationMap);
        }
        return result;
    }

}
