package in.nmaloth.geodegraal.dataService;

import in.nmaloth.geodegraal.model.UserInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserInfoDataService {

    Mono<Optional<UserInfo>> findUserInfo(String id);
    Mono<UserInfo> updateUserInfo(UserInfo userInfo);
    Flux<UserInfo> getAllUserInfo();

}
