package cs.jou.support.enhance.filter;


import cs.jou.support.Hello;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Slf4j
@Component
public class MyFilter implements Filter {
    @Resource
    private Hello hello;

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        System.out.println("=======================");
        System.out.println("doFilter");
        System.out.println(hello);
        chain.doFilter(request, response);
        System.out.println("doFilter");
    }
}
