package br.com.deveficiente.cdc.shared.exceptions;

import java.time.Instant;

public record StandardError(Instant errorMoment,
                            Integer status,
                            String error,
                            String path,
                            String message) {}
