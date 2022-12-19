package cs.jou.config;

import cs.jou.support.Intercept;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Component
public class MvcConfigure implements WebMvcConfigurer, ApplicationContextAware {
    protected ApplicationContext applicationContext;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<Intercept> intercepts = getDefaultIntercepts();

        for (Intercept intercept : intercepts) {
            registry.addInterceptor(intercept)
                    .addPathPatterns(intercept.getPathPattern());
        }
    }

    protected List<Intercept> getDefaultIntercepts() {
        return List.copyOf(applicationContext.getBeansOfType(Intercept.class).values());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
}
