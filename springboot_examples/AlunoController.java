package com.sollaris.admin.catalogo.infrastructure;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    private final CreateAlunoHandler createHandler;
    private final GetAlunoHandler getHandler = new GetAlunoHandler();
    private final GetAllAlunosHandler getAllAlunosHandler = new GetAllAlunosHandler();
    private final DeleteAlunoHandler deleteAlunoHandler = new DeleteAlunoHandler();

    public AlunoController() {
        this.createHandler = new CreateAlunoHandler();
    }

    @PostMapping
    public Aluno create(@RequestBody Aluno aluno) {
        return createHandler.handle(aluno);
    }

    @GetMapping("/{id}")
    public Aluno getById(@PathVariable("id") String id) {
        return getHandler.handle(id);
    }

    @GetMapping
    public List<Aluno> getAll() {
        return getAllAlunosHandler.handle();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        deleteAlunoHandler.handle(id);
    }
}
