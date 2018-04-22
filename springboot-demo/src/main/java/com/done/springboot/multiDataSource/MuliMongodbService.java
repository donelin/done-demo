package com.done.springboot.multiDataSource;

import com.done.springboot.multiDataSource.model.repository.PrimaryMongoObject;
import com.done.springboot.multiDataSource.model.repository.SecondaryMongoObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Done Lin on 2018/4/22.
 */
@Service
public class MuliMongodbService {

    @Autowired
    @Qualifier("primaryMongoTemplate")
    private MongoTemplate primaryRepository;

    @Autowired
    @Qualifier("secondaryMongoTemplate")
    private MongoTemplate secondaryRepository;

    public void save() {

        System.out.println("************************************************************");
        System.out.println("测试开始");
        System.out.println("************************************************************");

        this.primaryRepository
                .save(new PrimaryMongoObject(null, "第一个库的对象"));

        this.secondaryRepository
                .save(new SecondaryMongoObject(null, "第二个库的对象"));

        List<PrimaryMongoObject> primaries = this.primaryRepository.findAll(PrimaryMongoObject.class);
        for (PrimaryMongoObject primary : primaries) {
            System.out.println(primary.toString());
        }

        List<SecondaryMongoObject> secondaries = this.secondaryRepository.findAll(SecondaryMongoObject.class);

        for (SecondaryMongoObject secondary : secondaries) {
            System.out.println(secondary.toString());
        }

        System.out.println("************************************************************");
        System.out.println("测试完成");
        System.out.println("************************************************************");
    }
}


