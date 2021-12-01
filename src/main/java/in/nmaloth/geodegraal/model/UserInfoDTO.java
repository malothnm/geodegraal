package in.nmaloth.geodegraal.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTO {

    private String id;
    private String name;
    private Integer badgeId;
}
