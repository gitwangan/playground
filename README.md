* CRUD of Employee and Department
    * Employee: Spring Data JPA is used
    * `@Query` used on `R`
    * `@Modifying` and `@Query` used for `D`
    * Department: JPA Entity Manager + Hibernate is used
      * Dynamic query building used for `R`
      * JPQL used for `D` to avoid two queries if deleting by `remove(find())`
    * `@Transactional` used on `CUD`



* Remove duplicate rows in table
  * w/ primary key: 
    * Using inner join: `mysql> delete e1 from employee e1, employee e2 where e1.name = e2.name and e1.salary = e2.salary and e1.id > e2.id;`
    * Using partition by: `mysql> delete from employee where id in (select id from (select id, row_number() over(partition by name, salary) row_num from employee) e where e.row_num > 1);
      `
  * w/o primary key:
    * Using inner join + partition by: `mysql> delete e1 from employee e1, (select *, row_number() over(partition by name, salary) row_id from employee) e2 where e1.name = e2.name and e1.salary = e2.salary and e2.row_id > 1;
      `


* Spring Security + JWT
  * Only one registered user, information in `application.properties`
  * `config.SecurityConfig`: defines beans required for authentication and filter chain. Disabled csrf and added a JwtFilter in the filter chain.
  * `controller.LoginController`: defines `/login` as an entrypoint for authentication. Successful authentication will return a JWT back to the client.
  * `filter.JwtFilter`: for any request other than `/login`, the filter adds authentication information to `SecurityContextHolder` if its JWT can be validated. `/login` passes the filter.
  * `pojo.User`: an object that wraps up username and password
  * `sevice.impl.JwtUserDetailsService`: implements `UserDetails` which is needed in `JwtFilter`.
  * `util.JwtUtil`: utilities such as generate and validate JWTs.