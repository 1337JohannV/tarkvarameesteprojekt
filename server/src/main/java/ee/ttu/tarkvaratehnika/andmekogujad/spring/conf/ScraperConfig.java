package ee.ttu.tarkvaratehnika.andmekogujad.spring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan(basePackages = {"ee.ttu.tarkvaratehnika.andmekogujad.scraper"})
public class ScraperConfig {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setWaitForTasksToCompleteOnShutdown(false);
        executor.setThreadNamePrefix("scraper_task_executor_thread");
        executor.initialize();
        return executor;
    }
}
