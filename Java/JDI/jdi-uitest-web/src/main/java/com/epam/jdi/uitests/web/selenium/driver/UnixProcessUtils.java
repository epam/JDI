package com.epam.jdi.uitests.web.selenium.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UnixProcessUtils {
    /**
     *
     * @param rootNamePart
     */
    public static void killProcessesTree(String rootNamePart) {
        List<String> chrome;
        try {
            chrome = getPIDsByNamePart(rootNamePart);
            for (String s : chrome) {
                int pid = Integer.parseInt(s);
                killChildProcesses(pid);
            }
            killProcessesByNamePart(rootNamePart);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException("Can't kill drivers");
        }
    }

    /**
     *
     * @param value
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private static List<String> getPIDsByNamePart(String value) throws IOException, InterruptedException {
        Process process = new ProcessBuilder(
            "/usr/bin/pgrep", "-afi", value)
            .start();
        process.waitFor();
        return inputStreamToList(process.getInputStream());
    }
    private static List<String> inputStreamToList(InputStream stream) {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (Exception ex) { throw new RuntimeException("Can't read Input Stream: " + ex.getMessage()); }
        return list;
    }
    /**
     *
     * @param pid
     */
    private static void killChildProcesses(int pid) {
        String ppid = String.valueOf("-P "+pid);
        killProcessWithArgs(Collections.singletonList(ppid));
    }

    /**
     *
     * @param name
     */
    public static void killProcessesByNamePart(String name) {
        killProcessWithArgs(Arrays.asList("-aif", name));
    }

    /**
     *
     * @param args
     */
    private static void killProcessWithArgs(List<String> args) {
        List<String> list = new ArrayList<>();
        list.add("/usr/bin/pkill");
        list.addAll(args);
        try {
            Process child = new ProcessBuilder(list.toArray(new String[list.size()])).start();
            child.waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException("Can't kill process: " + e.getMessage());
        }
    }

}