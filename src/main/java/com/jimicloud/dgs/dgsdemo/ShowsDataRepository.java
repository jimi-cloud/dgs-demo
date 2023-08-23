package com.jimicloud.dgs.dgsdemo;

import com.netflix.dgs.codegen.generated.types.Show;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowsDataRepository extends MongoRepository<Show, Integer> {}
