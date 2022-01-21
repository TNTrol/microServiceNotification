package ru.redcollar.notification.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import ru.redcollar.notification.domain.Role;
import ru.redcollar.notification.domain.User;

import java.io.IOException;

@Component
public class JwtConverter {

    public User parseJwt(String token) {
        try {
            String[] split_string = token.split("\\.");
            String base64EncodedBody = split_string[1];
            Base64 base64Url = new Base64(true);
            String body = new String(base64Url.decode(base64EncodedBody));

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(body.getBytes());
            User user = new User();
            user.setLogin(node.path("preferred_username").textValue());
            node.path("role").elements().forEachRemaining(s->user.getRoles().add( new Role(s.textValue())));
            return user;
        } catch (JwtException | ClassCastException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
