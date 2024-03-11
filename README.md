* Spring Security + JWT
  * Only one registered user, information in `application.properties`
  * `config.SecurityConfig`: defines beans required for authentication and filter chain. Disabled csrf and added a JwtFilter in the filter chain.
  * `controller.LoginController`: defines `/login` as an entrypoint for authentication. Successful authentication will return a JWT back to the client.
  * `filter.JwtFilter`: for any request other than `/login`, the filter adds authentication information to `SecurityContextHolder` if its JWT can be validated. `/login` passes the filter.
  * `pojo.User`: an object that wraps up username and password
  * `sevice.impl.JwtUserDetailsService`: implements `UserDetails` which is needed in `JwtFilter`.
  * `util.JwtUtil`: utilities such as generate and validate JWTs.