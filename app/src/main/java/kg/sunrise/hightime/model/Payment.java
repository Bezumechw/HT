package kg.sunrise.hightime.model;

public class Payment {

    String Message;
    String payment_link;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getPayment_link() {
        return payment_link;
    }

    public void setPayment_link(String payment_link) {
        this.payment_link = payment_link;
    }
}
