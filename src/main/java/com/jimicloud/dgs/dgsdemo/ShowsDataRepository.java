package com.jimicloud.dgs.dgsdemo;

import com.jimicloud.dgs.dgsdemo.repo.Show;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowsDataRepository extends MongoRepository<Show, String> {
}
