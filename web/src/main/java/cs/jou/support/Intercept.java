package cs.jou.support;

import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

public interface Intercept extends HandlerInterceptor {
    default List<String> getPathPattern() {return List.of("/**");}
}
