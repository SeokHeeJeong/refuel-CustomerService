package refuel;

import lombok.Data;

@Data
public class Reserved extends AbstractEvent {

    private Long id;
    private String fuelType;
    private int qty;
    private String customerId;
    private String stationId;
    private String reservationStatus;
    private Long price;

}