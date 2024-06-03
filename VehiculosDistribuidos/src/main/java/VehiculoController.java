package com.example.vehiculosdistribuidos.controller;

import com.example.vehiculosdistribuidos.Vehiculo;
import com.example.vehiculosdistribuidos.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping
    public List<Vehiculo> consultarTodos() {
        return vehiculoRepository.consultarTodos();
    }

    @GetMapping("/{id}")
    public Vehiculo consultarPorId(@PathVariable int id) {
        return vehiculoRepository.consultarPorId(id);
    }

    @PostMapping
    public void insertarVehiculo(@RequestBody Vehiculo vehiculo) {
        vehiculoRepository.insertarVehiculo(vehiculo);
    }

    @DeleteMapping("/{id}")
    public void eliminarVehiculo(@PathVariable int id) {
        vehiculoRepository.eliminarVehiculo(id);
    }

    @PutMapping("/{id}")
    public void actualizarVehiculo(@PathVariable int id, @RequestBody Vehiculo vehiculo) {
        vehiculo.setId(id);
        vehiculoRepository.actualizarVehiculo(vehiculo);
    }

    @GetMapping("/potenciaMedia/{tipo}")
    public double obtenerPotenciaMedia(@PathVariable String tipo) {
        return vehiculoRepository.obtenerPotenciaMedia(tipo);
    }

    @GetMapping("/potenciaMaxima/{tipo}")
    public int obtenerPotenciaMaxima(@PathVariable String tipo) {
        return vehiculoRepository.obtenerPotenciaMaxima(tipo);
    }
}

