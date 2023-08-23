package com.jimicloud.dgs.dgsdemo.repo;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
public record Review(String id, Integer starScore) {

}
