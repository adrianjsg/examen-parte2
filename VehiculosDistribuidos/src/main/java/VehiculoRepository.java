/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adriansobrevela
 */
package vehiculosdistribuidos.repository;

import com.example.vehiculosdistribuidos.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VehiculoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Vehiculo> consultarTodos() {
        String sql = "SELECT * FROM vehiculos";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToVehiculo(rs));
    }

    public Vehiculo consultarPorId(int id) {
        String sql = "SELECT * FROM vehiculos WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> mapRowToVehiculo(rs));
    }

    public void insertarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (tipo, marca, potencia, fecha_compra) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, vehiculo.getTipo(), vehiculo.getMarca(), vehiculo.getPotencia(), vehiculo.getFechaCompra());
    }

    public void eliminarVehiculo(int id) {
        String sql = "DELETE FROM vehiculos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void actualizarVehiculo(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculos SET tipo = ?, marca = ?, potencia = ?, fecha_compra = ? WHERE id = ?";
        jdbcTemplate.update(sql, vehiculo.getTipo(), vehiculo.getMarca(), vehiculo.getPotencia(), vehiculo.getFechaCompra(), vehiculo.getId());
    }

    public double obtenerPotenciaMedia(String tipo) {
        String sql = "SELECT AVG(potencia) FROM vehiculos WHERE tipo = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{tipo}, Double.class);
    }

    public int obtenerPotenciaMaxima(String tipo) {
        String sql = "SELECT MAX(potencia) FROM vehiculos WHERE tipo = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{tipo}, Integer.class);
    }

    private Vehiculo mapRowToVehiculo(ResultSet rs) throws SQLException {
        return new Vehiculo(
            rs.getInt("id"),
            rs.getString("tipo"),
            rs.getString("marca"),
            rs.getInt("potencia"),
            rs.getDate("fecha_compra")
        );
    }
}

