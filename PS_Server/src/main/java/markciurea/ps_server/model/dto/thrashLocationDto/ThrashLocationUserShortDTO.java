package markciurea.ps_server.model.dto.thrashLocationDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import markciurea.ps_server.model.dto.userDto.UserShort;
import markciurea.ps_server.model.thrashLocation.ThrashLocation;

@Data
@NoArgsConstructor
public class ThrashLocationUserShortDTO {

    private Long Id;

    private String addressName;

    private Long x;

    private Long y;

    private UserShort employee;

    public ThrashLocationUserShortDTO(ThrashLocation thrashLocation) {
        this.Id = thrashLocation.getId();
        this.addressName = thrashLocation.getAddressName();
        this.x = thrashLocation.getX();
        this.y = thrashLocation.getY();
        this.employee = new UserShort(thrashLocation.getEmployee());
    }

}
