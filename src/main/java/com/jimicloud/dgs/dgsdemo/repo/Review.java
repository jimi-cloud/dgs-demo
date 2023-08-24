package com.jimicloud.dgs.dgsdemo.repo;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Review(@NotNull Integer starScore) {}