package ra.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ra.controller"})
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware {
    private String pathUpload = "C:\\Users\\ADMIN\\Desktop\\modul4Project\\src\\main\\webapp\\assets\\image";
    private String  pathCss = "E:\\JAVACORE\\Java_MD4_Session1_SpringMVC_CRUD\\demoSring\\src\\main\\webapp\\assets\\css\\";
    private ApplicationContext applicationContext;
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver view = new InternalResourceViewResolver();
        view.setPrefix("/WEB-INF/views/");
        view.setSuffix(".jsp");
        return view;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(52428800);
        return resolver;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**","/css/**","/js/**",
                        "/fonts/flaticon/**","/scss/**")
                .addResourceLocations("classpath:/assets/images/","classpath:/assets/css/",
                        "classpath:/assets/js/","classpath:/assets/fonts/flaticon/",
                        "classpath:/assets/scss/");
    }
}


