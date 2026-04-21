package com.github.datkatsu.mediatracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MediaEntryNotFoundException extends RuntimeException
{
    public MediaEntryNotFoundException(Long id)
    {
        super("MediaEntry not found with id: " + id);
    }
}
