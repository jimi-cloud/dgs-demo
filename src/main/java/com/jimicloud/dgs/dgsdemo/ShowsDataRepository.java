package com.jimicloud.dgs.dgsdemo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowsDataRepository<Show, String> extends MongoRepository<Show, String> {
}
