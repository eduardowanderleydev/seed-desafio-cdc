package br.com.deveficiente.cdc.exceptions.shared;

import java.time.Instant;

public record StandardError(Instant errorMoment,
                            Integer status,
                            String error,
                            String path,
                            String message) {}
