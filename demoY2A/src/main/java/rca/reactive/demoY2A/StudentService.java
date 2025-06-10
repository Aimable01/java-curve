package rca.reactive.demoY2A;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public Flux<Student> getAllStudents() {
        return repo.findAll();
    }
}
