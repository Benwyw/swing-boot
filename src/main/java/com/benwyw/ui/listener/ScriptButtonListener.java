package com.benwyw.ui.listener;

import com.benwyw.ui.panels.BatchPanel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class ScriptButtonListener implements ActionListener {
    private String script;
    private BatchPanel batchPanel;

    public ScriptButtonListener(String script, BatchPanel batchPanel) {
        this.script = script;
        this.batchPanel = batchPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        runBatchScript(script);
    }

    private void runBatchScript(String script) {
        try {

            // retrieve resources directory path
            String resourcesPath = new ClassPathResource("/").getURL().getPath();
            if (resourcesPath.startsWith("/")) {
                resourcesPath = resourcesPath.substring(1);
            }

            // change lang to en and execute batch script
            String cmd = "cmd /c chcp 437 && " + resourcesPath + "batch/" + script;
            log.info(String.format("Running command: %s", cmd));
            Process process = Runtime.getRuntime().exec(cmd.split(" "));
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;

            // display
            this.batchPanel.appendText("Running " + script);

            while ((line = reader.readLine()) != null) {
                this.batchPanel.appendText(line);
            }

            while ((line = errorReader.readLine()) != null) {
                this.batchPanel.appendText("ERROR: " + line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                this.batchPanel.appendText("Batch script " + script + " failed with exit code " + exitCode);
            } else {
                this.batchPanel.appendText("Batch script " + script + " completed successfully.");
            }
        } catch (Exception ex) {
            this.batchPanel.appendText("Exception occurred while running script: " + ex.getMessage());
        }
    }
}

