interface MessageService {
    void sendMessage(String message);
}

class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {

        System.out.println("Message sent: " + message);
    }
}

class Notification {
    private final MessageService messService;

    public Notification(MessageService messService) {
        this.messService = messService;
    }

    public void notify(String message) {
        messService.sendMessage(message);
    }
}

public class DIP {
    public static void main(String[] args) {
        MessageService emailService = new EmailService();
        Notification notification = new Notification(emailService);
        
        notification.notify("Witaj w SOLID!");
        
        emailService.sendMessage("Witaj");
    }
}