package refuel;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="MyReservation_table")
public class MyReservation {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;
        private Long reservationId;
        private String fuelType;
        private String customerId;
        private String stationId;
        private String reservationStatus;
        private Long price;
        private String paymentType;
        private String paymentStatus;
        private int qty;

}
