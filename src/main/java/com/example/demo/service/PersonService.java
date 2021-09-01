package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
  // Autowiredをつけることで、Beanから(@Componentをつけたクラスから)インスタンスを自動で割り当てる
  // 下記の場合、personDaoにPersonDaoクラスで作成したインスタンスを割り当てる
  // https://reasonable-code.com/spring-injection-method/
  // @Qualifierは、フィールドにautowiredするBeanを意味します
  // Autowiredで自動割り当てする際、割り当てられるインスタンスが複数ある場合、
  // 割り当てるインスタンスを明確にするために記載します。
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

  public Optional<Person> getPersonById(UUID id) {
    return personDao.selectPersonById(id);
  }

  public int deletePerson(UUID id) {
    return personDao.deletePersonById(id);
  }

  public int updatePerson(UUID id, Person newPerson) {
    return personDao.updatePersonById(id, newPerson);
  }
}
