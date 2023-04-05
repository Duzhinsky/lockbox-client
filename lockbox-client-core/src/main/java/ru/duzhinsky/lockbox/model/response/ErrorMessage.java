package ru.duzhinsky.lockbox.model.response;

import java.util.List;
import lombok.Value;

@Value
public class ErrorMessage {

    int code;

    String message;

    List<String> details;
}
