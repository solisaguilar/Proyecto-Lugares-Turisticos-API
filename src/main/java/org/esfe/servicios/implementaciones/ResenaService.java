package org.esfe.servicios.implementaciones;

import org.esfe.dtos.resena.ResenaGuardar;
import org.esfe.dtos.resena.ResenaModificar;
import org.esfe.dtos.resena.ResenaSalida;
import org.esfe.modelos.Resena;
import org.esfe.repositorios.IResenaRepository;
import org.esfe.servicios.interfaces.IResenaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResenaService implements IResenaService {
    @Autowired
    private IResenaRepository resenaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ResenaSalida> obtenerTodos() {
        List<Resena> resenas = resenaRepository.findAll();

        return resenas.stream()
                .map(resena -> modelMapper.map(resena, ResenaSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<ResenaSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Resena> page = resenaRepository.findAll(pageable);

        List<ResenaSalida> resenasDto = page.stream()
                .map(resena -> modelMapper.map(resena, ResenaSalida.class))
                .collect(Collectors.toList());

        return new PageImpl<>(resenasDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public ResenaSalida obtenerPorId(Integer id) {
        return modelMapper.map(resenaRepository.findById(id).get(), ResenaSalida.class);
    }

    @Override
    public ResenaSalida crear(ResenaGuardar resenaGuardar) {
        Resena resena = resenaRepository.save(modelMapper.map(resenaGuardar,Resena.class));
        return modelMapper.map(resena, ResenaSalida.class);
    }

    @Override
    public ResenaSalida editar(ResenaModificar resenaModificar) {
        Resena resena = resenaRepository.save(modelMapper.map(resenaModificar, Resena.class));
        return modelMapper.map(resena, ResenaSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        resenaRepository.deleteById(id);
    }
}
