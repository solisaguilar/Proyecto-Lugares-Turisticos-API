package org.esfe.servicios.implementaciones;

import org.esfe.dtos.categoria.CategoríaGuardar;
import org.esfe.dtos.categoria.CategoríaModificar;
import org.esfe.dtos.categoria.CategoríaSalida;
import org.esfe.modelos.Categoría;
import org.esfe.repositorios.ICategoríaRepository;
import org.esfe.servicios.interfaces.ICategoríaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoríaService implements ICategoríaService {
    @Autowired
    private ICategoríaRepository categoríaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoríaSalida> obtenerTodos() {
        List<Categoría> categorias = categoríaRepository.findAll();

        return categorias.stream()
                .map(categoria -> modelMapper.map(categoria, CategoríaSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<CategoríaSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Categoría> page = categoríaRepository.findAll(pageable);

        List<CategoríaSalida> categoriasDto = page.stream()
                .map(categoria -> modelMapper.map(categoria, CategoríaSalida.class))
                .collect(Collectors.toList());

        return new PageImpl<>(categoriasDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public CategoríaSalida obtenerPorId(Integer id) {
        return modelMapper.map(categoríaRepository.findById(id).get(), CategoríaSalida.class);
    }

    @Override
    public CategoríaSalida crear(CategoríaGuardar categoriaGuardar) {
        Categoría categoria = categoríaRepository.save(modelMapper.map(categoriaGuardar, Categoría.class));
        return modelMapper.map(categoria, CategoríaSalida.class);
    }

    @Override
    public CategoríaSalida editar(CategoríaModificar categoriaModificar) {
        Categoría categoria = categoríaRepository.save(modelMapper.map(categoriaModificar, Categoría.class));
        return modelMapper.map(categoria, CategoríaSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        categoríaRepository.deleteById(id);
    }
}
