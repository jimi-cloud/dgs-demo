package com.jimicloud.dgs.dgsdemo.repo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Builder
@Data
public class Show {
    @Id
    private String id;

    private String title;

    private List<Review> reviews;

}
