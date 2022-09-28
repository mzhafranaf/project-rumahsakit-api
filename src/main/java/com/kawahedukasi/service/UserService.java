package com.kawahedukasi.service;

import com.kawahedukasi.model.User;
import com.kawahedukasi.util.DecryptUtil;
import com.kawahedukasi.util.TokenUtil;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class UserService {
    @Transactional
    public User persistResponse(String name, String username, String password, String email, String userType){
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(BcryptUtil.bcryptHash(password));
        user.setEmail(email);
        user.setUserType(userType);

        user.persist();

        return user;
    }

    public Response registration(JsonObject request) {
        String name = request.getString("name");
        String username = request.getString("username");
        String password = request.getString("password");
        String email = request.getString("email");
        String usertype = request.getString("userType");

        User user = persistResponse(name, username, password, email, usertype);

        String token = TokenUtil.generate(user);

        JsonObject response = new JsonObject();
        response.put("code", "200");
        response.put("status", "registration success");
        response.put("data", user);
        response.put("token", token);

        return Response.ok().entity(response.getMap()).build();
    }

    public Response login(JsonObject request) throws Exception {
        String username = request.getString("username");
        String password = request.getString("password");

        Optional<User> userOptional = User.find("username = ?1", username).firstResultOptional();
        if(userOptional.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("USER_NOT_FOUND").build();
        }

        User user = userOptional.get();
//        if(!user.getPassword().equals(BcryptUtil.bcryptHash(password))) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("AUTH_FAILED").build();
//        }
        String bcryptPassword = user.getPassword();
        if(!DecryptUtil.verifyBcryptPassword(bcryptPassword, password)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("AUTH_FAILED").build();
        }

        String token = TokenUtil.generate(user);

        JsonObject response = new JsonObject();
        response.put("code", "200");
        response.put("status", "login success");
        response.put("data", user);
        response.put("token", token);

        return Response.ok().entity(response.getMap()).build();

    }

}
