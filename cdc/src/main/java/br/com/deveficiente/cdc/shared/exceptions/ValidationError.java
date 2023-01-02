package br.com.deveficiente.cdc.shared.exceptions;

import java.time.Instant;
import java.util.List;

public record ValidationError (Instant errorMoment,
                               Integer status,
                               String error,
                               String path,
                               String message,
                               List<FieldMessage> fieldMessages) {}
