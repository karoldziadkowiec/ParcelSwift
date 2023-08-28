public class Shipment {

    public int number;
    public String size;
    public String code;
    public String telNumOfSender;
    public String telNumOfReceiver;

    public Shipment(int nnumber, String givenSize, String givenCode, String givenTelNumOfSender, String givenTelNumOfReceiver) {

        number = nnumber;
        size = givenSize;
        code = givenCode;
        telNumOfSender = givenTelNumOfSender;
        telNumOfReceiver = givenTelNumOfReceiver;

    }
}
