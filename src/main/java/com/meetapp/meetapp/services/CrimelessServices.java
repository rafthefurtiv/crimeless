package com.meetapp.meetapp.services;

import com.meetapp.meetapp.models.Lobby;
import com.meetapp.meetapp.models.Position;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public interface CrimelessServices {

    public void writeList(String path, String fileName) throws IOException;

    public void ffmpegTs(String path, String fileName, String fileNameBase) throws IOException, InterruptedException;

}
