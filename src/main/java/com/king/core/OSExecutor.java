package com.king.core;

import com.king.exception.OSExcuteException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by king on 2017/4/26.
 */
public class OSExecutor {
    public static String command(String directory, String command) {
        boolean err = false;
        StringBuilder sb = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.directory(new File(directory));
            Process process = processBuilder.start();
            BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while((s = results.readLine()) != null)
                sb.append(s).append("\n");
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while((s = errors.readLine())!= null){
                System.err.println(s);
                err = true;
            }
        } catch (IOException e) {
            if(!command.startsWith("CMD /C"))
                command(directory, "CMD /C " + command);
            else
                throw new RuntimeException(e);
        }
        if(err)
            throw new OSExcuteException("Errors executing " + command);
        else
            return sb.toString();
    }
}
