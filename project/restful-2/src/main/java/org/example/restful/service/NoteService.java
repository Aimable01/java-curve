package org.example.restful.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.restful.dto.NoteDTO;
import org.example.restful.entity.Note;
import org.example.restful.entity.User;
import org.example.restful.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<NoteDTO> getNotesByUser(User user) {
        List<Note> notes = noteRepository.findByUserOrderByCreatedAtDesc(user);
        return notes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NoteDTO createNote(String title, String content, User user) {
        Note note = new Note(title, content, user);
        note = noteRepository.save(note);
        return convertToDTO(note);
    }

    public NoteDTO updateNote(Long noteId, String title, String content, User user) {
        Note note = noteRepository.findByIdAndUser(noteId, user)
                .orElseThrow(() -> new EntityNotFoundException("Note not found or not owned by user"));

        note.setTitle(title);
        note.setContent(content);
        note.setUpdatedAt(LocalDateTime.now());

        note = noteRepository.save(note);
        return convertToDTO(note);
    }

    public void deleteNote(Long noteId, User user) {
        Note note = noteRepository.findByIdAndUser(noteId, user)
                .orElseThrow(() -> new EntityNotFoundException("Note not found or not owned by user"));

        noteRepository.delete(note);
    }

    public NoteDTO getNoteById(Long noteId, User user) {
        Note note = noteRepository.findByIdAndUser(noteId, user)
                .orElseThrow(() -> new EntityNotFoundException("Note not found or not owned by user"));

        return convertToDTO(note);
    }

    private NoteDTO convertToDTO(Note note) {
        return new NoteDTO(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt());
    }
}
