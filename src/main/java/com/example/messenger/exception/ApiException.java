package com.example.messenger.exception;

import lombok.Builder;

import java.time.ZonedDateTime;

/**
 * Клас-обгортка, яка є відповіддю сервера, коли виникає exception.
 * Design Pattern Builder для того, щоб могти в подальшому гнучко створювати екземпляри цього класу шляхом використання
 * класа-білдера, який дозволяє зручно створювати об'єкти даного класу.
 * ApiException apiException = ApiException.builder()
 *                                         .error(param)
 *                                         .message(param)
 *                                         .path(param)
 *                                         .timestamp(param)
 *                                         .build();
 * @param error
 * @param message
 * @param path
 * @param timestamp
 */
@Builder
public record ApiException(
        String error,
        String message,
        String path,
        ZonedDateTime timestamp
) {
}
