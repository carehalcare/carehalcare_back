package carehalcare.carehalcare.config;


import com.google.api.gax.rpc.ApiException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class FCMConfig {

    @Value("${firebase.key-path}")
    String fcmKeyPath;

    @PostConstruct
    public void initialize() throws IOException {

        try {
            InputStream refreshToken = new ClassPathResource(fcmKeyPath).getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken)).build();

            FirebaseApp.initializeApp(options);
            log.info("Fcm Setting Completed");

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}