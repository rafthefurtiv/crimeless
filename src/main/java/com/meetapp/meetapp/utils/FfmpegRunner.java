package com.meetapp.meetapp.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FfmpegRunner extends Thread{

    public void run() {

        String folder = "C:\\xampp\\htdocs\\dashboard\\video\\provaRestService\\" + "rafthefurtiv" + "\\" + "zzk" + "\\";
        String ext = ".mp4";
        String fileNameBase = "newFile_" + "rafthefurtiv" + "_" + "2";
        String fileName = fileNameBase + ext;

        String FilePath = folder + fileName;
        String command = "ffmpeg -i "+folder+fileName+" -f hls -hls_time 3 -hls_list_size 0 -hls_flags single_file "+folder+fileNameBase+".m3u8";

        List<String> commands = new ArrayList<>();
        commands.add("ffmpeg");
        commands.add("-nostdin");
        commands.add("-loglevel");
        commands.add("quiet");
        commands.add("-i");
        commands.add(folder+fileName);
        commands.add("-f");
        commands.add("hls");
        commands.add("-hls_time");
        commands.add("3");
        commands.add("-hls_list_size");
        commands.add("0");
        commands.add("-hls_flags");
        commands.add("single_file");
        commands.add(folder+fileNameBase+".m3u8");


        try {
            ProcessBuilder builder = new ProcessBuilder();
            Process process = builder.command(commands).redirectErrorStream(true).start();
            new PrintStream(String.valueOf(process.getInputStream()));
            new PrintStream(String.valueOf(process.getErrorStream()));
            process.waitFor();
            System.out.println("FFMPEG fine.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("FFMPEG errore.");
        }
    }



}
