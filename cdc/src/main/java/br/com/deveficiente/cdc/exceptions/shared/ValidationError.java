package br.com.deveficiente.cdc.exceptions.shared;

import java.time.Instant;
import java.util.List;

public record ValidationError (Instant errorMoment,
                               Integer status,
                               String error,
                               String path,
                               String message,
                               List<FieldMessage> fieldMessages) {}
