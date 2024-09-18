package org.esfe.servicios.implementaciones;

import org.esfe.dtos.reserva.ReservaGuardar;
import org.esfe.dtos.reserva.ReservaModificar;
import org.esfe.dtos.reserva.ReservaSalida;
import org.esfe.modelos.Reserva;
import org.esfe.repositorios.IReservaRepository;
import org.esfe.servicios.interfaces.IReservaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService implements IReservaService {
    @Autowired
    private IReservaRepository reservaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ReservaSalida> obtenerTodos() {
        List<Reserva> reservas = reservaRepository.findAll();

        return reservas.stream()
                .map(reserva -> modelMapper.map(reserva, ReservaSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<ReservaSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Reserva> page = reservaRepository.findAll(pageable);

        List<ReservaSalida> reservasDto = page.stream()
                .map(reserva -> modelMapper.map(reserva, ReservaSalida.class))
                .collect(Collectors.toList());

        return new PageImpl<>(reservasDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public ReservaSalida obtenerPorId(Integer id) {
        return modelMapper.map(reservaRepository.findById(id).get(), ReservaSalida.class);
    }

    @Override
    public ReservaSalida crear(ReservaGuardar reservaGuardar) {
        Reserva reserva = reservaRepository.save(modelMapper.map(reservaGuardar,Reserva.class));
        return modelMapper.map(reserva, ReservaSalida.class);
    }

    @Override
    public ReservaSalida editar(ReservaModificar reservaModificar) {
        Reserva reserva = reservaRepository.save(modelMapper.map(reservaModificar, Reserva.class));
        return modelMapper.map(reserva, ReservaSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        reservaRepository.deleteById(id);
    }
}

