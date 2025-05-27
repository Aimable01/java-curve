package org.example.restful.repository;

import org.example.restful.entity.Note;
import org.example.restful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserOrderByCreatedAtDesc(User user);

    Optional<Note> findByIdAndUser(Long id, User user);
}
