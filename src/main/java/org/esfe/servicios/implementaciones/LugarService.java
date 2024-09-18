package org.esfe.servicios.implementaciones;

import org.esfe.dtos.lugar.LugarGuardar;
import org.esfe.dtos.lugar.LugarModificar;
import org.esfe.dtos.lugar.LugarSalida;
import org.esfe.modelos.Lugar;
import org.esfe.repositorios.ILugarRepository;
import org.esfe.servicios.interfaces.ILugarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LugarService implements ILugarService {
    @Autowired
    private ILugarRepository lugarRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LugarSalida> obtenerTodos() {
        List<Lugar> lugares = lugarRepository.findAll();

        return lugares.stream()
                .map(lugar -> modelMapper.map(lugar, LugarSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<LugarSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Lugar> page = lugarRepository.findAll(pageable);

        List<LugarSalida> lugaresDto = page.stream()
                .map(lugar -> modelMapper.map(lugar, LugarSalida.class))
                .collect(Collectors.toList());

        return new PageImpl<>(lugaresDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public LugarSalida obtenerPorId(Integer id) {
        return modelMapper.map(lugarRepository.findById(id).get(), LugarSalida.class);
    }

    @Override
    public LugarSalida crear(LugarGuardar lugarGuardar) {
        Lugar lugar = lugarRepository.save(modelMapper.map(lugarGuardar,Lugar.class));
        return modelMapper.map(lugar, LugarSalida.class);
    }

    @Override
    public LugarSalida editar(LugarModificar lugarModificar) {
        Lugar lugar = lugarRepository.save(modelMapper.map(lugarModificar, Lugar.class));
        return modelMapper.map(lugar, LugarSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        lugarRepository.deleteById(id);
    }
}

