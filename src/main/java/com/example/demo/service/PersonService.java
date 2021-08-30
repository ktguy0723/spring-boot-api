package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

// @Componentとおなじ
@Service
public class PersonService {
  
  private final PersonDao personDao;

  // @Autowired: コンストラクタインジェクションを使用するときにつける（4.3移行省略可）
  // https://reasonable-code.com/spring-injection-method/
  // @Qualifierは、フィールドにautowiredするBeanを意味します
  // https://ja.getdocs.org/spring-spring-autowiring-qualifier-example/
  @Autowired
  public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
    this.personDao = personDao;
  }
  
  public int addPerson(Person person) {
    return personDao.insertPerson(person);
  }

  public List<Person> getAllPeople() {
    return personDao.selectAllPeople();
  }
}
