package com.benwyw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import java.awt.*;

@Configuration
public class PanelConfiguration {

    @Bean
    public CardLayout cardLayout() {
        return new CardLayout();
    }

    @Bean
    public JPanel mainPanel(CardLayout cardLayout) {
        return new JPanel(cardLayout);
    }
}
