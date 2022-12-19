package cs.jou.support;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Hello {
    @Value("hello")
    String name;
}
