package ru.duzhinsky.lockbox.domain.response;

import java.util.Optional;

public interface RequestStatus {

    Optional<ErrorMessage> getError();

    Optional<String> getResponse();
}
