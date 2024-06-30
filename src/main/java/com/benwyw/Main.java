package com.benwyw;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
@Configuration
@EnableConfigurationProperties
@EnableScheduling
@EnableCaching
@EnableAsync
//@PropertySource("classpath:application.properties")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Main {

    @Bean
    public DataSource dataSource() {

        // Create a DataSource object and set its properties
        Dotenv config = Dotenv.configure().ignoreIfMissing().load();

        // Check if .env file exists
        if (ObjectUtils.isEmpty(config) || config.entries().isEmpty()) {
            log.warn(".env file not found. Using fallback values.");
//            return createFallbackDataSource();
            return null; // disable dataSource if auth not found
        }

        // Read properties from .env or use fallback values
        String url = config.get("SPRING_DATASOURCE_URL", "jdbc:h2:mem:testdb");
        String username = config.get("ORACLE_DB_USER", "sa");
        String password = config.get("ORACLE_DB_PASSWORD", "");

        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("oracle.jdbc.OracleDriver")
                .url(url)
                .username(username)
                .password(password)
                .build();

        // Return the DataSource object
        return dataSource;
    }

    @Deprecated
    private DataSource createFallbackDataSource() {
        // Create a fallback DataSource with default or predefined values
        // Adjust these values as needed
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:testdb")
                .username("sa")
                .password("")
                .build();
    }

    public static void main( String[] args ) {
        // Disable headless mode
        // VM option: -Djava.awt.headless=false
        System.setProperty("java.awt.headless", "false");

        SpringApplication.run(Main.class, args);

        if (!GraphicsEnvironment.isHeadless()) {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Spring Boot Swing App");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);

                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());

                JLabel label = new JLabel("Hello, Swing UI!");
                panel.add(label);

                JButton apiButton = new JButton("Call API");
                apiButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Trigger your API call here (e.g., using HttpURLConnection)
                        // Display the API response in a dialog or label
                        JOptionPane.showMessageDialog(frame, "API response: Hello from Spring Boot!");
                    }
                });
                panel.add(apiButton);

                frame.add(panel);
                frame.setVisible(true);
            });
        } else {
            log.warn("Running in headless mode. GUI components not supported.");
        }

        log.info("swing-boot started.");
    }
}
