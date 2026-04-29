package com.github.datkatsu.mediatracker.exception;

public class MediaEntryNotFoundException extends RuntimeException
{
    public MediaEntryNotFoundException(Long id)
    {
        super("MediaEntry not found with id: " + id);
    }
}
