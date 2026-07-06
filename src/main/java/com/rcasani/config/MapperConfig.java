package com.rcasani.config;

import com.rcasani.dto.EstudianteDTO;
import com.rcasani.model.Estudiante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("defaultMapper")
    public ModelMapper defaultMapper() {
        return new ModelMapper();
    }
    
    @Bean("estudianteMapper")
    public ModelMapper estudianteMapper() {
        ModelMapper mapper = new ModelMapper();

        //Handle Mismatches
        //Lectura
        mapper.createTypeMap(Estudiante.class, EstudianteDTO.class)
                .addMapping(Estudiante::getCorreo, (dest, v) -> dest.setCorreo((String) v));

        //Escritura
        mapper.createTypeMap(EstudianteDTO.class, Estudiante.class)
                .addMapping(EstudianteDTO::getCorreo, (dest, v) -> dest.setCorreo((String) v));

        return mapper;
    }
}