package in.nmaloth.geodegraal.controller;

import in.nmaloth.geodegraal.dataService.UserInfoDataService;
import in.nmaloth.geodegraal.model.UserInfo;
import in.nmaloth.geodegraal.model.UserInfoDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
public class UserInfoController {

    private final UserInfoDataService userInfoDataService;

    public UserInfoController(UserInfoDataService userInfoDataService) {
        this.userInfoDataService = userInfoDataService;
    }


    @PostMapping(value="/userInfo")
    public Mono<UserInfoDTO> createUserInfoDTO(@RequestBody UserInfoDTO userInfoDTO){

        UserInfo userInfo = UserInfo.builder()
                .id(UUID.randomUUID().toString().replace("-",""))
                .name(userInfoDTO.getName())
                .badgeId(userInfoDTO.getBadgeId())
                .build()
                ;

        return userInfoDataService.updateUserInfo(userInfo)
                .map(userInfo1 -> UserInfoDTO.builder().id(userInfo1.getId())
                        .name(userInfo1.getName())
                        .badgeId(userInfo1.getBadgeId())
                        .build()
                )

                ;

    }

    @GetMapping(value = "/userInfo/{id}")
    public Mono<UserInfoDTO> getUserInfoDTO(@PathVariable String id){

        return userInfoDataService.findUserInfo(id)
                .map(userInfoOptional ->{
                    if(userInfoOptional.isEmpty()){
                        throw new RuntimeException("Invalid Id");
                    }
                    return userInfoOptional.get();
                } )
                .map(userInfo -> UserInfoDTO.builder()
                        .id(userInfo.getId())
                        .name(userInfo.getName())
                        .badgeId(userInfo.getBadgeId())
                        .build())
        ;
    }

    @GetMapping
    public Flux<UserInfoDTO> getAllUserInfos(){
        return userInfoDataService.getAllUserInfo()
                .map(userInfo -> UserInfoDTO.builder()
                        .id(userInfo.getId())
                        .name(userInfo.getName())
                        .badgeId(userInfo.getBadgeId())
                        .build())
                ;
    }

    
}
