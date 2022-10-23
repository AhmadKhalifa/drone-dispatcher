package com.drone.dispatcher.domain.base;

import com.drone.dispatcher.domain.exception.ErrorResponse;
import com.drone.dispatcher.domain.exception.StatusException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private HttpStatus status;
    private T data;
    private String message;

    public static <T> Response<T> success(T value) {
        return new Response<>(HttpStatus.OK, value, null);
    }

    public static <T> Response<T> failure(StatusException statusException) {
        return new Response<>(statusException.getStatus(), null, statusException.getMessage());
    }

    public ResponseEntity<?> toResponseEntity() {
        return status.is2xxSuccessful() ?
                ResponseEntity.ok(data) :
                ResponseEntity
                        .status(status)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ErrorResponse(message));
    }
}
