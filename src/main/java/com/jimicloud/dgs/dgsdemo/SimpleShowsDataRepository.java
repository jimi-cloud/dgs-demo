package com.jimicloud.dgs.dgsdemo;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public class SimpleShowsDataRepository<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements ShowsDataRepository<T, ID> {
    public SimpleShowsDataRepository(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }
}
