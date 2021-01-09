package com.meetapp.meetapp.controllers;

import com.meetapp.meetapp.CrimelessApplication;
import com.meetapp.meetapp.models.Position;
import com.meetapp.meetapp.repositories.CrimelessRepository;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/crimeless")
public class CrimelessController {

    @Autowired
    CrimelessRepository crimelessRepository;

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
        {}
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

    @PostMapping("/save")
    public ResponseEntity save(@RequestParam("sequenceId") String sequenceId, @RequestBody String jsonVideo) {
        CrimelessApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());

        FileInputStream is = null;
        byte [] byteArr = new byte[12];
        try
        {
            is = new FileInputStream("C:\\Users\\Raffaele\\Documents\\Crimeless\\prova.mp4");

            byteArr = IOUtils.toByteArray(is);


        }
        catch (IOException ioe)
        {}
        finally
        {
            // close things
        }

        return new ResponseEntity<>(byteArr, HttpStatus.OK);
    }

    @PostMapping("/sendVideo")
    public ResponseEntity sendVideo(@RequestBody MultipartFile file, @RequestParam("sequenceId") String sequenceId) {
        CrimelessApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());


        String folder = "C:\\Users\\Raffaele\\Documents\\Crimeless\\fileRest\\";
        String fileName = "newFile";
        String ext = ".mp4";

        String FilePath = folder + fileName + sequenceId + ext;

        try {
            file.transferTo(new File(FilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/sendVideo2")
    public ResponseEntity sendVideo2(@RequestParam("sequenceId") String sequenceId) {
        CrimelessApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());


        String folder = "C:\\Users\\Raffaele\\Documents\\Crimeless\\fileRest\\";
        String fileName = "newFile";
        String ext = ".mp4";

        String FilePath = folder + fileName + sequenceId + ext;

/*        try {
            //file.transferTo(new File(FilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        return new ResponseEntity<>(FilePath, HttpStatus.OK);
    }

}
