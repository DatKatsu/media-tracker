package com.github.datkatsu.mediatracker.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Mapper(componentModel = "spring")
public abstract class MalMapper {

    @Value("${mal.site.base-url}")
    protected String malSiteBaseUrl;


}
