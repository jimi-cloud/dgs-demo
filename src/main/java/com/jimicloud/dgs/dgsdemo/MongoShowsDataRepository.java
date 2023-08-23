package com.jimicloud.dgs.dgsdemo;

import com.netflix.dgs.codegen.generated.types.Show;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.stereotype.Repository;

@Repository
public class MongoShowsDataRepository extends SimpleShowsDataRepository<Show, String> {
    public MongoShowsDataRepository(MongoEntityInformation<Show, String> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }
}
