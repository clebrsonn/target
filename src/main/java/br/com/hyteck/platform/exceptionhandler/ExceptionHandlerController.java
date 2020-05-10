package br.com.hyteck.platform.exceptionhandler;

import lombok.Getter;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        String messageUser = messageSource.getMessage("mensagem.invalida", null,
                LocaleContextHolder.getLocale());
        String messageDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Error> erros = Collections.singletonList(new Error(messageDev, messageUser));

        return handleExceptionInternal(ex, erros, headers, status, request);
    }

    @ExceptionHandler({NoSuchFieldException.class})
    protected ResponseEntity<Object> handleNoSuchFieldException(NoSuchFieldException ex, WebRequest request) {
        String messageUser = messageSource.getMessage("parametro.invalido", null, LocaleContextHolder.getLocale());
        String messageDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Error> erros = Collections.singletonList(new Error(messageDev, messageUser));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        String messageUser = messageSource.getMessage("parametro.duplicado", new String[]{ex.getConstraintName()}, LocaleContextHolder.getLocale());
        String messageDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Error> erros = Collections.singletonList(new Error(messageDev, messageUser));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Error> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String messageUser = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        String messageDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Error> erros = Collections.singletonList(new Error(messageDev, messageUser));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private List<Error> criarListaDeErros(BindingResult bindingResult) {
        List<Error> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.toString();
            erros.add(new Error(mensagemDesenvolvedor, mensagemUsuario));
        }

        return erros;
    }

    @Getter
    public class Error {
        private String messageDeveloper;
        private String messageUser;


        Error(String messageDeveloper, String messageUser) {
            this.messageDeveloper = messageDeveloper;
            this.messageUser = messageUser;
        }

    }

}
