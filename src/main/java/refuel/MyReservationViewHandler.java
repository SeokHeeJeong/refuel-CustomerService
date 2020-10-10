package refuel;

import refuel.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MyReservationViewHandler {


    @Autowired
    private MyReservationRepository myReservationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserved_then_CREATE_1 (@Payload Reserved reserved) {
        try {
            if (reserved.isMe()) {
                // view 객체 생성
                MyReservation myReservation = new MyReservation();
                // view 객체에 이벤트의 Value 를 set 함
                myReservation.setReservationId(reserved.getId());
                myReservation.setFuelType(reserved.getFuelType());
                myReservation.setCustomerId(reserved.getCustomerId());
                myReservation.setQty(reserved.getQty());
                myReservation.setStationId(reserved.getStationId());
                myReservation.setReservationStatus(reserved.getReservationStatus());
                myReservation.setPrice(reserved.getPrice());
                // view 레파지 토리에 save
                myReservationRepository.save(myReservation);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenRefuelled_then_UPDATE_1(@Payload Refuelled refuelled) {
        try {
            if (refuelled.isMe()) {
                System.out.println("##### listener Reserve : refuelled.isMe " );
                // view 객체 조회
                List<MyReservation> myReservationList = myReservationRepository.findByReservationId(refuelled.getReservationId());
                for(MyReservation myReservation : myReservationList){

                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myReservation.setReservationStatus(refuelled.getReservationStatus());
                    // view 레파지 토리에 save
                    myReservationRepository.save(myReservation);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaid_then_UPDATE_2(@Payload Paid paid) {
        try {
            if (paid.isMe()) {
                // view 객체 조회
                List<MyReservation> myReservationList = myReservationRepository.findByReservationId(paid.getReservationId());
                for(MyReservation myReservation : myReservationList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myReservation.setReservationStatus(paid.getReservationStatus());
                    myReservation.setPaymentType(paid.getPaymentType());
                    myReservation.setPaymentStatus(paid.getPaymentStatus());
                    // view 레파지 토리에 save
                    myReservationRepository.save(myReservation);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCanceled_then_UPDATE_3(@Payload ReservationCanceled reservationCanceled) {
        try {
            if (reservationCanceled.isMe()) {
                // view 객체 조회
                List<MyReservation> myReservationList = myReservationRepository.findByReservationId(reservationCanceled.getId());
                for(MyReservation myReservation : myReservationList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myReservation.setReservationStatus(reservationCanceled.getReservationStatus());
                    // view 레파지 토리에 save
                    myReservationRepository.save(myReservation);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}