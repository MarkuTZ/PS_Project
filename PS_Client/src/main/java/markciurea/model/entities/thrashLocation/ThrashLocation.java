package markciurea.model.entities.thrashLocation;

import lombok.Data;
import markciurea.model.entities.dto.UserShort;

@Data
public class ThrashLocation {

    private Long id;

    private String addressName;

    private Long x;

    private Long y;

    private UserShort employee;
}
