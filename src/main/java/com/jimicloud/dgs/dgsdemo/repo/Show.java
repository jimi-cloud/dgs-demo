package com.jimicloud.dgs.dgsdemo.repo;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Builder
public class Show {
    private String id;

    private String title;

    private List<Review> reviews;

}
