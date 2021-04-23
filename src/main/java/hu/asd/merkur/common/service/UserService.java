package hu.asd.merkur.common.service;

import org.springframework.stereotype.Service;

import hu.asd.merkur.common.persist.entity.QUser;
import hu.asd.merkur.common.persist.entity.User;
import hu.asd.merkur.common.persist.repository.UserRepository;
import hu.asd.merkur.core.service.ServiceParent;

@Service
public class UserService extends ServiceParent<User, QUser, UserRepository> {

}
