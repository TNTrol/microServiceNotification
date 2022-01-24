package ru.redcollar.notification.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import ru.redcollar.notification.domain.Role;
import ru.redcollar.notification.domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Component
public class JwtConverter {

    public User parseJwt(String token) {
        try {
            Claims body = Jwts.parser()
                    .parseClaimsJws(token)
                    .getBody();

            User user = new User();
            user.setLogin(body.getSubject());
            var a  = (ArrayList<LinkedHashMap>) body.get("role");
            user.setRoles(a.stream().map( s -> new Role((String) s.get("name"))).collect(Collectors.toList()));
            return user;
        } catch (JwtException | ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

}
