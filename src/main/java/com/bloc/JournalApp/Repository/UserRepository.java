package com.bloc.JournalApp.Repository;

import com.bloc.JournalApp.entity.JournalEntry;
import com.bloc.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);

    void deleteByUserName(String username);
}
