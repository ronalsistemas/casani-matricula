package com.rcasani.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcasani.dto.EstudianteDTO;
import com.rcasani.exception.ModelNotFoundException;
import com.rcasani.model.Estudiante;
import com.rcasani.service.IEstudianteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EstudianteController.class)
public class DocenteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEstudianteService estudianteService;

    @MockitoBean
    @Qualifier("estudianteMapper")
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    Estudiante ESTUDIANTE_1 = new Estudiante(1, "70894117", "Ronal Paul", "Casani Villasante", LocalDate.parse("1994-01-12"), "ronalsistemas@gmail.com", "967461873", true);
    Estudiante ESTUDIANTE_2 = new Estudiante(2, "70894128", "Katia Andrea", "Paredes Aguilar", LocalDate.parse("2004-02-18"), "katiapa1823@gmail.com", "936179555", true);

    EstudianteDTO ESTUDIANTEDTO_1 = new EstudianteDTO(1, "Ronal Paul", "Casani Villasante", "70894117", "ronalsistemas@gmail.com","967461873", LocalDate.parse("1994-01-12"), true);
    EstudianteDTO ESTUDIANTEDTO_2 = new EstudianteDTO(2, "Katia Andrea", "Paredes Aguilar", "70894128", "katiapa1823@gmail.com","936179555", LocalDate.parse("2004-02-18"), true);

    @Test
    public void findAllTest() throws Exception{
        Mockito.when(estudianteService.listar()).thenReturn(Arrays.asList(ESTUDIANTE_1, ESTUDIANTE_2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))); //Que me devuelva dos elementos
    }

    @Test
    public void findByIdTest() throws Exception{
        final int ID = 1;

        Mockito.when(estudianteService.buscar(any())).thenReturn(ESTUDIANTE_1);
        Mockito.when(modelMapper.map(ESTUDIANTE_1, EstudianteDTO.class)).thenReturn(ESTUDIANTEDTO_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/estudiantes/{id}",ID)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres", is("Ronal Paul")));
    }

    @Test
    public void saveTest() throws Exception{
        Mockito.when(estudianteService.guardar(any())).thenReturn(ESTUDIANTE_2);
        Mockito.when(modelMapper.map(ESTUDIANTE_2, EstudianteDTO.class)).thenReturn(ESTUDIANTEDTO_2);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ESTUDIANTE_2))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.estado", is(true)));
    }

    @Test
    public void updateTest() throws Exception{
        Mockito.when(estudianteService.actualizar(any(), any())).thenReturn(ESTUDIANTE_2);
        Mockito.when(modelMapper.map(ESTUDIANTE_2, EstudianteDTO.class)).thenReturn(ESTUDIANTEDTO_2);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/estudiantes/{id}",2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ESTUDIANTEDTO_2))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado", is(true)));
    }

    @Test
    public void updateErrorTest() throws Exception{
        final int ID = 99;
        Mockito.when(estudianteService.actualizar(any(), any())).thenThrow(new ModelNotFoundException("ID NO VALIDO: " + ID));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/estudiantes/{id}",ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ESTUDIANTEDTO_2))
                )
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(ModelNotFoundException.class, result.getResolvedException()));
    }

    @Test
    public void deleteTest() throws Exception{
        final int ID = 1;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/estudiantes/{id}",ID)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteErrorTest() throws Exception{
        final int ID = 99;

        Mockito.doThrow(new ModelNotFoundException("ID NO VALIDO: " + ID)).when(estudianteService).eliminar(any());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/estudiantes/{id}",ID)
                )
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(ModelNotFoundException.class, result.getResolvedException()));;
    }
}