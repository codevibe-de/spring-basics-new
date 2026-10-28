package summer;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pizza.product.ProductService;
import summer.exception.BeansException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// TODO Demo 4: @Disabled entfernen und die Tests laufen lassen, init() nutzt src/test/resources/test-beans.xml
@Disabled
class XmlConfigBeanContainerTest {

    @Test
    void init__missingResource() {
        // when
        ThrowableAssert.ThrowingCallable callable = () -> new XmlConfigBeanContainer("i-dont-exist");

        // then
        assertThatThrownBy(callable)
                .isInstanceOf(BeansException.class);
    }


    @Test
    void init() {
        // given
        var container = new XmlConfigBeanContainer("/test-beans.xml");

        // then
        assertThat(container.getBean(ProductService.class)).isNotNull();
    }
}