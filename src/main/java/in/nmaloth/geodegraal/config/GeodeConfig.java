package in.nmaloth.geodegraal.config;

import in.nmaloth.geodegraal.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
//import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
//import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
//import org.springframework.data.gemfire.config.annotation.EnablePdx;

@Configuration
@Slf4j
public class GeodeConfig {

    @Bean
    public ClientCache getClientCache(){

        ClientCacheFactory clientCacheFactory = new ClientCacheFactory()
                .addPoolLocator("192.168.29.79", 10334)
                ;
        log.info("Added Pool Locator");
        clientCacheFactory = clientCacheFactory.set("log-level", "WARN");

        log.info("Added Pool Locator");

        ClientCache clientCache = clientCacheFactory.create();
        return clientCache;

    }
}
