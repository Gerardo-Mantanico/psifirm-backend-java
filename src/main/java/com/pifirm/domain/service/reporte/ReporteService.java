package com.pifirm.domain.service.reporte;

import com.pifirm.domain.dto.reporte.AreaServicioSesionesDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ReporteService {

    private final JdbcTemplate jdbcTemplate;

    public ReporteService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AreaServicioSesionesDto> obtenerSesionesPorAreaYServicio() {
        String sql = "SELECT a.id AS area_id, a.nombre AS area_nombre, " +
                "ss.id AS servicio_id, ss.nombre AS servicio_nombre, " +
                "COUNT(s.id) AS sesiones " +
                "FROM session s " +
                "JOIN historia_clinica h ON h.id = s.hc_id " +
                "JOIN servicios ss ON ss.id = h.servicio_id " +
                "JOIN area a ON ss.id = a.servicio_id " +
                "GROUP BY a.id, a.nombre, ss.id, ss.nombre " +
                "ORDER BY a.nombre, ss.nombre";

        return jdbcTemplate.query(sql, new RowMapper<AreaServicioSesionesDto>() {
            @Override
            public AreaServicioSesionesDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new AreaServicioSesionesDto(
                        rs.getLong("area_id"),
                        rs.getString("area_nombre"),
                        rs.getLong("servicio_id"),
                        rs.getString("servicio_nombre"),
                        rs.getLong("sesiones")
                );
            }
        });
    }
}