package com.example.techecommerceserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
public class TechEcommerceServerApplication {

    public static void main(String[] args)throws IOException {

        SpringApplication.run(TechEcommerceServerApplication.class, args);

        try {
            FileInputStream serviceAccount = new FileInputStream("./src/main/resources/serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        return mapper;
//    }

}
