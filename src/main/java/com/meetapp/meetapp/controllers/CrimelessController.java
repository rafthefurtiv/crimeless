package com.meetapp.meetapp.controllers;

import com.meetapp.meetapp.CrimelessApplication;
import com.meetapp.meetapp.models.Position;
import com.meetapp.meetapp.repositories.CrimelessRepository;
import com.meetapp.meetapp.services.CrimelessServices;
import com.meetapp.meetapp.utils.FfmpegRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.*;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.VideoSize;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/crimeless")
public class CrimelessController {

    @Autowired
    CrimelessRepository crimelessRepository;

    @Autowired
    CrimelessServices crimelessServices;

    @GetMapping("/saveGet")
    public ResponseEntity saveGet(@RequestParam("sequenceId") String sequenceId) {
        CrimelessApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());
        FileInputStream is = null;
        byte [] byteArr = new byte[12];
        try
        {
            is = new FileInputStream("C:\\Users\\Raffaele\\Downloads\\prova_3s.mp4");

            byteArr = IOUtils.toByteArray(is);

            File someFile = new File("C:\\Users\\Raffaele\\Downloads\\prova_saved"+sequenceId+".mp4");
            FileOutputStream fos = new FileOutputStream(someFile);
            fos.write(byteArr);
            fos.flush();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        finally
        {
            // close things
        }

        String command = "C:\\Users\\Raffaele\\Documents\\Crimeless\\ffmpeg-4.3.1-win64-static\\ffmpeg-4.3.1-win64-static\\bin\\ffmpeg -i C:\\xampp\\htdocs\\dashboard\\video\\prova_saved02.mp4 -vcodec libx264 -acodec aac C:\\xampp\\htdocs\\dashboard\\video\\prova_saved02.ts";

        Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(true, HttpStatus.OK);
    }


    @PostMapping("/sendVideo")
    public ResponseEntity sendVideo(@RequestBody MultipartFile file, @RequestParam("username") String username, @RequestParam("sequenceId") String sequenceId,
                                    @RequestParam("idSession") String idSession) {
        CrimelessApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String folder = "C:\\xampp\\htdocs\\dashboard\\video\\provaRestService\\" + username + "\\" + idSession + "\\";
        String ext = ".mp4";
        String fileNameBase = "newFile_" + username + "_" + sequenceId;
        String fileName = fileNameBase + ext;

        String FilePath = folder + fileName;

        try {
            file.transferTo(new File(FilePath));
            crimelessServices.ffmpegTs(folder, fileName, fileNameBase);
            crimelessServices.writeList(folder, fileNameBase);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @GetMapping("/getListFile")
    public ResponseEntity getListFile(@RequestParam("username") String username) {
        CrimelessApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());

        String folder = "C:\\xampp\\htdocs\\dashboard\\video\\provaRestService\\" + username + "\\";


        File directory = new File(folder);
        File[] directories = directory.listFiles(File::isDirectory);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenDir = null;

        if (directories != null)
        {
            for (File dir : directories)
            {
                if (dir.lastModified() > lastModifiedTime)
                {
                    chosenDir = dir;
                    lastModifiedTime = dir.lastModified();
                }
            }
        }

        String ret = folder + chosenDir.getName() + "\\list.m3u8";

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }



/*    @GetMapping("/prova")
    public ResponseEntity prova() {
        CrimelessApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }*/


}
