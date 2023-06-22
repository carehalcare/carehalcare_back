package carehalcare.carehalcare.service;

import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FCMService {
    @Value("${firebase.icon}")
    String icon;

    public void sendMessage(String registrationToken, String body) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                        .setTtl(3600*1000)
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .setRestrictedPackageName("carehalcare.carehalcare")
                        .setDirectBootOk(true)
                        .setNotification(AndroidNotification.builder()
                                .setTitle("케어할케어")
                                .setBody(body)
                                .setIcon(icon)
                                .build())
                        .build())
                .setToken(registrationToken)
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("FCM_Send: " + response);
        } catch (FirebaseMessagingException e) {
            log.info("FCM_Except: "+e.getMessage());
        }
    }
}
