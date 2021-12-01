package in.nmaloth.geodegraal.dataService;
import in.nmaloth.geodegraal.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserInfoDataServiceImpl implements UserInfoDataService {

    private final Region<String,UserInfo> userInfoRegion;

    public UserInfoDataServiceImpl(ClientCache cache){

        userInfoRegion = cache.<String,UserInfo>createClientRegionFactory(ClientRegionShortcut.PROXY)
                .create("user");
    }

    @Override
    public Mono<Optional<UserInfo>> findUserInfo(String id) {

        CompletableFuture<UserInfo> completableFuture = CompletableFuture
                .supplyAsync(() -> userInfoRegion.get(id));

        return Mono.fromFuture(completableFuture)
                .map(userInfo -> {
                    if(userInfo == null){
                        return Optional.empty();
                    }
                    return Optional.of(userInfo);
                })

                ;
    }

    @Override
    public Mono<UserInfo> updateUserInfo(UserInfo userInfo) {

        CompletableFuture<UserInfo> completableFuture = CompletableFuture
                .supplyAsync(() -> userInfoRegion.put(userInfo.getId(),userInfo));

        return Mono.fromFuture(completableFuture)
                .doOnNext(userInfo1 -> log.info(userInfo1.toString()))
                ;
    }

    @Override
    public Flux<UserInfo> getAllUserInfo() {

        CompletableFuture<Iterable<String>> completableFuture = CompletableFuture
                .supplyAsync(()-> userInfoRegion.keySetOnServer())
                ;
        return Mono.fromFuture(completableFuture)
                .flatMapIterable(keys ->keys)
                .map(s -> userInfoRegion.get(s))

                ;
    }
}
