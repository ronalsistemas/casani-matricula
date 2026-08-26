package com.rcasani.service;

import com.rcasani.model.Estudiante;
import com.rcasani.repository.IEstudianteRepo;
import com.rcasani.service.impl.EstudianteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class EstudianteServiceTest {

    @MockitoBean
    private IEstudianteRepo estudianteRepo;

    @MockitoBean
    private IEstudianteService estudianteService;

    private Estudiante ESTUDIANTE_1;
    private Estudiante ESTUDIANTE_2;
    private Estudiante ESTUDIANTE_3;

    @BeforeEach
    public void init() { //init sirve para inicializar valores
        MockitoAnnotations.openMocks(this);
        this.estudianteService= new EstudianteServiceImpl(estudianteRepo);

        ESTUDIANTE_1 = new Estudiante(1, "70894117", "Ronal Paul", "Casani Villasante", LocalDate.parse("1994-01-12"), "ronalsistemas@gmail.com", "967461873", true);
        ESTUDIANTE_2 = new Estudiante(2, "70894128", "Katia Andrea", "Paredes Aguilar", LocalDate.parse("2004-02-18"), "katiapa1823@gmail.com", "936179555", true);

        Mockito.when(estudianteRepo.findAll()).thenReturn(List.of(ESTUDIANTE_1, ESTUDIANTE_2));
        Mockito.when(estudianteRepo.findById(any())).thenReturn(Optional.of(ESTUDIANTE_1));
        Mockito.when(estudianteRepo.save(any())).thenReturn(ESTUDIANTE_1);
    }

    @Test
    void findAllTest() throws Exception{
        List<Estudiante> response = estudianteService.listar();

        assertNotNull(response); //Está nulo?
        //assertTrue(response.isEmpty());
        //assertFalse(response.isEmpty()); Está vacio?
    }

    @Test
    void findByIdTest() throws Exception{
        final int ID = 1;
        Estudiante response = estudianteService.buscar(ID);

        assertNotNull(response);
    }

    @Test
    void saveTest() throws Exception{
        Estudiante response = estudianteService.guardar(ESTUDIANTE_1);;
        assertNotNull(response);
    }
/*
    @Test
    void deleteTest() throws Exception{
        final int ID = 1;

        estudianteService.eliminar(ID);
        estudianteService.eliminar(ID);
        estudianteService.eliminar(ID);

        //verify(repo, times(3)).deleteById(ID);
        //verify(repo, atLeast(2)).deleteById(ID);
        //verify(repo, atMost(2)).deleteById(ID);
        //verify(repo, never()).deleteById(any());
    }*/
}