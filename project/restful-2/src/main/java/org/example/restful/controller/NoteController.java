package org.example.restful.controller;

import jakarta.validation.Valid;
import org.example.restful.dto.ApiResponse;
import org.example.restful.dto.NoteDTO;
import org.example.restful.dto.NoteRequest;
import org.example.restful.entity.User;
import org.example.restful.service.NoteService;
import org.example.restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<NoteDTO>>> getAllNotes() {
        User currentUser = getCurrentUser();
        List<NoteDTO> notes = noteService.getNotesByUser(currentUser);
        return ResponseEntity.ok(ApiResponse.success("Notes retrieved successfully", notes));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<NoteDTO>> createNote(@Valid @RequestBody NoteRequest noteRequest) {
        User currentUser = getCurrentUser();
        NoteDTO createdNote = noteService.createNote(
                noteRequest.getTitle(),
                noteRequest.getContent(),
                currentUser);
        return ResponseEntity.ok(ApiResponse.success("Note created successfully", createdNote));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NoteDTO>> getNoteById(@PathVariable Long id) {
        User currentUser = getCurrentUser();
        NoteDTO note = noteService.getNoteById(id, currentUser);
        return ResponseEntity.ok(ApiResponse.success("Note retrieved successfully", note));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NoteDTO>> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest noteRequest) {
        User currentUser = getCurrentUser();
        NoteDTO updatedNote = noteService.updateNote(
                id,
                noteRequest.getTitle(),
                noteRequest.getContent(),
                currentUser);
        return ResponseEntity.ok(ApiResponse.success("Note updated successfully", updatedNote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteNote(@PathVariable Long id) {
        User currentUser = getCurrentUser();
        noteService.deleteNote(id, currentUser);
        return ResponseEntity.ok(ApiResponse.success("Note deleted successfully"));
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getCurrentUser(username);
    }
}
