package com.meetapp.meetapp.services;

import com.meetapp.meetapp.models.Lobby;
import com.meetapp.meetapp.models.Position;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CrimelessServicesImpl implements CrimelessServices {

    @Override
    public void writeList(String path, String fileName) throws IOException {
        String listName = "list.m3u8";
        File playlistFile = new File(path+listName);
        FileWriter myWriter;
        if (playlistFile.createNewFile()) {
            System.out.println("File created: " + path+listName);
            myWriter = new FileWriter(path+listName, true);

            myWriter.write("#EXTM3U\n");
            myWriter.write("#EXT-X-PLAYLIST-TYPE:EVENT\n");
            myWriter.write("#EXT-X-VERSION:7\n");
            myWriter.write("#EXT-X-TARGETDURATION:4\n");
            myWriter.write("#EXT-X-MEDIA-SEQUENCE:0\n");
            myWriter.write("#EXT-X-DISCONTINUITY\n");
            myWriter.write("#EXTINF:3.000,\n");
            myWriter.write(fileName+".ts\n");

        } else {
            System.out.println("File already exists.");
            myWriter = new FileWriter(path+listName, true);
            myWriter.write("#EXT-X-DISCONTINUITY\n");
            myWriter.write("#EXTINF:3.000,\n");
            myWriter.write(fileName+".ts\n");
        }


        myWriter.close();
    }


    @Override
    public void ffmpegTs(String folder, String fileName, String fileNameBase) throws IOException, InterruptedException {

        List<String> commands = new ArrayList<>();
        commands.add("ffmpeg");
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

        ProcessBuilder builder = new ProcessBuilder();
        Process process = builder.command(commands).redirectErrorStream(true).start();
        new PrintStream(String.valueOf(process.getInputStream()));
        new PrintStream(String.valueOf(process.getErrorStream()));
        process.waitFor();

    }
}
