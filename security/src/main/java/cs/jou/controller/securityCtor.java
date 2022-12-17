package cs.jou.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class securityCtor {
    private final AuthenticationManager manager;
    public securityCtor(AuthenticationManager manager) {
        this.manager = manager;
    }

    @GetMapping("/r/1")
    public Object r1() {
        return "r1";
    }

    @GetMapping("/r/2")
    public Object r2() {
        return "r2";
    }


    @GetMapping("login")
    public Object login(String usn, String pwd) {
        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(usn, pwd));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return authenticate;
    }

    @GetMapping("/a")
    public Object a() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
