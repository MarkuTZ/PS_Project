package markciurea.ps_server.model.thrashLocation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import markciurea.ps_server.model.dto.thrashLocationDto.ThrashLocationUserShortDTO;
import markciurea.ps_server.model.user.Employee;

@Data
@Entity
@NoArgsConstructor
public class ThrashLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String addressName;

    private Long x;

    private Long y;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employee;

    public ThrashLocation(ThrashLocationUserShortDTO dto) {
        this.Id = 0L;
        this.addressName = dto.getAddressName();
        this.x = dto.getX();
        this.y = dto.getY();
    }

}
