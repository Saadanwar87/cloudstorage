package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/home")
public class HomeController {

    private UserService userService;
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;

    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String getHomePage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("files", fileService.getFileNames(userService.getUser(auth.getName()).getUserId()));
        model.addAttribute("notes", noteService.getNotes(userService.getUser(auth.getName()).getUserId()));
        model.addAttribute("credentials", credentialService.getCredential(userService.getUser(auth.getName()).getUserId()));
        return "home";
    }

    @PostMapping("/file-upload")
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException, SQLException {
        if(fileUpload.isEmpty()){
            model.addAttribute("success", false);
            model.addAttribute("message", "No file selected to upload!");
            return "result";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loginUser = userService.getUser(auth.getName());

        File file = new File();
        file.setFileName(fileUpload.getOriginalFilename());
        file.setContentType(fileUpload.getContentType());
        String fSize = ""+fileUpload.getSize();
        file.setFileSize(fSize);
        file.setFileData(fileUpload.getBytes());
        file.setUserId(loginUser.getUserId());

        int rowsAdded = fileService.addFile(file);
        if(rowsAdded < 0){
            model.addAttribute("success", false);
            model.addAttribute("error", true);
            model.addAttribute("message", "File not added.");
        }else {
            model.addAttribute("success", true);
            model.addAttribute("error", false);
            model.addAttribute("message", "new File added successfully");
        }

        return "result";
    }

    @GetMapping ("delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model){
       int change = fileService.deleteFile(fileId);
       if(change >0) {
           model.addAttribute("success", true);
           model.addAttribute("message", "File deleted successfully");
       }else{
           model.addAttribute("error", true);
           model.addAttribute("message", "File not deleted");
       }
        return "result";
    }

    @GetMapping("download/{fileId}")
    public ResponseEntity downloadFile (@PathVariable Integer fileId, Authentication authentication){
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        File file = fileService.getFile(userId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getFileData());
    }

    @PostMapping("/note")
    public String addNote(Note note, Model model, Authentication authentication){
        int userId = userService.getUser(authentication.getName()).getUserId();
        int result = 0;
        if(note.getNoteId() != null){
            result = noteService.updateNote(note);
        }else {
            result = noteService.addNote(note, userId);
        }
        if(result > 0){
            model.addAttribute("success", true);
            model.addAttribute("message", "File deleted successfully");
        }else{
            model.addAttribute("error", true);
            model.addAttribute("message", "File not deleted");
        }

        return "result";
    }

    @GetMapping("noteDelete/{noteId}")
    public String deleteNote(@PathVariable int noteId, Model model){
        int change = noteService.deleteNote(noteId);
        if(change > 0){
            model.addAttribute("success", true);
            model.addAttribute("message", "Note deleted successfully");
        }else{
            model.addAttribute("error", true);
            model.addAttribute("message", "Note not deleted");
        }

        return "result";
    }

    @PostMapping("/credential")
    public String addCredential(Credential credential, Model model, Authentication authentication){
        int userId = userService.getUser(authentication.getName()).getUserId();
        int result = 0;
        if(credential.getCredentialId() != null){
            result = credentialService.updateCredential(credential);
        }else {
            result = credentialService.addCredential(credential, userId);
        }
        if(result > 0){
            model.addAttribute("success", true);
            model.addAttribute("message", "Note deleted successfully");
        }else{
            model.addAttribute("error", true);
            model.addAttribute("message", "Note not deleted");
        }
        return "result";
    }

    @GetMapping("credentialDelete/{credentialId}")
    public String deleteCredential(@PathVariable int credentialId, Model model){
        int change = credentialService.deleteCredential(credentialId);
        if(change > 0){
            model.addAttribute("success", true);
            model.addAttribute("message", "Note deleted successfully");
        }else{
            model.addAttribute("error", true);
            model.addAttribute("message", "Note not deleted");
        }

        return "result";
    }
}
